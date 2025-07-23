import os
from dotenv import load_dotenv
from motor.motor_asyncio import AsyncIOMotorClient
from pymongo.errors import ConnectionFailure
import logging

logger = logging.getLogger(__name__)

load_dotenv()

DATABASE_CONFIG = {
    "host": os.getenv("MONGO_HOST", "localhost"),
    "port": int(os.getenv("MONGO_PORT", "27017")),
    "database": os.getenv("MONGO_DATABASE", "petshop"),
    "username": os.getenv("MONGO_USERNAME"),
    "password": os.getenv("MONGO_PASSWORD"),
}


class Database:
    """Async MongoDB connection manager using Motor"""

    _client = None
    _database = None

    @classmethod
    async def connect(cls):
        """Connect to MongoDB asynchronously"""
        try:
            if DATABASE_CONFIG["username"] and DATABASE_CONFIG["password"]:
                connection_string = f"mongodb://{DATABASE_CONFIG['username']}:{DATABASE_CONFIG['password']}@{DATABASE_CONFIG['host']}:{DATABASE_CONFIG['port']}/{DATABASE_CONFIG['database']}"
            else:
                connection_string = f"mongodb://{DATABASE_CONFIG['host']}:{DATABASE_CONFIG['port']}"

            cls._client = AsyncIOMotorClient(
                connection_string, serverSelectionTimeoutMS=5000)

            await cls._client.admin.command('ping')
            cls._database = cls._client[DATABASE_CONFIG["database"]]

            logger.info(
                f"✅ Connected to MongoDB: {DATABASE_CONFIG['database']}")
            return True

        except ConnectionFailure as e:
            logger.error(f"❌ MongoDB connection failed: {e}")
            return False
        except Exception as e:
            logger.error(f"❌ Unexpected error during connection: {e}")
            return False

    @classmethod
    async def get_database(cls):
        """Get database instance"""
        if cls._database is None:
            await cls.connect()
        return cls._database

    @classmethod
    async def get_collection(cls, collection_name):
        """Get collection"""
        db = await cls.get_database()
        return db[collection_name] if db is not None else None

    @classmethod
    async def is_connected(cls):
        """Check if connected asynchronously"""
        try:
            if cls._client is not None:
                await cls._client.admin.command('ping')
                return True
        except Exception:
            pass
        return False

    @classmethod
    async def close_connection(cls):
        """Close database connection"""
        if cls._client is not None:
            cls._client.close()
            cls._client = None
            cls._database = None
            logger.info("🔌 MongoDB connection closed")

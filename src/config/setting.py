import os
from dotenv import load_dotenv
from pymongo import MongoClient
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
    """Simple MongoDB connection manager"""

    _client = None
    _database = None

    @classmethod
    def connect(cls):
        """Connect to MongoDB"""
        try:
            if DATABASE_CONFIG["username"] and DATABASE_CONFIG["password"]:
                connection_string = f"mongodb://{DATABASE_CONFIG['username']}:{DATABASE_CONFIG['password']}@{DATABASE_CONFIG['host']}:{DATABASE_CONFIG['port']}/{DATABASE_CONFIG['database']}"
            else:
                connection_string = f"mongodb://{DATABASE_CONFIG['host']}:{DATABASE_CONFIG['port']}"

            cls._client = MongoClient(
                connection_string, serverSelectionTimeoutMS=5000)
            cls._client.admin.command('ping')
            cls._database = cls._client[DATABASE_CONFIG["database"]]

            logger.info(
                f"✅ Connected to MongoDB: {DATABASE_CONFIG['database']}")
            return True

        except ConnectionFailure as e:
            logger.error(f"❌ MongoDB connection failed: {e}")
            return False

    @classmethod
    def get_database(cls):
        """Get database instance"""
        if cls._database is None:
            cls.connect()
        return cls._database

    @classmethod
    def get_collection(cls, collection_name):
        """Get collection"""
        db = cls.get_database()
        return db[collection_name] if db is not None else None

    @classmethod
    def is_connected(cls):
        """Check if connected"""
        try:
            if cls._client is not None:
                cls._client.admin.command('ping')
                return True
        except Exception:
            pass
        return False

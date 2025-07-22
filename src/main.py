import logging
from config.setting import Database, check_connection

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)


def main():
    """Main function to test database connection."""

    db = Database
    db.connect()

    if check_connection():
        logger.info("✅ Database connection test successful!")
    else:
        logger.error("❌ Database connection test failed.")


if __name__ == "__main__":
    main()

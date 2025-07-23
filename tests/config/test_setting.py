from src.config.setting import Database
import pytest
import pytest_asyncio
from unittest.mock import patch, AsyncMock, MagicMock
from pymongo.errors import ConnectionFailure
import os
import sys

sys.path.insert(0, os.path.abspath(
    os.path.join(os.path.dirname(__file__), '..', 'src')))


@pytest.fixture
def mock_motor_client():
    with patch('src.config.setting.AsyncIOMotorClient') as mock_client:
        mock_instance = MagicMock()
        mock_client.return_value = mock_instance
        mock_instance.admin.command = AsyncMock()
        yield mock_client


@pytest.fixture
def mock_env_vars(monkeypatch):
    monkeypatch.setenv("MONGO_HOST", "localhost")
    monkeypatch.setenv("MONGO_PORT", "27017")
    monkeypatch.setenv("MONGO_DATABASE", "testdb")
    monkeypatch.setenv("MONGO_USERNAME", "")
    monkeypatch.setenv("MONGO_PASSWORD", "")


@pytest_asyncio.fixture(autouse=True)
async def cleanup_database():
    """Clean up database state after each test"""
    yield
    await Database.close_connection()
    Database._client = None
    Database._database = None


@pytest.mark.asyncio
async def test_connect_success(mock_motor_client, mock_env_vars):
    mock_motor_client.return_value.admin.command.return_value = True
    result = await Database.connect()
    assert result is True


@pytest.mark.asyncio
async def test_connect_failure_connection_error(mock_motor_client, mock_env_vars):
    mock_motor_client.return_value.admin.command.side_effect = ConnectionFailure(
        "Connection failed")
    result = await Database.connect()
    assert result is False


@pytest.mark.asyncio
async def test_connect_failure_generic_error(mock_motor_client, mock_env_vars):
    mock_motor_client.return_value.admin.command.side_effect = Exception(
        "Generic error")
    result = await Database.connect()
    assert result is False


@pytest.mark.asyncio
async def test_get_database_when_connected(mock_motor_client, mock_env_vars):
    mock_motor_client.return_value.admin.command.return_value = True
    await Database.connect()
    db_instance = await Database.get_database()
    assert db_instance is not None


@pytest.mark.asyncio
async def test_get_database_when_not_connected(mock_motor_client, mock_env_vars):
    mock_motor_client.return_value.admin.command.return_value = True
    db_instance = await Database.get_database()
    assert db_instance is not None


@pytest.mark.asyncio
async def test_get_collection(mock_motor_client, mock_env_vars):
    mock_motor_client.return_value.admin.command.return_value = True
    await Database.connect()
    collection = await Database.get_collection("test_collection")
    assert collection is not None


@pytest.mark.asyncio
async def test_get_collection_when_db_none(mock_motor_client, mock_env_vars):
    with patch.object(Database, 'connect', return_value=False):
        Database._client = None
        Database._database = None

        collection = await Database.get_collection("test_collection")
        assert collection is None


@pytest.mark.asyncio
async def test_is_connected_true(mock_motor_client, mock_env_vars):
    mock_motor_client.return_value.admin.command.return_value = True
    await Database.connect()
    result = await Database.is_connected()
    assert result is True


@pytest.mark.asyncio
async def test_is_connected_false_no_client(mock_motor_client, mock_env_vars):
    Database._client = None
    Database._database = None

    result = await Database.is_connected()
    assert result is False


@pytest.mark.asyncio
async def test_close_connection(mock_motor_client, mock_env_vars):
    mock_motor_client.return_value.admin.command.return_value = True
    await Database.connect()

    assert Database._client is not None
    assert Database._database is not None

    await Database.close_connection()

    assert Database._client is None
    assert Database._database is None


@pytest.mark.asyncio
async def test_close_connection_when_not_connected():
    await Database.close_connection()
    assert Database._client is None
    assert Database._database is None


@pytest.mark.asyncio
async def test_connection_string_with_auth(mock_motor_client):
    with patch('src.config.setting.DATABASE_CONFIG', {
        'host': 'localhost',
        'port': '27017',
        'database': 'testdb',
        'username': 'admin',
        'password': 'secret'
    }):
        mock_motor_client.return_value.admin.command.return_value = True
        await Database.connect()

        expected_connection_string = "mongodb://admin:secret@localhost:27017/testdb"
        mock_motor_client.assert_called_once_with(
            expected_connection_string,
            serverSelectionTimeoutMS=5000
        )


@pytest.mark.asyncio
async def test_connection_string_without_auth(mock_motor_client):
    with patch('src.config.setting.DATABASE_CONFIG', {
        'host': 'localhost',
        'port': '27017',
        'database': 'testdb',
        'username': '',
        'password': ''
    }):
        mock_motor_client.return_value.admin.command.return_value = True
        await Database.connect()

        expected_connection_string = "mongodb://localhost:27017"
        mock_motor_client.assert_called_once_with(
            expected_connection_string,
            serverSelectionTimeoutMS=5000
        )

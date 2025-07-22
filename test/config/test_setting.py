import pytest
from unittest.mock import patch
from pymongo.errors import ConnectionFailure
from src.config.setting import Database


@pytest.fixture
def mock_mongo_client():
    with patch('src.config.setting.MongoClient') as mock_client:
        yield mock_client


@pytest.fixture
def mock_env_vars(monkeypatch):
    monkeypatch.setenv("MONGO_HOST", "localhost")
    monkeypatch.setenv("MONGO_PORT", "27017")
    monkeypatch.setenv("MONGO_DATABASE", "testdb")
    monkeypatch.setenv("MONGO_USERNAME", "testuser")
    monkeypatch.setenv("MONGO_PASSWORD", "testpass")


def test_connect_success(mock_mongo_client, mock_env_vars):
    mock_mongo_client.return_value.admin.command.return_value = True
    assert Database.connect() is True


def test_connect_failure(mock_mongo_client, mock_env_vars):
    mock_mongo_client.return_value.admin.command.side_effect = ConnectionFailure
    assert Database.connect() is False


def test_get_database(mock_mongo_client, mock_env_vars):
    mock_mongo_client.return_value.admin.command.return_value = True
    db_instance = Database.get_database()
    assert db_instance is not None


def test_get_collection(mock_mongo_client, mock_env_vars):
    mock_mongo_client.return_value.admin.command.return_value = True
    collection = Database.get_collection("test_collection")
    assert collection is not None


def test_is_connected_true(mock_mongo_client, mock_env_vars):
    mock_mongo_client.return_value.admin.command.return_value = True
    Database.connect()
    assert Database.is_connected() is True


def test_is_connected_false(mock_mongo_client, mock_env_vars):
    mock_mongo_client.return_value.admin.command.side_effect = ConnectionFailure
    Database.connect()
    assert Database.is_connected() is False

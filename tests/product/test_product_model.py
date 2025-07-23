import pytest
from pydantic import ValidationError
from bson import ObjectId
from src.model.product import ProductModel, Unit

def test_valid_product_model():
    product = ProductModel(
        name="Susu Ultra",
        price=15000.0,
        quantity=10,
        volume=1.0,
        unit=Unit.liter,
    )
    assert product.name == "Susu Ultra"
    assert product.price == 15000.0
    assert product.quantity == 10
    assert product.volume == 1.0
    assert product.unit == Unit.liter
    assert isinstance(product.id, ObjectId)

def test_invalid_price():
    with pytest.raises(ValidationError):
        ProductModel(
            name="Produk",
            price=0,
            quantity=5,
            volume=1.0,
            unit=Unit.kilogram,
        )

def test_invalid_quantity():
    with pytest.raises(ValidationError):
        ProductModel(
            name="Produk",
            price=10000,
            quantity=-1,
            volume=1.0,
            unit=Unit.gram,
        )

def test_invalid_volume():
    with pytest.raises(ValidationError):
        ProductModel(
            name="Produk",
            price=10000,
            quantity=5,
            volume=-0.1,
            unit=Unit.mililiter,
        )

def test_invalid_unit():
    with pytest.raises(ValidationError):
        ProductModel(
            name="Produk",
            price=10000,
            quantity=5,
            volume=1.0,
            unit="pcs",
        )

def test_name_length():
    with pytest.raises(ValidationError):
        ProductModel(
            name="",
            price=10000,
            quantity=5,
            volume=1.0,
            unit=Unit.gram,
        )

def test_object_id_alias():
    custom_id = ObjectId()
    product = ProductModel(
        _id=custom_id,
        name="Produk A",
        price=5000,
        quantity=3,
        volume=0.5,
        unit=Unit.liter,
    )
    assert product.id == custom_id

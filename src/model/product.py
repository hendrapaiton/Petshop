from enum import Enum
from pydantic import BaseModel, Field
from typing import Optional
from .objectid import PyObjectId
from bson import ObjectId


class Unit(str, Enum):
    gram = "gr"
    kilogram = "kg"
    liter = "l"
    mililiter = "ml"


class ProductModel(BaseModel):
    id: Optional[PyObjectId] = Field(default_factory=PyObjectId, alias="_id")
    name: str = Field(..., min_length=1, max_length=100)
    price: float = Field(..., gt=0)
    quantity: int = Field(..., ge=0)
    volume: float = Field(..., ge=0)
    unit: Unit = Field(...)

    class ConfigDict:
        validate_by_name = True
        arbitrary_types_allowed = True
        json_encoders = {ObjectId: str}

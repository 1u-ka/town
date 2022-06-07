from pydantic import BaseModel

class Map(BaseModel):
  id: int
  width: int
  height: int
  description: str

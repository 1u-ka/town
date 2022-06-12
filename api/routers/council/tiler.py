from api.db  import db
from fastapi import APIRouter, Body

router = APIRouter(
  prefix = '/api/maps/{mid}/tiles',
  tags   = [ 'admin' ]
)

def create(mid: int, data: str = Body(...)):
  return []

router.post('/')(create)

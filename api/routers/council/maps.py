from api.db import db
from fastapi import APIRouter, Body

router = APIRouter(
  prefix = '/api/maps',
  tags   = [ 'maps' ]
)

def list():
  return db.do('select * from maps')

router.get('/')(list)

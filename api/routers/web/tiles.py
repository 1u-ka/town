from api.db  import db
from fastapi import APIRouter, Body


router = APIRouter(
  prefix = '/api/maps/{mid}/tiles',
  tags   = [ 'tiles' ]
)

def list(mid: int):
  results = db.select(['id', 'coordx', 'coordy', 'symbol', 'terrain'],
                      'select {0} from tiles t where map = %s',
                      mid)

  if 1 > len(results):
    raise HTTPException(404, 'Not found')

  return results

def show(mid: int, tid: int):
  return []

router.get('/')(list)
router.get('/{tid}/')(show)

from api.db        import db
from api.specs.map import Map
from fastapi       import APIRouter, Body, HTTPException

router = APIRouter(
  prefix = '/api/maps',
  tags   = [ 'maps' ]
)

def list():
  return db.do('select * from maps')

def show(id: int):
  results = db.select(['id', 'width', 'height', 'description'],
                      'select {0} from maps m where m.id = %s',
                      id)

  if 1 > len(results):
    raise HTTPException(404, 'Not found')

  return results[0]

router.get('/')(list)
router.get('/{id}', response_model = Map)(show)

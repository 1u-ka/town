from fastapi                 import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from fastapi.staticfiles     import StaticFiles

from api.routers.council     import spriter, tiler
from api.routers.web         import maps, tiles
from toolz                   import pipe

api = FastAPI()
api.mount('/static', StaticFiles(directory="public"), name='static')

# council / administratice
api.include_router(spriter.router)
api.include_router(tiler.router)

# web
api.include_router(maps.router)
api.include_router(tiles.router)

api.add_middleware(
    CORSMiddleware,
    allow_origins=['*']
)

def main():
  return { 'status': 'OK' }

api.get('/')(main)

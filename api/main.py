from fastapi                 import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware

from api.routers.council     import maps, tiles
from toolz                   import pipe

api = FastAPI()

api.include_router(maps.router)
api.include_router(tiles.router)

api.add_middleware(
    CORSMiddleware,
    allow_origins=['*']
)

def main():
  return { 'status': 'OK' }

api.get('/')(main)

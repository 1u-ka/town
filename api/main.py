from fastapi             import FastAPI, HTTPException
from api.routers.council import maps
from toolz               import pipe

api = FastAPI()

api.include_router(maps.router)

def main():
  return { 'status': 'OK' }

api.get('/')(main)

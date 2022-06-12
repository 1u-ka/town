import os

from fastapi import APIRouter, UploadFile

router = APIRouter(prefix='/api/sprites', tags=['admin'])


def list():
    return os.listdir('public')


def create(upload: UploadFile):
    fp = ('static/%s' % (upload.filename))
    with open(fp, 'wb+') as f:
        f.write(upload.file.read())

    return {'fp': fp, 'status': 'success'}


router.get('/')(list)
router.post('/')(create)

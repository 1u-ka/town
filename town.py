from api.main import api
import uvicorn

uvicorn.run(api,
            host='0.0.0.0',
            reload=False,
            port=1234,
            workers=1)

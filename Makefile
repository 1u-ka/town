#!make

default:
	#
up:
	docker-compose up -d
stop:
	docker-compose stop

heroku:
	heroku buildpacks:clear && \
	heroku buildpacks:add https://github.com/moneymeets/python-poetry-buildpack.git && \
	heroku buildpacks:add heroku/python
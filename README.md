# master-thesis-backend
This is where the Backend for the Master Project will be located

## A command used to create the Docker image
`docker build -f Dockerfile -t master-thesis-backend .`

## A command used to run the Docker image in the container
`docker run -p 80:80 master-thesis-backend`

## A command used to force recreate docker compose
`docker-compose up --force-recreate`

## Backend + Database without SSL
`http://193.33.111.196:8889`

## Backend + Database with SSL
`https://193.33.111.196:8888`

## SSL OFF Swagger
`http://193.33.111.196:8889/swagger-ui/#/`

## SSL ON Swagger
`https://193.33.111.196:8888/swagger-ui/#/`



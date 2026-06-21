# Local Docker Setup

This project runs locally with Docker Compose:

- PostgreSQL 16 on `localhost:5433`
- Spring Boot backend on `http://localhost:8085`
- React Vite frontend served by Nginx on `http://localhost:3000`

The frontend is built with `VITE_API_BASE_URL=/api`. Nginx proxies `/api` and `/uploads` to the backend container, so no manual frontend API URL change is needed for Docker.

## Run

From the repository root:

```bash
docker compose build
docker compose up -d
docker compose ps
```

Open:

- Frontend: `http://localhost:3000`
- Backend API: `http://localhost:8085/api`

## Validate

```bash
curl -i http://localhost:8085/api/resources

curl -i -X POST http://localhost:8085/api/resources \
  -H "Content-Type: application/json" \
  -d '{"name":"Docker Test Hall","type":"Lecture Hall","location":"Main Building","capacity":100,"description":"Created through Docker Compose","status":"Active"}'

curl -i http://localhost:8085/api/resources

docker compose logs backend --tail=100
docker compose logs frontend --tail=100
```

## Stop

```bash
docker compose down
```

To stop and delete the local PostgreSQL and upload volumes:

```bash
docker compose down -v
```

## Environment

The Compose file includes local development defaults. To override them, copy `.env.example` to `.env` and edit values locally. Do not commit `.env`.

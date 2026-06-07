docker build -t decom-db .
docker run -d --rm -p 5430:5432 --name decom-pg decom-db
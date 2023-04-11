# Create a running db instance via docker

Set up a working mysql server instance and client via docker compose.

```docker
version: '3'

services:
  db:
    image: mariadb:latest
    restart: always
    container_name: mariadb-db
    environment:
      - MARIADB_ROOT_PASSWORD=oarnud9I
    ports:
      - 3306:3306
    volumes:
      - mariadb:/var/lib/mysql
  adminer:
    image: adminer
    restart: always
    ports:
      - 8080:8080

volumes:
  mariadb:
```

## Create database tacocloud

```docker
> docker container exec -it mariadb-db bash
> mysql -u root -p
> create database tacocloud;
```

## Create a user and set permissions

```shell
CREATE USER 'tacouser'@'%' IDENTIFIED BY 'tacopassword';

GRANT ALL ON tacocloud.* TO 'tacouser'@'%';
```

run with profile `mysql`

```bash
java -jar -Dspring.profiles.active=mysql target/taco-cloud-0.0.1-SNAPSHOT.jar
```

run different profile via maven

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

## Access ingredients api protected by resource server

```bash
curl -v -H"Content-type: application/json" -d'{"id":"CCKT", "name":"Legless Crickets", "type":"PROTEIN"}' -H"Authorization: Bearer eyJraWQiOiI1NWY2ZWJmZS05MjY0LTQ3ZjQtODQ1Ni03OGNkN2JiNDcyZjYiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsImF1ZCI6InRhY28tYWRtaW4tY2xpZW50IiwibmJmIjoxNjgxMjAyNjEzLCJzY29wZSI6WyJkZWxldGVJbmdyZWRpZW50cyIsIndyaXRlSW5ncmVkaWVudHMiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAwIiwiZXhwIjoxNjgxMjAyOTEzLCJpYXQiOjE2ODEyMDI2MTN9.omorbLYqF3T8kZmT3dAYKeuqGu82_LeeMta98WALXezZg4nrqJWZovNdbJeCUgCFWmOm9Oswq2iGSu9zXnMjh8O7WivNPof-6ojTyty-LzlKFIgqEBmvnRMY-ZySLHedcpj8fQW8qxTOuy4ZhPg2raA0wQ11-2m-Pa-MNu9UmA3qwO5X68LeKTRDZhUnXBCtjxhBc_Q7uHP4ro-OTtsNNnvIjiwxaX1DjVPlKnAoz-mSkh6UfXXzBMOzmtC2wTc4L-JcAusrCcM5XMK6wMxU3rBzECBoTk4hHdNlRbOrp3-RB-sifjl1-2EPt0CvKDiIby-w8y2w3cJHXpWnupcUlA" localhost:8080/api/ingredients
```

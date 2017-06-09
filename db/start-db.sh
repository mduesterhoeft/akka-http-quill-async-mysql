#!/usr/bin/env bash
docker run --name sakila -e MYSQL_DATABASE=sakila -e MYSQL_USER=user -e MYSQL_PASSWORD=pwd -e MYSQL_ROOT_PASSWORD=pwd -p 3306:3306 -d mysql:8

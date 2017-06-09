# akka-http-quill-async-mysql

A playground project using 

- [akka-http](http://doc.akka.io/docs/akka-http/current/scala/http/index.html)
- [quill](http://getquill.io/)
- [mysql-async](https://github.com/mauricio/postgresql-async/tree/master/mysql-async)

It uses [mysql's sakila sample database](https://dev.mysql.com/doc/sakila/en/). 
The schema and data can be downloaded [here](http://downloads.mysql.com/docs/sakila-db.zip)


## Run

```bash
# run
sbt run

# test
sbt test
sbt clean coverage test coverageReport

# docker build
sbt docker:publishLocal
sbt docker:publish
```

## Use the API

### Films

#### Get a pages list of films:

```bash
http ":8080/api/films?page=3&length=25"
```

Example response:

```
HTTP/1.1 200 OK
Content-Length: 618
Content-Type: application/hal+json
Date: Fri, 09 Jun 2017 06:50:44 GMT
Server: akka-http/10.0.3

{
    "_embedded": {
        "films": [
            {
                "category": "Horror", 
                "description": "A Thoughtful Panorama of a Database Administrator And a Mad Scientist who must Outgun a Mad Scientist in A Jet Boat", 
                "id": 9, 
                "releaseYear": 2006, 
                "rentalRate": 2.99, 
                "title": "ALABAMA DEVIL"
            }, 
            {
                "category": "Sports", 
                "description": "A Action-Packed Tale of a Man And a Lumberjack who must Reach a Feminist in Ancient China", 
                "id": 10, 
                "releaseYear": 2006, 
                "rentalRate": 4.99, 
                "title": "ALADDIN CALENDAR"
            }
        ]
    }, 
    "page": {
        "length": 2, 
        "number": 3
    }
}
```





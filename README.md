# URL Shortener

## Tech stack
- [Scala](https://www.scala-lang.org/)
- [Play Framework](https://www.playframework.com/)
- [Redis](https://github.com/antirez/redis)
- [ScalaTest](http://www.scalatest.org/)
- [Mockito](https://github.com/mockito/mockito)
- [Docker](https://www.docker.com/)


## Commands

### Run
Run `docker-compose`, it will start `api`, `redis` and will expose api port to host.

```sh
docker-compose up
```

### Test
```sh
sbt test
```

## Sample usage

```sh
# Shorten
curl -X POST http://localhost:9000/url=http%3A%2F%2Fyaho2o.com

# Call shortened url
curl -X GET http://localhost:9000/igDpdNx
```

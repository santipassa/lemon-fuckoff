# lemon-fuckoff

Project for lemon interview

## This project is using:

![java 11](https://img.shields.io/badge/java-11-orange.svg)
![springboot 2.6.3](https://img.shields.io/badge/springboot-2.5.6-272822.svg)

## Documentation

[![spects](https://img.shields.io/badge/specs-%E2%9D%A4-green.svg)](http://localhost:8080/swagger-ui.html)

## Running it with Docker locally:

1. Build fat jar file

```bash 
$ mvn clean compile install 
```

2. Build image:

```bash 
$ docker build -t "lemon-fuckoff" . 
```

3. Check the image:

> This is an optional step, you can skip this if you want.

``` bash 
$ docker images 
```

4. Run the container:

``` bash 
$ docker run --name lemon-fuckoff -p 8080:8080 lemon-fuckoff:latest 
```

## Running it without Docker:

```bash 
$ mvn spring-boot:run 
```

## Testing:

```bash 
$ mvn test 
```

## How to use:

### Get message from FOAAS API:

``` bash
curl --location --request GET 'localhost:8080/message' \
--header 'X-User-Id: 1234'
```

> Tip 1: The header X-User-Id must be 1234 if you don't send this you will receive a 401 status code.

> Tip 2: You only can use this service 5 times in 10 seconds time window. If you exceed this quota you will receive 429 status code.



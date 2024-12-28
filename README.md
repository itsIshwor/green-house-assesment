
# Greenhouse Assessment API

Project to process the receipt.

## Author

- [@Ishwor Upreti](https://github.com/itsIshwor)

## Prerequisites
* [Docker](https://www.docker.com/)
* [POSTMAN](https://www.postman.com/)


## Run Locally

Clone the project

```bash
  git clone https://github.com/itsIshwor/green-house-assesment.git
```

Go to the project directory

```bash
  cd green-house-assesment
```
Give execution permission to `start.sh` file

```bash
  chmod +x start.sh
```

Execute the start script
```bash
 ./start.sh
```
once all above steps complete you will able to see following in browser:
![Alt text](/imgs/homepage.png?raw=true "Optional Title")


once you're able to  lunch `localhost:8080` import `greenhouse-assessment.postman_collection.json` into your postman.
and Run the collection. You will be able to see following result:
 ![Alt text](/imgs/postmanresult.png?raw=true "Optional Title")      

### Limitations:
 - Add unit test cases with junit mockito.

## BUILD WITH
* [Maven](https://maven.apache.org/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.1/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.1/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [lombok](https://projectlombok.org/)


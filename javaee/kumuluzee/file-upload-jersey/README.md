# File upload in Jersey (JAX-RS)


## Run 
To run project run command `mvn clean package` and then `java -jar target/test-file-upload-1.0.0.jar`.

Server endpoint will be available at `http://localhost:8080/files`.

## Notes
Due to Jersey we must use ResourceConfig class rather than Application class. All Resources must be registered in constructor method.
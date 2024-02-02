Runs on java 17
./gradlew bootJar(packaging as a jar file)
java -jar build/libs/catalog-service-0.0.1-SNAPSHOT.jar -: run the jar file(saved by default at build/libs)

./gradlew build -: to build the project
grype . -: to check security vulnerability
./gradlew test --tests CatalogServiceApplicationTests(test class name)  -: to run tests on terminal

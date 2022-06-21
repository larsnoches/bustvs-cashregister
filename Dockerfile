FROM bellsoft/liberica-openjdk-alpine:17
COPY /src/target/cashregister-*.jar cashregister.jar
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "cashregister.jar"]
# quarkus-with-pdf-generation Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

<p>
<details>
<summary>Expandir la información</summary>

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

</details>
</p> 
<p><br></p>

## Packaging and running the application

<p>
<details>
<summary>Expandir la información</summary>

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

</details>
</p> 
<p><br></p>

## Creating a native executable

<p>
<details>
<summary>Expandir la información</summary>

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-with-pdf-generation-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

</details>
</p> 
<p><br></p>

# Functionality

<p>
<details>
<summary>Expandir la información</summary>
This project is used to explore the capabilities of quarkus in conjuction with the open source pdfbox libraries.

#### Endpoints
This project exposes an endpoint that receives a CloudEvent:

| Verb | URI | Function |
| POST | /document/pdf |Create the PDF document using the data of the CloudEvent|
|---|---|---|
</details>
</p> 
<p><br></p>

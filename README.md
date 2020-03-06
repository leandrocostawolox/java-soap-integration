# Java SOAP integration

Java SOAP integration to an external calculator service using Spring Boot and JAXB2 plugin.

To generate the classes for the WSDL specified in the pom.xml file, you only need to compile the project (./mvnw compile). 
Then you will find the generated classes in target/generated-sources if you want to check that it really works.  

This project provides RAML specification that you can find in src/main/resources/raml. 
Besides the api.raml file there is an api_spec.html file which shows the documentation.
This file was generated using [raml2html](https://github.com/raml2html/raml2html).
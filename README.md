#Keycloak User Sample

##Description

This is an example project using Keycloak UserFederationProvider SPI implementation with a legacy REST application, 
which is responsible for the the authentication.

This project is based on the article [Migrate to Keycloak with Zero Downtime](https://tech.smartling.com/migrate-to-keycloak-with-zero-downtime-8dcab9e7cb2c#.stu560wee)


##Requirements

1. JDK 8
2. Keycloak Server 1.9.0+

##Installation

1) Run mvn package


    mvn clean package
    
2) Deploy the module

          
module add --name=sp.rafael.keycloak.federation-impl --dependencies=org.keycloak.keycloak-core,org.keycloak.keycloak-server-spi,org.jboss.logging,javax.persistence.api,org.jboss.resteasy.resteasy-jaxrs,org.apache.httpcomponents,javax.ws.rs.api --resource-delimiter=, --resources=/home/rafael/dev/estudos/keycloak/keycloak-user-sample/federation-impl/target/federation-impl.jar,/home/rafael/dev/estudos/keycloak/keycloak-user-sample/operador-model/target/operador-model.jar 

     
3)


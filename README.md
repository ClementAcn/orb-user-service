# Microservice permettant la gestion des utilisateurs
## Présentation générale
Ce microservice appartient à l'écosystème orb qui est en tout composé de 3 microservices.
Ces trois services sont : 
* Authentification des utilisateurs
* Gestion des notes
* UI

## Présentation de ce service
Ce service permet la gestion des utilisateurs. Que ce soit l'ajout, la suppression, la mise à jour ou la visualisation d'un ou des avis.


## Pré-requis
* [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven 4](https://maven.apache.org/)

## Compilation de l'application
``
mvn clean install
``

## Lancement de l'application
``
java -jar target/score-service-0.0.1-SNAPSHOT.jar
``

## Documentation de l'API
Actuellement ce service tourne sur le port 9090. <br/>
La documentation sera donc accessible via cette url : <br/>
http://localhost:9090/swagger-ui.html

## Contributeurs
* Matthieu Meaux
* Cédric Martin
* Samuel Dubernet
* Clément Aucoin
 
# ActiveMQ_ESDE_FK

Prequisites:

1. Intellij IDEA (preferably Ultimate edition - its free as a student https://www.jetbrains.com/community/education/#students)
2. Java 8 (this version is specifically is needed for JavaFX else you can have java 11 but make sure to install java fx - https://blog.idrsolutions.com/2019/05/using-javafx-with-java-11/)
3. Download ActiveMQ for your operating system (you won't need it to run it) - 5.16.3 - https://activemq.apache.org/components/classic/download/
4. Docker installed


Docker image of activemq can be pulled using the following command:
```
docker pull jof34/dummy-active-mq
```
Run the container using the following command:
```
docker run --name=activemq -p 8161:8161 -p 5672:5672 -p 61613:61613 -p 61614:61614 -p 61616:61616 -p 1883:1883  jof34/dummy-active-mq
```

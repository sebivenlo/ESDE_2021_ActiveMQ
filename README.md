# ActiveMQ_ESDE_FK

Prequisites:

1. A Java IDEA (we prefer Intellij IDE - Ultimate edition - its free as a student https://www.jetbrains.com/community/education/#students)
2. How to set java jdk in Intellij: https://www.jetbrains.com/help/idea/sdk.html#change-project-sdk
3. Java 8 (this version is specifically is needed for JavaFX else you can have java 11 but make sure to install java fx sdk - https://stackoverflow.com/questions/52682195/how-to-get-javafx-and-java-11-working-in-intellij-idea)
4. Download ActiveMQ for your operating system (you won't need it to run it a docker image will be provided to you) - 5.16.3 - https://activemq.apache.org/components/classic/download/
5. Docker installed

6. The git repo clone url is https://github.com/sebivenlo/ESDE_2021_ActiveMQ.git


Docker image of activemq that is going to be used for demonstration can be pulled using the following command:
```
docker pull jof34/dummy-active-mq
```
Run the container using the following command:
```
docker run --name=activemq -p 8161:8161 -p 5672:5672 -p 61613:61613 -p 61614:61614 -p 61616:61616 -p 1883:1883  jof34/dummy-active-mq
```

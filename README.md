# Kafka Demo

## Build & run
check the [TOOLS INSTALLATION](doc/TOOLS.md)
### local run
```shell script
./mvnw spring-boot:run
```
## run on K8s
- start minikube
```shell script
minikube start
eval $(minikube docker-env)
echo "minikube ip = `minikube ip`"
```
- deploy
```shell script
skaffold dev --trigger notify
```

## Test
```shell script
curl -s -H "Host: demo-kafka.local"  http://192.168.99.100/messages/stream 
```

## Documentation

Spring Cloud Stream
* [Spring for Apache Kafka Deep Dive – Part 2: Apache Kafka and Spring Cloud Stream](https://www.confluent.io/blog/spring-for-apache-kafka-deep-dive-part-2-apache-kafka-spring-cloud-stream/)
* [Stream Processing with Spring Cloud Stream and Apache Kafka Streams.](https://spring.io/blog/2019/12/04/stream-processing-with-spring-cloud-stream-and-apache-kafka-streams-part-3-data-deserialization-and-serialization)
* [Introduction to Spring Cloud Stream](https://www.baeldung.com/spring-cloud-stream)
* [spring-cloud-stream-binder-kafka](https://cloud.spring.io/spring-cloud-stream-binder-kafka/spring-cloud-stream-binder-kafka.html#_multiple_input_bindings)
* [angular-spring-boot-kafka](https://medium.com/swlh/angular-spring-boot-kafka-how-to-stream-realtime-data-the-reactive-way-510a0f1e5881)

## original 
[Spring initializr](https://start.spring.io/#!type=gradle-project&language=kotlin&platformVersion=2.3.1.RELEASE&packaging=jar&jvmVersion=11&groupId=com.github.scalvet&artifactId=demo-kafka&name=demo-kafka&description=Demo%20project%20for%20Spring%20Boot&packageName=com.github.scalvet.demo-kafka&dependencies=actuator,web,validation,cloud-stream,kafka-streams)

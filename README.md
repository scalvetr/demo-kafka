# Kafka Demo
## original 
[Spring initializr](https://start.spring.io/#!type=gradle-project&language=kotlin&platformVersion=2.3.1.RELEASE&packaging=jar&jvmVersion=11&groupId=com.github.scalvet&artifactId=demo-kafka&name=demo-kafka&description=Demo%20project%20for%20Spring%20Boot&packageName=com.github.scalvet.demo-kafka&dependencies=actuator,web,validation,cloud-stream,kafka-streams)

## Build & run

### local run
```shell script
./mvnw spring-boot:run
```

## run on K8s
- install skaffold
```shell script
curl -Lo skaffold https://storage.googleapis.com/skaffold/releases/latest/skaffold-linux-amd64
sudo install skaffold /usr/local/bin/
```
- install kustomize
```shell script
curl -s "https://raw.githubusercontent.com/\
kubernetes-sigs/kustomize/master/hack/install_kustomize.sh"  | bash
sudo chown root:root kustomize
sudo mv kustomize /usr/local/bin/
```
- update config
```shell script
kubectl create configmap demo-actuator-config --from-file=src/main/resources/application.yml -o yaml --dry-run > src/main/k8s/demo-actuator-config-map.yml
```
- install minikube
```shell script

curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 \
&& chmod +x minikube
sudo mkdir -p /usr/local/bin/
sudo install minikube /usr/local/bin/
# TODO test docker driver
minikube start \
    --driver=virtualbox \
    --network-plugin=cni \
    --enable-default-cni \
    --bootstrapper=kubeadm

minikube addons enable ingress
minikube stop
```

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
Check `actuator-requests.http` file for sample requests.

In case you are running on minikube, execute `minikube ip` and update `http-client.env.json` accordingly.

## Features
* CustomCheckHealthIndicator
* CustomEndpoint

Turn the custom health indicator to unhealthy 
```shell script
baseUrl="http://192.168.99.100"
host="actuator.demo.local"

curl -v -X GET ${baseUrl}/actuator/health \
-H "Host: ${host}" \
-H "Accept: application/json" | jq

curl -v -X GET ${baseUrl}/actuator/health/liveness \
-H "Host: ${host}" \
-H "Accept: application/json" | jq

kubectl get pods
kubectl get pods -l app=demo-actuator -o json | jq

curl -v ${baseUrl}/actuator/custom \
-H "Host: ${host}" \
-H "Content-Type: application/json" \
--data-binary @- << EOF
{
  "healthy": false
}
EOF

kubectl get pods -l app=demo-actuator
#kubectl -n default get events --field-selector involvedObject.kind=Pod -l app=demo-actuator
kubectl get event --field-selector involvedObject.kind=Pod

```

check responses for the different endpoints
```shell script
### GET request to /actuator/health
GET {{host}}/actuator/health
Accept: application/json

### GET request to /actuator/health/readiness
GET {{host}}/actuator/health/readiness
Accept: application/json

### GET request to /actuator/health/liveness
GET {{host}}/actuator/health/liveness
Accept: application/json
```


## Memory configuration
The different memory and CPU limits and optimizations can be done in `src/main/k8s/deployment.yml`

`paketo-buildpacks/bellsoft-liberica:memory-calculator` is responsible to automatically  calculate JVM memory settings
adjusting them to container's limits.

### Limit CPU and memory - no JVM arguments
```yaml
          resources:
            requests:
              cpu: "0.3"
              memory: "128m"
            limits:
              cpu: "0.5"
              memory: "256m"
          env:
            - name: spring.config.location
              value: classpath:application.yml,file:/app/config/application.yml
```

### Limit CPU and memory - JVM arguments
```yaml
          resources:
            requests:
              cpu: "0.3"
              memory: "128m"
            limits:
              cpu: "0.5"
              memory: "256m"
          env:
            - name: spring.config.location
              value: classpath:application.yml,file:/app/config/application.yml
            - name: BPL_JVM_HEAD_ROOM
              value: "2"
            - name: BPL_JVM_LOADED_CLASS_COUNT
              value: "35"
            - name: BPL_JVM_THREAD_COUNT
              value: "10"
            - name: JAVA_OPTS
              value: >-
                    -XX:ReservedCodeCacheSize=40M
                    -XX:MaxMetaspaceSize=60M
                    -Xlog:gc
                    -Xms34m
                    -Xmx40m
                    -Xss256k
                    -XX:MaxRAM=150M
```
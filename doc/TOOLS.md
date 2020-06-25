# Tools installation

## install skaffold
```shell script
curl -Lo skaffold https://storage.googleapis.com/skaffold/releases/latest/skaffold-linux-amd64
sudo install skaffold /usr/local/bin/
```
## install kustomize
```shell script
curl -s "https://raw.githubusercontent.com/\
kubernetes-sigs/kustomize/master/hack/install_kustomize.sh"  | bash
sudo chown root:root kustomize
sudo mv kustomize /usr/local/bin/
```
## install minikube
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

## start minikube
```shell script
minikube start
eval $(minikube docker-env)
echo "minikube ip = `minikube ip`"
```
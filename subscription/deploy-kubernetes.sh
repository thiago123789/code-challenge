#!/bin/zsh

echo "apply kubernetes secrets"
kubectl apply -f ./kubernetes/secrets.yaml

echo "apply mysql deployment"
kubectl apply -f ./kubernetes/mysql-deployment.yaml

echo "apply mysql service"
kubectl apply -f ./kubernetes/mysql-service.yaml
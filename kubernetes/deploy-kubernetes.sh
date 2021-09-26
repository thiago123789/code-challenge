#!/bin/zsh

echo "deleting previous version"
kubectl delete secrets subscription-secrets
kubectl delete svc mysql-subscription-svc
kubectl delete deployment mysql-subscription-deploy
kubectl delete svc subscription-svc
kubectl delete deployment subscription-deployment
kubectl delete svc bff-svc
kubectl delete deployment bff-deployment

echo "apply kubernetes secrets"
kubectl apply -f ./secrets.yaml

echo "apply mysql deployment"
kubectl apply -f ./mysql-deployment.yaml

echo "apply mysql service"
kubectl apply -f ./mysql-service.yaml

echo "apply backend deployment"
kubectl apply -f ./backend.yaml

echo "apply backend service"
kubectl apply -f ./backend-svc.yaml

echo "apply bff deployment"
kubectl apply -f ./bff-deployment.yaml

echo "apply bff service"
kubectl apply -f ./bff-svc.yaml

echo "apply email deployment"
kubectl apply -f ./email-deployment.yaml

echo "apply email svc"
kubectl apply -f ./email-svc.yaml
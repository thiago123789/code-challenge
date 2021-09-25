#!/bin/zsh
kubectl apply -f admin-user.yaml

kubectl apply -f role-binding.yaml

kubectl -n kubernetes-dashboard get secret $(kubectl -n kubernetes-dashboard get sa/admin-user -o jsonpath="{.secrets[0].name}") -o go-template="{{.data.token | base64decode}}" | clip
# Code Challenge

## Requirements

This project was executed using the services with the specific version listed bellow (on Windows 10 Pro):

- Docker 20.10.8
- Minikube 1.23.2
- Java 8

## How to run ?

### Login docker hub

With the docker installed and running, execute:

> docker login

Inform your [docker hub](http://hub.docker.com) credentials and press enter.

This credentials will be used by docker in the future to upload the images to your docker hub account.

### Building services containers

Run the ```run.sh``` script inside of all service folders passing the ```-n [USER_FROM_DOCKER_HUB]```

> run.sh -n thiago123789

like this:

- Email service
> cd email 

> ./run.sh -n thiago123789

- Bff service
> cd bff

> ./run.sh -n thiago123789

- Subscription service
> cd subscription

> ./run.sh -n thiago123789

### Creating kubernetes infrascruture

First of all we need to start the minikube

> minikube start

After this, go to kubernetes folder and run the ```deploy-kubernetes.sh```

> ./deploy-kubernetes.sh

### Testing load balancer locally

To test load balancer locally you need to use minikube tunnel running:

> minikube tunnel

After this run:

> kubectl get svc

The result will be something like this: 

![image](https://user-images.githubusercontent.com/11702372/134815250-851a3faf-5619-4b8f-b2e0-e8412fc2a9b8.png)

Now you need to use the external-ip to open the bff's swagger (on windows you can use localhost instead of 127.0.0.1)

So put on browser the following address:

> http://localhost:8090/swagger-ui/index.htm

The swagger ui should appear with the endpoints available on bff service

## Architecture

![arch-1](https://user-images.githubusercontent.com/11702372/134814297-f234a8d6-a29e-45a7-9e9d-91970c4b6279.png)

The image above show the arachitecture and how the services communicate between them.

All the operations from the BFF to the Subscription service that will cause database change will be made using the RabbitMQ service


## Improvements points
- add some cache service on bff get endpoints (using something like Redis)
- improve the logging
- add some service to help with tracing 

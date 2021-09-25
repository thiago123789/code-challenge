#!/bin/zsh
user=""
while [ $# != "" ]; do
    case "$1" in
      -n)
        shift
         user=$1
        shift
        ;;
      -h)
        shift
          print "use -n to specify docker hub repository name\n-n [docker hub user]"
          exit
        shift
        ;;
      *)
        break
        ;;
    esac
done

echo "Packaging app into jar file"
./mvnw clean package

echo "build container"
docker build --tag=subscription:latest .

if [ -n "$user" ];
then
echo "pushing to docker hub"
docker push "${user}"/subscription:latest
fi

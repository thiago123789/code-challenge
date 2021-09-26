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

./bff/run.sh -n "${user}"

./email/run.sh -n "${user}"

./subscription/run.sh -n "${user}"

./kubernetes/deploy-kubernetes.sh



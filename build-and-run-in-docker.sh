#!/bin/bash

HOME=`dirname $0`
cd $HOME

bin/activator playUpdateSecret
bin/activator dist

docker build --tag alexanderfefelov/area-451 .
docker run --name area-451 --detach --tty \
  --restart always \
  --publish 80:9000 \
  --volume /etc/localtime:/etc/localtime:ro \
  --volume /etc/timezone:/etc/timezone:ro \
  alexanderfefelov/area-451

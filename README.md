# area-451

## Docker

    cd /tmp
    git clone https://github.com/alexanderfefelov/area-451.git
    bin/activator docker:publishLocal 
    docker run --name area-451 --detach --tty \
      --restart always \
      --publish 80:9000 \
      --volume /etc/localtime:/etc/localtime:ro \
      --volume /etc/timezone:/etc/timezone:ro \
      alexanderfefelov/area-451:1.0-SNAPSHOT

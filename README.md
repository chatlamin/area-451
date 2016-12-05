# area-451

## Docker

    cd /tmp
    git clone https://github.com/alexanderfefelov/area-451.git
    cd area-451
    bin/activator docker:publishLocal
    docker run --name area-451 --detach --tty \
      --restart always \
      --publish 80:9000 \
      --volume /etc/localtime:/etc/localtime:ro \
      --volume /etc/timezone:/etc/timezone:ro \
      alexanderfefelov/area-451:1.0-SNAPSHOT

## Debian

    cd /tmp
    git clone https://github.com/alexanderfefelov/area-451.git
    cd area-451
    sbt debian:packageBin

## RPM

    cd /tmp
    git clone https://github.com/alexanderfefelov/area-451.git
    cd area-451
    sbt rpm:packageBin

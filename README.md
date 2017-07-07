# area-451

area-451 -- это сервер, показывающий клиенту страницу, информирующую, что доступ
к запрошенному клиентом ресурсу запрещён решением органов государственной власти РФ.
Предполагается, что переадресация клиента на эту страницу производится DPI-системой провайдера.

Страница отдаётся с HTTP-статусом `451`:

![Страница 451](https://raw.githubusercontent.com/alexanderfefelov/area-451/master/doc/page451.png)

Сервер способен отсылать метрики в [Graphite](http://graphite.readthedocs.io/).
Вот так эти метрики выглядят в [Grafana](http://graphite.readthedocs.io/en/latest/):

![Метрики](https://raw.githubusercontent.com/alexanderfefelov/area-451/master/doc/metrics.png)

## Docker

    cd /tmp
    git clone https://github.com/alexanderfefelov/area-451.git
    cd area-451
    vi conf/graphite.conf
    sbt docker:publishLocal
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

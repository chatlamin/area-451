FROM openjdk:8-jre

ENV APP=area-451
ENV VERSION=1.0-SNAPSHOT

ADD target/universal/$APP-$VERSION.zip /

RUN unzip -qq $APP-$VERSION.zip \
  && rm $APP-$VERSION.zip \
  && mv $APP-$VERSION $APP

EXPOSE 9000

VOLUME /$APP/conf
VOLUME /$APP/public
VOLUME /$APP/logs

WORKDIR /$APP

ENTRYPOINT bin/$APP

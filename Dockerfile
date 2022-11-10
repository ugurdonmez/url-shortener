#FROM adoptopenjdk/openjdk11
#
#ENTRYPOINT ["java" ,"-jar", "/app/app.jar"]
#
#ADD ./target/scala-2.13/short-url_2.13-1.0-SNAPSHOT.jar /app/app.jar

FROM openjdk:11.0.11

ARG SBT_VERSION=1.7.2

# Install sbt
RUN \
  mkdir /working/ && \
  cd /working/ && \
  curl -L -o sbt-$SBT_VERSION.deb https://repo.scala-sbt.org/scalasbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb && \
  apt-get update && \
  apt-get install sbt && \
  cd && \
  rm -r /working/ && \
  sbt sbtVersion

RUN mkdir -p /root/build/project
ADD build.sbt /root/build/
ADD ./project/plugins.sbt /root/build/project
RUN cd /root/build && sbt compile

EXPOSE 9000
WORKDIR /root/build

CMD sbt compile run

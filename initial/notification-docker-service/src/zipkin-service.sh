#!/bin/bash

SERVICE_PORT=8090
CONTROL_PORT=8091
IMAGE_NAME="openzipkin/zipkin"
CONTAINER_NAME="com.techbow.notification.zipkin"

function init() {
  imageID=$(docker images -q ${IMAGE_NAME})
  if [ ! -z "${imageID}" ]; then
    docker pull ${IMAGE_NAME}
  fi
}

function start() {
  docker run -d -p ${CONTROL_PORT}:9411 \
          --name ${CONTAINER_NAME} ${IMAGE_NAME}
}

function stop() {
  containerID=$(docker ps -q -f name=${CONTAINER_NAME})
  if [ ! -z "${containerID}" ]; then
    docker stop ${containerID}
    docker rm ${containerID}
  fi
}

command=$1

if [ "${command}" == "init" ]; then
  init
elif [ "${command}" == "start" ]; then
  init
  start
elif [ "${command}" == "stop" ]; then
  stop
elif [ "${command}" == "restart" ]; then
  init
  stop
  start
fi
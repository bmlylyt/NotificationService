#!/bin/bash

SERVICE_PORT=8085
CONTROL_PORT=8086
IMAGE_NAME="rabbitmq"
RUNNING_IMAGE_NAME="rabbitmq:3-management"
CONTAINER_NAME="com.techbow.notification.rabbitmq"

function init() {
  imageID=$(docker images -q ${IMAGE_NAME})
  if [ ! -z "${imageID}" ]; then
    docker pull ${IMAGE_NAME}
  fi
}

function start() {
  docker run -d -p ${SERVICE_PORT}:5672 \
          -p ${CONTROL_PORT}:15672 \
          --name ${CONTAINER_NAME} ${RUNNING_IMAGE_NAME}
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
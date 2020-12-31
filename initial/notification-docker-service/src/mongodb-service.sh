#!/bin/bash

SERVICE_PORT=8083
IMAGE_NAME="mongo"
CONTAINER_NAME="com.techbow.notification.mongodb"

function init() {
  imageID=$(docker images -q ${IMAGE_NAME})
  if [ ! -z "${imageID}" ]; then
    docker pull ${IMAGE_NAME}
  fi
}

function start() {
  docker run -d -p ${SERVICE_PORT}:27017 \
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
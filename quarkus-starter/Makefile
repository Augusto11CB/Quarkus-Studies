SHELL := /bin/bash

PROJECT_NAME=$(shell ./gradlew properties -q | grep "projectName:" | awk '{print $$2;}')
APP_VERSION=$(shell ./gradlew properties -q | grep "^version:" | awk '{print $$2;}')
DOCKER_REPO=$(shell ./gradlew properties -q | grep "dockerImageRepository:" | awk '{print $$2;}')
DOCKER_REGISTRY=$(shell ./gradlew properties -q | grep "dockerRegistry:" | awk '{print $$2;}')

DATETIME=$(shell date +"%d%m%Y%H%M")

GRADLE=./gradlew -i

build:
	@echo " -- Building project --"
	@${GRADLE} clean test build --no-daemon

run:
	@echo " -- Running project --"
	@${GRADLE} --console=plain quarkusDev

debug:
	@echo " -- Debugging project --"
	@${GRADLE} --console=plain quarkusDev --debug

format:
	@echo " -- Formatting project --"
	@${GRADLE} ktlintFormat

docker-release-dev:
	@${GRADLE} clean test build --no-daemon
	@docker build -t ${PROJECT_NAME}:${APP_VERSION}-build-${DATETIME} .


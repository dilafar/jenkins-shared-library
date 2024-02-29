#!usr/bin/env groovy

def call(){
    echo "build version ${NEW_VERSION}..."
    sh "mvn clean package"
}
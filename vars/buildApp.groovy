#!usr/bin/env groovy

def call(){
    echo "build version ${NEW_VERSION}..."
    echo "build application for $BRANCH_NAME"
    sh "mvn install"
}
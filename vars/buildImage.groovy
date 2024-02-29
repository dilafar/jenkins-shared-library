#!usr/bin/env groovy

def call(){
    echo"build docker image..."
    withCredentials([
            usernamePassword(credentialsId:'docker-credentials',usernameVariable:'USER',passwordVariable:'PASS')
    ]){
        sh "docker build -t fadhiljr/mssample:java-maven-2.0 ."
        sh "echo ${PASS} | docker login -u ${USER} --password-stdin"
        sh "docker push fadhiljr/mssample:java-maven-2.0"

    }
}
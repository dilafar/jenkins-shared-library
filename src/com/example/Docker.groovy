package com.example

class Docker implements Serializable{

    def script

    Docker(script){
        this.script = script
    }

    def buildDockerImage(String image){
        script.sh "docker build -t ${image} ."
    }
    def dockerLogin(){
        script.withCredentials([
                script.usernamePassword(credentialsId:'docker-credentials',usernameVariable:'USER',passwordVariable:'PASS')
        ]){
            script.sh "echo ${script.PASS} | docker login -u ${script.USER} --password-stdin"

        }
    }
    def pushDockerImage(String image){
        script.sh "docker push ${image}"
    }
}

package com.example

class Docker implements Serializable{

    def script

    Docker(script){
        this.script = script
    }

    def buildDockerImage(String image,String imageVersion){
        script.sh "docker build -t ${image}-v${imageVersion} ."
    }

    def buildCloudDockerImage(String image,String imageVersion){
        script.sh "docker build -t ${image}:${imageVersion} ."
    }

    def dockerLogin(){
        script.withCredentials([
                script.usernamePassword(credentialsId: 'docker', passwordVariable: 'PASS', usernameVariable: 'USER')
        ]){
            script.sh "echo ${script.PASS} | docker login -u ${script.USER} --password-stdin"

        }
    }

    def dockerLoginPrivateCloud(String repoUrl){
        script.withCredentials([
                script.usernamePassword(credentialsId: 'ecr-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')
        ]){
            script.sh "echo ${script.PASS} | docker login -u ${script.USER} --password-stdin ${repoUrl}"

        }
    }

    def dockerLoginPrivateCloudAzure(String repoUrl){
        script.withCredentials([
                script.usernamePassword(credentialsId: 'ecr-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')
        ]){
            script.sh "echo ${script.PASS} | docker login -u ${script.USER} --password-stdin ${repoUrl}"

        }
    }

    def pushDockerImage(String image,String imageVersion){
        script.sh "docker push ${image}-v${imageVersion}"
    }

    def pushCloudDockerImage(String image,String imageVersion){
        script.sh "docker push ${image}:${imageVersion}"
    }

    def signImage(String image,String imageVersion , String COSIGN_PRIVATE_KEY,String COSIGN_PUBLIC_KEY){
        def IMAGE_DIGEST = script.sh(script: "docker inspect --format='{{index .RepoDigests 0}}' ${image}-v${imageVersion}", returnStdout: true).trim()
        script.echo "Image Digest: ${IMAGE_DIGEST}"
        script.sh "echo 'y' | cosign sign --key ${COSIGN_PRIVATE_KEY} ${IMAGE_DIGEST}"
        script.sh "cosign verify --key ${COSIGN_PUBLIC_KEY} ${IMAGE_DIGEST}"
    }

    def signCloudImage(String image,String imageVersion , String COSIGN_PRIVATE_KEY,String COSIGN_PUBLIC_KEY){
        def IMAGE_DIGEST = script.sh(script: "docker inspect --format='{{index .RepoDigests 0}}' ${image}-v${imageVersion}", returnStdout: true).trim()
        script.echo "Image Digest: ${IMAGE_DIGEST}"
        script.sh "export COSIGN_TLOG_UPLOAD=false"
        script.sh "cosign sign --key ${COSIGN_PRIVATE_KEY} ${IMAGE_DIGEST}"
        script.sh "cosign verify --key ${COSIGN_PUBLIC_KEY} --private-infrastructure=true ${IMAGE_DIGEST}"
    }
}
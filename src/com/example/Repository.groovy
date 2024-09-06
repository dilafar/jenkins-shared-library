package com.example

class Repository implements Serializable{
    def script

    Repository(script){
        this.script = script
    }

    def nexusArtifactUpload(String version){
        script.nexusArtifactUploader(
                nexusVersion: 'nexus3',
                protocol: 'http',
                nexusUrl: '172.16.12.32:8080',
                groupId: 'QA',
                version: "${script.BUILD_ID}-${script.BUILD_TIMESTAMP}",
                repository: 'bank-microservice',
                credentialsId: 'nexuslogin',
                artifacts: [
                        [artifactId: microserviceapp,
                         classifier: '',
                         file: 'accounts' + version + '.jar',
                         type: 'jar']
                ]
        )
    }
}

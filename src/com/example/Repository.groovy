package com.example

class Repository implements Serializable{
    def script

    Repository(script){
        this.script = script
    }

    def uploadArtifactToNexus(String nexusUrl, String groupId, String artifactId, String version, String repository, String credentialsId, String artifactFile, String artifactType) {
        script.echo "Uploading artifact to Nexus: ${artifactFile}"
        script.nexusArtifactUploader(
                nexusVersion: 'nexus3',
                protocol: 'http',
                nexusUrl: nexusUrl,
                groupId: groupId,
                version: version,
                repository: repository,
                credentialsId: credentialsId,
                artifacts: [
                        [artifactId: artifactId,
                         classifier: '',
                         file: artifactFile,
                         type: artifactType]
                ]
        )
    }
}

#!usr/bin/env groovy
import com.example.Repository


def call(String nexusUrl, String groupId, String artifactId, String version, String repository, String credentialsId, String artifactFile, String artifactType){
    return new Repository(this).uploadArtifactToNexus(nexusUrl,groupId,artifactId,version,repository,credentialsId,artifactFile,artifactType)
}
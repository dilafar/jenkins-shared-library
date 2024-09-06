#!usr/bin/env groovy
import com.example.Repository


def call(String version){
    return new Repository(this).nexusArtifactUpload(version);
}
#!usr/bin/env groovy
import com.example.Docker

def call(String repoUrl){
    return new Docker(this).dockerLoginPrivateCloud(repoUrl)
}
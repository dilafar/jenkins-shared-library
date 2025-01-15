#!usr/bin/env groovy
import com.example.Docker

def call(String imageName,String imageVersion){
    return new Docker(this).pushDockerImage(imageName,imageVersion)
}
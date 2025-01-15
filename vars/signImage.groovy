#!usr/bin/env groovy
import com.example.Docker

def call(String imageName, String imageVersion, String COSIGN_PRIVATE_KEY, String COSIGN_PUBLIC_KEY){
    return new Docker(this).signImage(imageName,imageVersion,COSIGN_PRIVATE_KEY,COSIGN_PUBLIC_KEY)
}
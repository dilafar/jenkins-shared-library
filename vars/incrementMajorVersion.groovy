#!usr/bin/env groovy
import com.example.Version


def call(){
    return new Version(this).incrementMajorVersion()
}
#!usr/bin/env groovy
import com.example.Test


def call(){
    return new Test(this).checkStyleAnalysis();
}
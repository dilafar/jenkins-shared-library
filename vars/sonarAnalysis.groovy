#!usr/bin/env groovy
import com.example.Test


def call(String sonnerHome){
        return new Test(this).sonarAnalysis(sonnerHome);
}

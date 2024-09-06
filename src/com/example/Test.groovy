package com.example

class Test implements Serializable{
    def script

    Test(script){
        this.script = script
    }

    def test(){
        script.sh "mvn test"
    }

    def checkStyleAnalysis(){
        script.sh "mvn checkstyle:checkstyle"
    }

    def sonarAnalysis(String scannerHome) {
        script.withSonarQubeEnv('sonar') {
            script.sh """${scannerHome}/bin/sonar-scanner \
            -Dsonar.projectKey=banking \
            -Dsonar.projectName=banking-microservice-repo \
            -Dsonar.projectVersion=1.0 \
            -Dsonar.sources=src/ \
            -Dsonar.java.binaries=target/classes \
            -Dsonar.junit.reportsPath=target/surefire-reports/ \
            -Dsonar.jacoco.reportPaths=target/jacoco.exec \
            -Dsonar.java.checkstyle.reportPaths=target/checkstyle-result.xml
        """
        }
    }

    def qualityGate(){
        script.timeout(time: 1, unit: 'HOURS'){
                script.waitForQualityGate abortPipeline: true
        }
    }
}

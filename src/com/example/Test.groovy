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

    def runSonarScanner(String sonarEnv, String scannerHome, String projectKey, String projectName, String projectVersion, String sources, String hostUrl, String javaBinaries, String jacocoReportsPath, String junitReportsPath, String coverageXmlReportPath, String dependencyCheckJsonPath, String dependencyCheckHtmlPath, String checkstyleReportPath) {
        script.withSonarQubeEnv(sonarEnv) {
            def sonarCommand = """
            ${scannerHome}/bin/sonar-scanner \\
                -Dsonar.projectKey=${projectKey} \\
                -Dsonar.projectName=${projectName} \\
                -Dsonar.projectVersion=${projectVersion} \\
                -Dsonar.sources=${sources} \\
                -Dsonar.host.url=${hostUrl} \\
                -Dsonar.java.binaries=${javaBinaries} \\
                -Dsonar.jacoco.reportsPath=${jacocoReportsPath} \\
                -Dsonar.junit.reportsPath=${junitReportsPath} \\
                -Dsonar.coverage.jacoco.xmlReportPaths=${coverageXmlReportPath} \\
                -Dsonar.dependencyCheck.jsonReportPath=${dependencyCheckJsonPath} \\
                -Dsonar.dependencyCheck.htmlReportPath=${dependencyCheckHtmlPath} \\
                -Dsonar.java.checkstyle.reportPaths=${checkstyleReportPath}
        """
            script.echo "Running SonarQube analysis for project: ${projectName}"
            script.sh(script: sonarCommand)
        }
    }

    def qualityGate(){
        script.timeout(time: 1, unit: 'HOURS'){
                script.waitForQualityGate abortPipeline: true
        }
    }
}

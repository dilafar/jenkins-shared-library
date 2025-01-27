#!usr/bin/env groovy
import com.example.Test


def call(String sonarEnv, String scannerHome, String projectKey, String projectName, String projectVersion, String sources, String hostUrl, String javaBinaries, String jacocoReportsPath, String junitReportsPath, String coverageXmlReportPath, String dependencyCheckJsonPath, String dependencyCheckHtmlPath, String checkstyleReportPath){
        return new Test(this).runSonarScanner(sonarEnv,scannerHome,projectKey,projectName,projectVersion,sources,hostUrl,javaBinaries,jacocoReportsPath,junitReportsPath,coverageXmlReportPath,dependencyCheckJsonPath,dependencyCheckHtmlPath,checkstyleReportPath)
}

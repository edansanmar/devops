package etapas.reto
def sonarQube() {
        def scannerHome = tool 'SonarqubeScanner'
withSonarQubeEnv('ServerSonarqube') {
    sh "${scannerHome}/bin/sonar-scanner \
        -Dsonar.projectKey=$KEY_PROJECT \
        -Dsonar.projectName=${NAME_PROJECT} \
        -Dsonar.sources=src/main/java \
        -Dsonar.java.binaries=target/classes \
        -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
}


    echo "Finalización de prueba en sonarQube"
    //return jacocoReportPath
}



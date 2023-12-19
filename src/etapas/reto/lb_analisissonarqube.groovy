package etapas.reto
def sonarQube() {
        def scannerHome = tool 'SonarqubeScanner'
withSonarQubeEnv('ServerSonarqube') {
    sh "${scannerHome}/bin/sonar-scanner \
        -Dsonar.projectKey=analisisTermometro \
        -Dsonar.projectName=analisisTermometro \
        -Dsonar.sources=. \
        -Dsonar.java.binaries=. \
        -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
}


    echo "Finalización de prueba en sonarQube"
    //return jacocoReportPath
}

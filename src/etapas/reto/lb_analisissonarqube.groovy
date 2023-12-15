package etapas.reto

def clonarCheckout(scmUrl) {
        git url: scmUrl
        echo "Finalizado"
}


def construirBuild() {
         sh 'mvn clean package'
        echo "Finalización de Build Applicarion"
}

def pruebaTest() {
         sh 'mvn clean package'
        echo "Finalización de Build Applicarion"
}

def sonarQube() {
        def scannerHome = tool 'SonarqubeScanner'
    withSonarQubeEnv('ServerSonarqube') {
        sh "${scannerHome}/bin/sonar-scanner \
            -Dsonar.projectKey=analisisTermometro \
            -Dsonar.projectName=analisisTermometro \
            -Dsonar.sources=src/main/java \
            -Dsonar.java.binaries=target/classes \
            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
    }

    echo "Finalización de prueba en sonarQube"
}
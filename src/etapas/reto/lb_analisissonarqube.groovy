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

def empaquetadoPackage() {
            sh 'mvn package'
                }
                post {
                    always {
                        junit 'target/surefire-reports/TEST-*.xml' // Patrón para los archivos XML de pruebas
                    }
                    success {
                        archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false // Archivar el archivo JAR generado
                    }
        echo "Finalización del empaquetado"
}

def sonarQube() {
        def scannerHome = tool 'SonarqubeScanner'
    withSonarQubeEnv('ServerSonarqube') {
        sh "${scannerHome}/bin/sonar-scanner \
            -Dsonar.projectKey=analisisTermometro \
            -Dsonar.projectName=analisisTermometro \
            -Dsonar.sources=. \
            -Dsonar.java.binaries=.\
            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"

             // Asignar la ruta del informe Jacoco
        //def jacocoReportPath = "target/site/jacoco/jacoco.xml"
    }

    echo "Finalización de prueba en sonarQube"
    //return jacocoReportPath
}
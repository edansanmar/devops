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
    // Utilizar 'withEnv' para establecer la variable de entorno
    withEnv(["SCANNER_HOME=SonarqubeScanner"]) {
        // Bloque 'script' para ejecutar los pasos de SonarQube
        script {
            def scannerHome = tool 'SonarqubeScanner'
            withSonarQubeEnv('ServerSonarqube') {
                sh """
                    \${SCANNER_HOME}/bin/sonar-scanner \
                    -Dsonar.projectKey=analisisTermometro \
                    -Dsonar.projectName=analisisTermometro \
                    -Dsonar.sources=src/main/java \
                    -Dsonar.java.binaries=target/classes \
                    -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                """
            }
        }
    }

    // Tareas adicionales después de la ejecución de SonarQube
    echo "Finalización de prueba en SonarQube"
}

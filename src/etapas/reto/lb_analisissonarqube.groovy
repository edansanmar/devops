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
    environment {
        scannerHome = tool 'SonarqubeScanner'
    
    steps {
        withSonarQubeEnv('ServerSonarqube') {
           sh "${scannerHome}/bin/sonar-scanner \
                            -Dsonar.projectKey=analisisTermometro \
                            -Dsonar.projectName=analisisTermometro \
                            -Dsonar.sources=src/main/java \
                            -Dsonar.java.binaries=target/classes \
                            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
        }
    }
    script {
            // Consultar la API de SonarQube para obtener información adicional
            def sonarApiUrl = "https://tu-sonarqube-server/api/"
            def projectKey = "analisisTermometro"
            def response = sh(script: "curl -s ${sonarApiUrl}measures/component?component=${projectKey}", returnStatus: true)
            if (response == 0) {
                echo "Análisis de SonarQube completado con éxito."
            } else {
                echo "Error en el análisis de SonarQube."
            }
        }
    }
    
    // Tareas adicionales después de la ejecución de SonarQube
    echo "Finalización de prueba en SonarQube"
}

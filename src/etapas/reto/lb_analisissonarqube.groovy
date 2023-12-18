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

/*def empaquetadoPackage() {

              sh 'mvn package'
                }
                post {
                    always {
                        junit 'target/surefire-reports/TEST-*.xml' // Patrón para los archivos XML de pruebas
                    }
                    success {
                        archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false // Archivar el archivo JAR generado
                    }     
                    return this
}*/
def empaquetadoPackage() {
    enviroment { def rutaArchivoJar // Variable para almacenar la ruta del archivo JAR generado

    script {
        sh 'mvn package'
        // Obtener la ruta del archivo JAR generado
        rutaArchivoJar = sh(script: 'find target -name "*.jar" | head -n 1', returnStdout: true).trim()
    }

    post {
        always {
            junit 'target/surefire-reports/TEST-*.xml' // Patrón para los archivos XML de pruebas
        }
        success {
            // Archivar el archivo JAR generado
            archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
        }
        script {
            // Guardar la ruta del archivo JAR en un archivo de propiedades
            writeFile file: 'ruta_archivo_jar.properties', text: "RUTA_ARCHIVO_JAR=${rutaArchivoJar}"
        }
    }

    return rutaArchivoJar
    }
}


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
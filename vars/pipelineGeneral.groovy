//File: pipelineGeneral.groovvy
def call (Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend wiht SCM URL: ${scmUrl}"
pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    git url: scmUrl
                }
            }

            stage('Build Application') {
                steps {
                    sh 'mvn clean package'
                }
            }

            stage('Test') {
                steps {
                    sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent test jacoco:report' // Ejecuta las pruebas y genera el informe de cobertura con JaCoCo
                }
            }

            stage('Package') {
                steps {
                    sh 'mvn package'
                }
                post {
                    always {
                        junit 'target/surefire-reports/TEST-*.xml' // Patrón para los archivos XML de pruebas
                    }
                    success {
                        archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false // Archivar el archivo JAR generado
                    }
                }
            }

            stage('SonarQube analysis') {
                environment {
                    scannerHome = tool 'SonarqubeScanner'
                }
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
            }
        }
    }
  
}
[11:42 a. m., 12/12/2023] Edwin Andrés Sánchez: // File: pipelineGeneral.groovy
def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    
    // Bloque node para que funcione el script cambio
    node {
        // Importa scripts externos
        script {
            // Se carga o importa el script externo
            load 'vars/prueba'

            // Se llama o llaman las funciones
            prueba.artefacto()
        }
    }
}
[1:49 p. m., 12/12/2023] Edwin Andrés Sánchez: // File: pipelineGeneral.groovy
def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    
    // Pipeline, para probar el funcionamiento de la librería compartida
    pipeline {
        agent any
        stages {
            
            stage('Step 1') {
                steps {
                    // Llama a la función de la biblioteca compartida pasando scmUrl como argumento
                    script {
                        prueba.call(scmUrl)
                    }
                }
            }
        }
    }
}
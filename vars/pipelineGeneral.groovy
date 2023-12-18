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

            /*stage('Package') {
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
            }*/
                        // Llamada a la función definida en packageStage.groovy
            script {
                def packageStage = load 'packageStage.groovy'
                packageStage.runPackageStage()
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
                            -Dsonar.sources=. \
                            -Dsonar.java.binaries=. \
                            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
                    }
                }
            }
        }
    }
  
}
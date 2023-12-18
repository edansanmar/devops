def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"

    pipeline {
        agent any
        stages {
            stage('Checkout') {
                steps {
                    script {
                        def clonarr = new etapas.reto.lb_analisissonarqube()
                        clonarr.clonarCheckout(scmUrl)
                    }
                }
            }
            stage('Build Application') {
                steps {
                    script {
                        def cleann = new etapas.reto.lb_analisissonarqube()
                        cleann.construirBuild()
                    }
                }
            }
            stage('Test') {
                steps {
                    script {
                        def pruebaa = new etapas.reto.lb_analisissonarqube()
                        pruebaa.pruebaTest()
                    }
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

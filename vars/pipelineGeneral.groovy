// File: pipelineGeneral.groovy
def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"

    pipeline {
        agent any
        stages {
            stage('Checkout') {
                steps {
                    script {
                        def clonarr = new etapas.reto.lb_buildartefacto()
                        clonarr.clonarCheckout(scmUrl)
                    }
                }
            }
            stage('Build Application') {
                steps {
                    script {
                        def cleann = new etapas.reto.lb_buildartefacto()
                        cleann.construirBuild()
                    }
                }
            }
            stage('Test') {
                steps {
                    script {
                        def pruebaa = new etapas.reto.lb_buildartefacto()
                        pruebaa.pruebaTest()
                    }
                }
            }
            stage('Package') {
                steps {
                    script {
                        // Llamamos a la función empaquetadoPackage y recibimos el resultado en el mapa resultadoEmpaquetado
                        def resultadoEmpaquetado = new etapas.reto.lb_analisissonarqube()
                        resultadoEmpaquetado.empaquetadoPackage()

                    }
                }
            }

            stage('SonarQube') {
                steps {
                    script {
                        def analisiscode = new etapas.reto.lb_analisissonarqube()
                        analisiscode.empaquetadoPackage()
                    }
                }
            }
        }

        // Bloque post para manejar post-construcción
        post {
            always {
                script {
                    junit 'target/surefire-reports/TEST-*.xml'
                }
            }
            success {
                script {
                    archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
                }
            }
        }
    }
}

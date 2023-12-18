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
        
        // Manejo de acciones de post-construcción
        script {
            // Ejecutar en caso de éxito
            if (currentBuild.resultIsBetterOrEqualTo('SUCCESS')) {
                junit 'target/surefire-reports/TEST-*.xml'
                archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
            }
            // Ejecutar en caso de error
            else {
                echo "Error occurred: ${currentBuild.result}"
            }
        }
    }
}

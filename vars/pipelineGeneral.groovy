// File: pipelineGeneral.groovy
def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    echo "Uso de la rama feature"

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
                        def resultadoEmpaquetado = new etapas.reto.lb_buildartefacto()
                        resultadoEmpaquetado.empaquetadoPackage()
                    }
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
    

            stage('SonarQube') {
                
                steps {
                    script {
                        def analisiscode = new etapas.reto.lb_analisissonarqube()
                        analisiscode.sonarQube()
                    }
                }
            }
        }
    }
}

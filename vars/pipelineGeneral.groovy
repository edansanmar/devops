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
                        cleann.call()
                    }
                }
            }
           /* stage('Test') {
                steps {
                    script {
                        def pruebaa = new etapas.reto.lb_analisissonarqube()
                        pruebaa.pruebaTest()
                    }
                }
            }
            stage('Package') {
                steps {
                    script {
                        // Llamamos a la función empaquetadoPackage y recibimos el resultado en el mapa resultadoEmpaquetado
                        def resultadoEmpaquetado = new etapas.reto.lb_analisissonarqube().empaquetadoPackage(params)

                        // Accedemos a la ruta del archivo JAR desde el mapa resultadoEmpaquetado
                        def rutaArchivoJar = resultadoEmpaquetado.rutaArchivoJar

                        // Ahora puedes usar 'rutaArchivoJar' según tus necesidades
                        echo "Ruta del archivo JAR: ${rutaArchivoJar}"
                    }
                }
            }*/
        }
    }
}

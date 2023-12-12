// File: pipelineGeneral.groovy
def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    
    // Pipeline, para probar el funcionamiento de la librería compartida
    pipeline {
        agent any
        stages {
            //Cambio a subir al repositorio remoto
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
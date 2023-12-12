// File: pipelineGeneral.groovy
def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    
    // Pipeline, para probar el funcionamiento de la librería compartida
    pipeline {
        agent any
        stages {
            stage('Step 1') {
                steps {
                    script {
                        // Llama a la función de la biblioteca compartida
                        prueba.artefacto(scmUrl)
                    }
                }
            }
        }
    }
}


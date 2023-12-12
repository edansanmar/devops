// File: pipelineGeneral.groovy
// File: pipelineGeneral.groovy
def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    
    // Pipeline, para probar el funcionamiento de la librería compartida
    pipeline {
        agent any
        stages {
            stage('Checkout') {
        steps {
            git url: scmUrl
        }
    }
            stage('Step 1') {
                steps {
                    // Llama a la función de la biblioteca compartida pasando scmUrl como argumento
                    script {
                        prueba.artefacto(scmUrl)
                    }
                }
            }
        }
    }
}


// File: pipelineGeneral.groovy
def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    
    //Pipeline, para probar el funcionamiento de la libreria compartida
    Pipeline {
        any agent
         stages {
            stage('Step 1') {
                steps {
                    script {
                    // Llama a la función de la biblioteca compartida
                    prueba.artefacto()
                }
            }
        }
    }
}


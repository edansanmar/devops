// vars/pipelineGeneral.groovy
def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    pipeline{
        agent any
        stages{
            stage('Clonar y verificar el repositorio'){
                steps{
                    script{
                        clonarycapturar(scmUrl)
                    }
                }
            }
        }
    }
}

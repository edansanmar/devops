
// Resto del código
library('devops').import etapas.reto.clonar()
def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    pipeline {
        stages{
            stage('Chekout') {
                script {
                    load 'src/etapas/reto/clonar.groovy'
                    def clonarIn=  clonar()
                    clonarIn()
                }
            }
        }
    }    
   
}


// Resto del código
@Library('devops') _

def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    pipeline {
        stages{
            stage('Chekout') {
                script {
                    clonar.chekout(scmUrl)
                }
            }
        }
    }    
   
}

// Importa la clase necesaria
import etapas.reto.clonar

// Resto del código
//@Library('devops') _

def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    
    pipeline {
        agent any
        stage('Clone and Checkout') {
        steps {
          script {
            //print "-------------------${env.GIT_BRANCH}-------------------"
            clonar(scmUrl)
          }
        }
      }
    }
}

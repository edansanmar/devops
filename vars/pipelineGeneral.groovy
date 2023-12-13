// Importa la clase necesaria
import etapas.reto.clonar

// Resto del código
//@Library('devops') _

def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    
    pipeline {
        agent any
        stages {
            stage('Verificar repo') {
                steps {
                    // Crea una instancia de la clase y llama al método
                    script {
                      clonar(scmUrl)
                    }
                }
            }
        }
    }
}

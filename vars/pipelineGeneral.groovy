 // Importa la función desde la carpeta src de la misma librería
//@Library('devops') _

def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    
    pipeline {
        agent any
        stages {
            stage('Verificar repo') {
                steps {
                    // Crear una instancia de la clase y llamar a la función
                    script {
                        def clonarInstancia = new src.etapa.reto.clonar()
                        clonarInstancia.call(scmUrl)
                    }
                }
            }
        }
    }
}

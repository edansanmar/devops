 // Importa la función desde la carpeta src de la misma librería
    @Library('devops') _
def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    pipeline{
        stages{
            stage('Verificar repo'){
                def clonarInstancia = new etapa.reto.clonarMethod()
                clonarInstancia(scmUrl)
            }
        }
    }
   
}

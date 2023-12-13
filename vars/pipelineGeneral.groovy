 // Importa la función desde la carpeta src de la misma librería
    @Library('devops.ClonarYCapturar') _
def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    
   
    
    // Llama a la función clonarycapturar pasando scmUrl como argumento
    clonarycapturar(scmUrl)
}

// vars/pipelineGeneral.groovy
def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    
    // Importa la etapa desde la carpeta src de la misma librería
    //@Library('devops') _

    // Llama a la función de la etapa (especifica la ruta completa si es necesario)
    devops.bar(scmUrl)
}

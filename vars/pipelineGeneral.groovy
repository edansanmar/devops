//File: pipelineGeneral.groovvy
def call (Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend wiht SCM URL: ${scmUrl}"
    
    //Importa scripts externos
    script {
        //Se carga o importa el script externo
        load 'vars/lb_buildartefacto/lb_buildartefacto.groovy'

        //Se llama o llaman las funciones
        lb_buildartefacto.lb_buildartefacto1()
    }
  
}
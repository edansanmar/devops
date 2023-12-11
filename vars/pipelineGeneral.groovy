//File: pipelineGeneral.groovvy
def call (Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend wiht SCM URL: ${scmUrl}"
    
    //Importa scripts externos
    script {
        load 
    }
  
}
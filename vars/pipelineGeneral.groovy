def call (Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend wiht SCM URL: ${scmUrl}"
pipeline {
        agent any
        stages {
            stage('Checkout') {
                steps {
                    script{
                        def clonarr= new etapas.reto.clonar()
                        clonarr.call(scmUrl)
                        
                    }
                  
                    
                }
            }

                  }
    }
  
}
// lb_buildartefacto

def artefacto(Map params) {
    def scmUrl = params.scmUrl
    echo "Primera parte de la ejecución del Pipeline"
    //Clonación del repositorio 
    stage('Checkout'){
        steps{
            git url: scmUrl
        }
    }
    //etapa de construción de la aplicación
    stage('Build Application'){
        steps{
            sh 'mvn clean package'
        }
    }


}
// lb_buildartefacto

def artefacto() {
    
    echo "Primera parte de la ejecución del Pipeline"
    //Clonación del repositorio 
    stage('Checkout'){
        steps{
            git url: 'https://github.com/edansanmar/termometro.git'
        }
    }
    //etapa de construción de la aplicación
    stage('Build Application'){
        steps{
            sh 'mvn clean package'
        }
    }


}
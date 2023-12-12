// lb_buildartefacto

def artefacto(scmUrl) {
    echo "Primera parte de la ejecución del Pipeline"
    // Clonación del repositorio 
    stage('Checkout') {
        steps {
            git url: scmUrl
        }
    }
    // Etapa de construcción de la aplicación
    stage('Build Application') {
        steps {
            sh 'mvn clean package'
        }
    }
}

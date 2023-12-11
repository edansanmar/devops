//File: pipelineGeneral.groovvy
def call (Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend wiht SCM URL: ${scmUrl}"
    
    // Clonar el repositorio
    echo "Clonación de repositorio"
    checkout([$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[url: scmUrl]]])

    // Construir la aplicación con Maven
    echo "Contruir la aplicación con Maven"
    sh 'mvn clean package'


}
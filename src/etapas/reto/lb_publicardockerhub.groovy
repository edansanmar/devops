//Se establece la dirección de ubicación del archivo
package etapas.reto
def publicarimagen(){
    def dockerHubUsername = 'edansama96'
    def dockerHubTokenCredentialId= 'tokendockerhub'
    def imageExists = sh(script: "docker images -q ${dockerHubUsername}/termometro-buildimagen"
    ,returnStdout: true).trim()
    if(imageExists){
        echo "La imagen ${dockerHubUsername}/termometro-buildimagen ya existe en Docker Hub. Descartndo Publicación... "
    } else {
        withCredentials([usernamePassword(credentialsId: dockerHubTokenCredentialId, passwordVariable:
        'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]){
            sh "docker login --username ${env.DOCKERHUB_USERNAME} --password ${env.DOCKERHUB_PASSWORD}"
        }
        sh "docker tag termometro-buildimagen ${dockerHubUsername} "
        sh "docker push ${dockerHubUsername}/termometro-buildimagen"
    }
}


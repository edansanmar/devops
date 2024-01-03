//Se establece la dirección de ubicación del archivo
package etapas.reto
def publicarimagen(){
    def dockerHubTokenCredentialId= 'tokendockerhub'
    def imageExists = sh(script: "docker images -q ${env.DOCKERHUB_USERNAME}/termometro-buildimagen",returnStdout: true).trim()
    if(imageExists){
        echo "La imagen ${env.DOCKERHUB_USERNAME}/termometro-buildimagen ya existe en Docker Hub. Descartndo Publicación... "
    } else {
        withCredentials([usernamePassword(creddentialsId: dockerHubTokenCredentialId, passwordVariable:'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]){
            sh "docker login --username ${env.DOCKERHUB_USERNAME} --password ${env.DOCKERHUB_PASSWORD}"
        }
        sh "docker tag termometro-buildimagen ${env.DOCKERHUB_USERNAME} "
        sh "docker push ${env.DOCKERHUB_USERNAME}/termometro-buildimagen"
    }
}


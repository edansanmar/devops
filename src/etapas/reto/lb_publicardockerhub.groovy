//Se establece la dirección de ubicación del archivo
package etapas.reto
def publicarimagen(){
    def dockerHubTokenCredentialId= 'tokendockerhub'
    def imageExists = sh(script: "docker images -q ${env.NAME_DOCKER_HUB}/termometro-buildimagen",returnStdout: true).trim()
    if(imageExists){
        echo "La imagen ${env.NAME_DOCKER_HUB}/termometro-buildimagen ya existe en Docker Hub. Descartndo Publicación... "
    } else {
        withCredentials([usernamePassword(creddentialsId: dockerHubTokenCredentialId, passwordVariable:'DOCKERHUB_PASSWORD', usernameVariable: 'NAME_DOCKER_HUB')]){
            sh "docker login --username ${env.NAME_DOCKER_HUB} --password ${env.DOCKERHUB_PASSWORD}"
        }
        sh "docker tag termometro-buildimagen ${env.NAME_DOCKER_HUB} "
        sh "docker push ${env.NAME_DOCKER_HUB}/termometro-buildimagen"
    }
}


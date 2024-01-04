//Se establece la dirección de ubicación del archivo
package etapas.reto

def publicarimagen() {
    def dockerHubUsername = 'edansama96'
    def dockerHubTokenCredentialId = 'tokendockerhub'

    // Verificar si la imagen ya existe localmente
    def imageExists = sh(script: "docker images -q ${dockerHubUsername}/termometro-buildimagen", returnStdout: true).trim()

   /* if (imageExists) {
        // Si la imagen ya existe, imprimir un mensaje y descartar la publicación
        echo "La imagen ${dockerHubUsername}/termometro-buildimagen ya existe en Docker Hub. Descartando publicación..."
    } else {
        // Autenticación en Docker Hub usando credenciales de Jenkins
        withCredentials([usernamePassword(credentialsId: dockerHubTokenCredentialId,
                                         passwordVariable: 'DOCKERHUB_PASSWORD',
                                         usernameVariable: 'DOCKERHUB_USERNAME')]) {
            sh "docker login --username ${env.DOCKERHUB_USERNAME} --password ${env.DOCKERHUB_PASSWORD}"
        }

        // Etiquetar la imagen local con el nombre de Docker Hub y luego empujarla a Docker Hub
       // sh "docker tag termometro-buildimagen ${dockerHubUsername}/termometro-buildimagen"
      //  sh "docker push ${dockerHubUsername}/termometro-buildimagen:latest"
       
    }*/
    //sh "docker push ${dockerHubUsername}/termometro-buildimagen"
    
     // Autenticación en Docker Hub utilizando las credenciales almacenadas en Jenkins
                    withCredentials([usernamePassword(credentialsId: 'dockerHubTokenCredentialId',
                                                     passwordVariable: 'DOCKERHUB_PASSWORD',
                                                     usernameVariable: 'DOCKERHUB_USERNAME')]) {
                        sh "docker login --username ${env.DOCKERHUB_USERNAME} --password ${env.DOCKERHUB_PASSWORD}"

                        // Etiquetar y empujar la imagen a Docker Hub
                        //sh 'docker tag miusuario/mi-imagen:latest miusuario/mi-imagen:latest'
                        //sh 'docker push miusuario/mi-imagen:latest'
                        sh 'docker push ${dockerHubUsername}/termometro-buildimagen'



}




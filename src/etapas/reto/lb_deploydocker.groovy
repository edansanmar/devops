def implementarDocker (){
   /* def dockerHubUsername = 'edansama96'
    def imageName = 'termometro-buildimagen'
    def containerName = 'termometro-buildimagen'
    def dockerimage = "${dockerHubUsername}/${imageName}:latest"
    def containerExists = sh(script: "docker ps -a --filter name=${containerName}",
    returnStdout: true).trim()
    if (containerExists) {
        echo "El contenedor ${containerExists} ya existe. Descartando creación..."
    } else {
        sh "docker stop ${containerName} || true"
        sh "docker rm ${containerName} || true"
       // sh "docker pull ${dockerimage}"
        sh "docker run -d -p 3000:8080 --name ${containerName} ${env.DOCKERHUB_USERNAME}/${imageName}"
    }*/
    def dockerImage = 'edansam96/termometro-buildimagen:latest'
    def containerName = 'termometro-buildimagen'

    sh "docker pull ${dockerImage}"

    sh "docker stop ${containerName} || true"
    sh "docker rem ${containerName} || true"

   sh "docker run -d  --name ${containerName} -p 3000:8080 edansam96/termometro-buildimagen:latest"


}


package etapas.reto
//Se procedera a crear una función, para la implementación de una imagen del proyecto
def createImage(){
   //Se establece una variable, la cual se utiliza para 
 def imageExists = sh(script: "docker images -q edansama96/termometro-buildimagen", returnStdout: true).trim()
 if(imageExists){
    echo "La imagen termometro-buildimagen ya existe. Descartando construcción..."
 }else {
    sh 'docker build -t edansama96/termometro-buildimagen -f ../termometro/Dockerfile ../termometro'
 }
}

//Se intenta que se pueda realizar el pull request

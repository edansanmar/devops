package etapas.reto
// packageStage.groovy// File: deploy.groovy
class DeployStage {
    def deployApplication() {
        sh 'mvn clean package'
        echo "Finalización de Build Applicarion"
        // Agrega aquí los comandos o scripts necesarios para desplegar tu aplicación
    }
}
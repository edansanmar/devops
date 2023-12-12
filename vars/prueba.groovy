// lb_buildartefacto

// File: buildStage.groovy
def call() {
    stage('Build') {
        steps {
            echo 'Building the application...'
            // Agrega aquí los pasos específicos para la etapa de build
        }
    }
}
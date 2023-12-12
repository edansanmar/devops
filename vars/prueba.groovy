// lb_buildartefacto

// File: checkoutStage.groovy
def call() {
    stage('Checkout') {
        steps {
            echo 'Checking out the code...'
            // Agrega aquí los pasos específicos para la etapa de checkout
        }
    }
}
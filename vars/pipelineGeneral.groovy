// File: pipelineGeneral.groovy

//@Library('etapas.reto') _

// Arreglo
def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"

    pipeline {
        agent any

        stages {
            clonar.scmCheckout(scmUrl) // Llama a la función de clonación definida en clonar.groovy
            // buildStage()
            // testStage()

            // Otras funciones de etapas aquí...

            // Puedes seguir llamando a las funciones de etapas según sea necesario
        }
    }
}


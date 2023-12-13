// File: pipelineGeneral.groovy

// Arreglo
def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"

    pipeline {
        agent any

        stages {
            checkoutStage(scmUrl)
            // buildStage()
            // testStage()

            // Otras funciones de etapas aquí...

            // Puedes seguir llamando a las funciones de etapas según sea necesario
        }
    }
}


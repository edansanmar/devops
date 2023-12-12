// lb_buildartefacto

// File: checkoutStage.groovy
def call(scmUrl) {
    stage('Checkout') {
        steps {
            echo 'Checking out the code...'
             git url: scmUrl
        }
    }
}
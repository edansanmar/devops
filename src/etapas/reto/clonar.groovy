// File: src/CheckoutStage.groovy

def call(scmUrl) {
    stage('Checkout') {
        steps {
            git url: scmUrl
        }
    }
}

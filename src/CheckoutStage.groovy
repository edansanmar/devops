// File: src/CheckoutStage.groovy

def checkoutStage(scmUrl) {
    stage('Checkout') {
        steps {
            git url: scmUrl
        }
    }
}

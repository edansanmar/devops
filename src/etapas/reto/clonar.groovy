// File: src/CheckoutStage.groovy
package src.etapas.reto

def call(scmUrl) {
    stage('Checkout') {
        steps {
            git url: scmUrl
        }
    }
}

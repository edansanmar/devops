// File: src/CheckoutStage.groovy
package etapas.reto

def call(scmUrl) {
        git url: scmUrl
        echo "Finalizado"
}

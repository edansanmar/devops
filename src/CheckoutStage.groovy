// File: src/CheckoutStage.groovy

def call(scmUrl) {
        git url: scmUrl
        echo "Finalizado"
}

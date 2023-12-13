package etapas.reto

    def call(scmUrl) {
  node {
    //cambio
      git url: scmUrl
    }



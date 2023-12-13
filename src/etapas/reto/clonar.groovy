package etapas.reto

class clonar{
    def clonarMethod(scmUrl){
        node {
            script {
                 git url: scmUrl
            }
        }
    }
}




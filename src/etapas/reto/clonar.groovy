
def chekout(scmUrl){
    node {
         //cambio
         script {
      git url: scmUrl
      }
    }
}


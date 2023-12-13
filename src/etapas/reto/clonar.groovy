
def chekout(scmUrl){
   pipeline{
     stages {
        stage("Verificación"){
            steps{
                script{
                    git url: scmUrl
                }
            }
        }
    }
   }
}


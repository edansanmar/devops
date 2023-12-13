
def chekout(scmUrl){
   pipeline{
     stages {
        stage("Verificación"){
            steps{
                script{
                    sh 'echo hello wordl'
                }
            }
        }
    }
   }
}


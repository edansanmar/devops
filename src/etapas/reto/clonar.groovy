
def chekout(scmUrl){
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


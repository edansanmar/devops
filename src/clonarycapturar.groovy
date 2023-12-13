def call(scmUrl){
    node {
        script{
            git url: scmUrl
        }
    }
}
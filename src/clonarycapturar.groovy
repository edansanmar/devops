def call(scmUrl){
    node {
        script{
            git url: 'https://github.com/edansanmar/termometro.git'
        }
    }
}
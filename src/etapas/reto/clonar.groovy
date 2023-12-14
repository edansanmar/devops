def call(scmUrl) {
    //cambio de info
        git url: scmUrl
        echo "Finalizado"
        sh'mvn clean'
        echo "Finalizo la limpieza"
        sh 'mvn install'
}

package etapas.reto

def clonarCheckout(scmUrl) {
        git url: scmUrl
        echo "Finalizado"
}

def call() {
    try {
        sh 'mvn dependency:purge-local-repository'
        sh 'mvn clean'
        sh 'mvn compile'
        sh 'mvn test'
        sh 'mvn package'

        post {
            always {
                junit 'target/surefire-reports/TEST-*.xml' // Patrón para los archivos XML de pruebas
            }
            success {
                archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false // Archivar el archivo JAR generado
            }
        }

        //archiveArtifacts artifacts: "target/${env.ARTIFACT_BACK}.jar", allowEmptyArchive: true, onlyIfSuccessful: false, displayName: "${env.ARTIFACT_BACK}.jar"

        //sh "mvn deploy:deploy-file -Durl=${env.MAVEN_SNAPSHOTS} -DrepositoryId=${env.REPOSITORY_ID_NEXUS} -Dfile=target/${env.ARTIFACT_BACK}.jar -DgroupId=${env.GROUP_ID_NEXUS} -DartifactId=${env.ARTIFACT_ID_BACK_JAVA} -Dversion=${env.ARTIFACT_VERSION_NEXUS_JAVA} -Dpackaging=jar -DrepositoryUsername='${env.NEXUS_USERNAME}' -DrepositoryPassword='${env.NEXUS_PASSWORD}'"

    } catch (Exception e) {
        currentBuild.result = 'FAILURE'
        throw e
    }
}




def construirBuild() {
         sh 'mvn clean package'
        echo "Finalización de Build Applicarion"
}

def pruebaTest() {
        sh 'mvn clean package'
        sh 'mvn test'
        sh 'mvn clean verify' //Generación del informe de cobertura
        //sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent test jacoco:report'
        echo "Finalización de Build Applicarion"
}

def empaquetadoPackage() {

              sh 'mvn package'
                }
                post {
                    always {
                        junit 'target/surefire-reports/TEST-*.xml' // Patrón para los archivos XML de pruebas
                    }
                    success {
                        archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false // Archivar el archivo JAR generado
                    }     
                    return this
}

/*def sonarQube() {
        def scannerHome = tool 'SonarqubeScanner'
withSonarQubeEnv('ServerSonarqube') {
    sh "${scannerHome}/bin/sonar-scanner \
        -Dsonar.projectKey=analisisTermometro \
        -Dsonar.projectName=analisisTermometro \
        -Dsonar.sources=. \
        -Dsonar.java.binaries=. \
        -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
}


    echo "Finalización de prueba en sonarQube"
    //return jacocoReportPath
}*/
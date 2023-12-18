package etapas.reto
// packageStage.groovy
def runPackageStage() {
    stage('Package') {
        steps {
            sh 'mvn package'
        }
        post {
            always {
                junit 'target/surefire-reports/TEST-*.xml'
            }
            success {
                archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
            }
        }
    }
}
return this // Devuelve el objeto actual para permitir llamadas encadenadas

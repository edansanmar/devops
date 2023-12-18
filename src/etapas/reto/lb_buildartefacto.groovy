package etapas.reto
// packageStage.groovy
def call() {
      sh 'mvn package'
                }
                post {
                    always {
                        junit 'target/surefire-reports/TEST-*.xml' // Patrón para los archivos XML de pruebas
                    }
                    success {
                        archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false // Archivar el archivo JAR generado
                    }
}
return this // Devuelve el objeto actual para permitir llamadas encadenadas

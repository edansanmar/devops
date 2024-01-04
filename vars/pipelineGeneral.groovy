// File: pipelineGeneral.groovy

def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"
    echo "Using the feature branch"

    pipeline {
        agent any
        stages {
            stage('Checkout') {
                steps {
                    script {
                        def buildArtefacto = new etapas.reto.lb_buildartefacto()
                        buildArtefacto.clonarCheckout(scmUrl)
                    }
                }
            }
            stage('Build Application') {
                steps {
                    script {
                        def buildArtefacto = new etapas.reto.lb_buildartefacto()
                        buildArtefacto.construirBuild()
                    }
                }
            }
            stage('Test') {
                steps {
                    script {
                        def buildArtefacto = new etapas.reto.lb_buildartefacto()
                        buildArtefacto.pruebaTest()
                    }
                }
            }
            stage('Package') {
                steps {
                    script {
                        def buildArtefacto = new etapas.reto.lb_buildartefacto()
                        buildArtefacto.empaquetadoPackage()
                    }
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
            stage('SonarQube') {
                steps {
                    script {
                        def analisisSonarQube = new etapas.reto.lb_analisissonarqube()
                        analisisSonarQube.sonarQube()
                    }
                }
            }
            stage('Build Image') {
                steps {
                    script {
                        def buildImagen = new etapas.reto.lb_buildimagen()
                        buildImagen.createImage()
                    }
                }
            }
            stage('Publish Image') {
                steps {
                    script {
                        def publicarImagen = new etapas.reto.lb_publicardockerhub()
                        publicarImagen.publicarimagen()
                    }
                }
            }
        }
    }
}

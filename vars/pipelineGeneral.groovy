def call(Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM URL: ${scmUrl}"

    pipeline {
        agent any
        stages {
            stage('Checkout') {
                when {
                    expression { return env.BRANCH_NAME == 'feature' }
                }
                steps {
                    script {
                        def clonarr = new etapas.reto.lb_buildartefacto()
                        clonarr.clonarCheckout(scmUrl)
                    }
                }
            }
            stage('Build Application') {
                when {
                    expression { return env.BRANCH_NAME == 'feature' }
                }
                steps {
                    script {
                        def cleann = new etapas.reto.lb_buildartefacto()
                        cleann.construirBuild()
                    }
                }
            }
            stage('Test') {
                when {
                    expression { return env.BRANCH_NAME == 'feature' }
                }
                steps {
                    script {
                        def pruebaa = new etapas.reto.lb_buildartefacto()
                        pruebaa.pruebaTest()
                    }
                }
            }
            stage('Package') {
                when {
                    expression { return env.BRANCH_NAME == 'feature' }
                }
                steps {
                    script {
                        def resultadoEmpaquetado = new etapas.reto.lb_buildartefacto()
                        resultadoEmpaquetado.empaquetadoPackage()
                    }
                }
            }
            stage('SonarQube') {
                when {
                    expression { return env.BRANCH_NAME == 'feature' }
                }
                steps {
                    script {
                        def analisiscode = new etapas.reto.lb_analisissonarqube()
                        analisiscode.sonarQube()
                    }
                }
            }
        }
    }
}

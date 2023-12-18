def call (Map params) {
    def scmUrl = params.scmUrl

    echo "Deploying backend wiht SCM URL: ${scmUrl}"
pipeline {
        agent any
        stages {
            stage('Checkout') {
                steps {
                    script{
                        def clonarr= new etapas.reto.lb_analisissonarqube()
                        clonarr.clonarCheckout(scmUrl)
                        
                    }
                  
                    
                }
            }
            stage('Build Applicarion') {
                steps {
                    script{
                        def cleann= new etapas.reto.lb_analisissonarqube()
                        cleann.construirBuild()
                    }
                }
            }
            stage('Test') {
                steps {
                    script{
                        def pruebaa= new etapas.reto.lb_analisissonarqube()
                        pruebaa.pruebaTest()
                    }
                }
            }
            stage('Package') {
                node {
                    script{
                        def empaquetadoo= new etapas.reto.lb_analisissonarqube()
                        empaquetadoo.empaquetadoPackage()
                    }
                }
            }
            stage('SonarQube analysis') {
                node {
                    script{
                        def analisiss= new etapas.reto.lb_analisissonarqube()
                        analisiss.sonarQube()
                    }
                }
            }

                  }
    }
  
}
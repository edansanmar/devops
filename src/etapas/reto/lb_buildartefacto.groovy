package etapas.reto

def clonarCheckout(scmUrl) {
        git url: scmUrl
        echo "Finalizado"
}


def construirBuild() {
         sh 'mvn clean package'
        echo "Finalización de Build Applicarion"
}

def pruebaTest() {
        sh 'mvn clean package'
        //sh 'mvn test'
        //sh 'mvn clean verify' //Generación del informe de cobertura
        sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent test jacoco:report'
        echo "Finalización de Build Applicarion"
}

def empaquetadoPackage() {

              sh 'mvn package'
              echo "Package Verificado"   
                
}


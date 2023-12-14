package etapas.reto
def call(){
    sh 'mvn clean package'
    eho "Finaliza limpieza"
}
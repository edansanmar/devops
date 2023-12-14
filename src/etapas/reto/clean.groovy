package etapas.reto
def call(){
    sh 'mvn clean package'
    echo "Finaliza limpieza"
}

package grafo;


public class Grafo<T extends Comparable> {

    //atributos
        int [][]matrizAdyacencia;
        public boolean [] visitados;
        public T  [] elementos;
        int size=0;   
        public NodoGrafo<T> origen;
    //buscar Orden
    
        public Grafo(){
            matrizAdyacencia=new int[size][size];
            visitados=new boolean [size];
            elementos=(T[])new Object[size];
            for(int i=0;i<size;i++)
                 for(int j=0;i<size;j++)
                     matrizAdyacencia[i][j]=0;
        }
        
        
        private int buscarPos(NodoGrafo<T> buscar){
            int x=0;
            while(x<size &&  elementos[x].compareTo(buscar.getElem())!=0){
                x++;
            }
            if(x==size)//no existe
                return -1;
            else//si lo encontro
                return x;
        }
        
        private void expandCapacity(){
            boolean[] aux = new boolean[size* 2];
            T[] aux2=(T[])new Object[size*2];

        for (int i = 0; i <size; i++) {
            aux[i] = visitados[i];
            aux2[i]=elementos[i];
        }
        visitados=aux;
        elementos=aux2;
    
        }
        private void addVisitados(){
            
        }
        private void addElementos(){
            
        }
        
        public void join(NodoGrafo<T> origen, NodoGrafo<T> destino){
            if(origen.getElem().equals(destino.getElem())){
                //no es grafico con ciclos
                int x=buscarPos(origen);
                int y=buscarPos(destino);
                matrizAdyacencia[x][y]=0;
            }else{
                origen.setSiguiente(destino);
                int x=buscarPos(origen);
                int y=buscarPos(destino);
                matrizAdyacencia[x][y]=1;
            }
    }
        
        public void insert(T elem){
            NodoGrafo<T> nuevo=new NodoGrafo<T>(elem);
            size=size+1;
            if(size>=visitados.size)
               expandCapacity();
            else

               visitados[size]=false;
               elementos[size]=elem;
        }
    
    
    public String buscaOrden(NodoGrafo<T> inicial){
int x=buscaPos(inicial);
 while()
}
    
    
    
    public static void main(String[] args) {
       
    }
    
}

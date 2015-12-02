
package grafo;

import java.util.ArrayList;
import java.util.Queue;


public class Grafo<T extends Comparable> {

    //atributos
        int [][]matrizAdyacencia;
        public boolean [] visitados;
        public T  [] elementos;
        int numNodos=0;   
        public NodoGrafo<T> origen;
    
        public void imprimeGrafo(){
            System.out.println("Elementos: ");
            imprimeElementos();
            System.out.println("");
            System.out.println("Visitados: ");
            imprimeVisitados();
            System.out.println("Matriz Adyacencia: ");
            imprimeMatriz();
                    
        }
        private void imprimeElementos(){
            for(int i=0;i<elementos.length;i++)
                System.out.print(elementos[i]+" ");
        }
        private void imprimeVisitados(){
            for(int i=0;i<visitados.length;i++)
                System.out.print(visitados[i]+" ");
        }
        private void imprimeMatriz() {
        for (int x=0; x <numNodos; x++) {
            for (int y=0; y<numNodos; y++) {
                if(y==numNodos)
                    y=0;
                System.out.print(matrizAdyacencia[x][y]+" ");
            }
            System.out.println("");
         }
       
        }
    
        public Grafo(int n){//n>0
            matrizAdyacencia=new int[n][n];
            visitados=new boolean [n];
            elementos=(T[])new Comparable[n];
            for(int i=0;i<n;i++){
                 for(int j=0;j<n;j++){
                     matrizAdyacencia[i][j]=0;
                 }
            }
            
            for(int j=0; j<n;j++){
                visitados[j]=false;
            }
            numNodos=n;
        }
        
       
        private int buscarPos(NodoGrafo<T> buscar){
            int x=0;
            T elem=elementos[x];
            while(x<numNodos &&  elem.compareTo(buscar.getElem())!=0){
                x++;
                elem=elementos[x];
            }
            if(x==numNodos)//no existe
                return -1;
            else//si lo encontro
                return x;
            
            
        }
        
        private void expandCapacity(){
            
            boolean[] aux = new boolean[visitados.length* 2];
            T[] aux2=(T[])new Comparable[elementos.length*2];

        for (int i = 0; i <visitados.length; i++) {
            aux[i] = visitados[i];//vaciado
            aux2[i]=elementos[i];
        }
        visitados=aux;
        elementos=aux2;
    
        }
        private void marcar(NodoGrafo<T> inicial){
            int pos=buscarPos(inicial);
            visitados[pos]=true;
        }
        private boolean marcado(NodoGrafo<T> inicial){
            boolean resp=false;
            int pos=buscarPos(inicial);
            if(visitados[pos]==true)
                resp=true;
            return resp;
        }
        private Pila<T> obtenerAdyacencia(NodoGrafo<T> uno){
            int pos= buscarPos(uno);
            Pila<T> pila = null; 
          
            for(int i=0;i<elementos.length;i++){
                if(matrizAdyacencia[pos][i]==1){
                    pila.push(elementos[i]);
                }
                  
            }
            return pila;
        
        }
        public void DFS(T i){//recorrido por profundidad
           NodoGrafo<T> inicio=new NodoGrafo<T>(i);
            T vertice = null;
            NodoGrafo<T> v;
            
            marcar(inicio);
            System.out.println(inicio.getElem().toString());
            Pila<T> adyacencia=obtenerAdyacencia(inicio);
            while(!adyacencia.isEmpty())
                 vertice=(T)adyacencia.pop();
                 v=new NodoGrafo<T>(vertice);
                 if(marcado(v)==false){
                     DFS(vertice);
                }
                     
            
        }
     
        
        
        
        public void join(T origen, T destino){
            NodoGrafo<T> o=new NodoGrafo(origen);
            NodoGrafo<T> d=new NodoGrafo(destino);
            if(origen.equals(destino)){
                //no es grafico con ciclos
                int x=buscarPos(o);
                int y=buscarPos(d);
                matrizAdyacencia[x][y]=0;
            }else{
                o.setSiguiente(d);
                int x=buscarPos(o);
                int y=buscarPos(d);
                matrizAdyacencia[x][y]=1;
            }
    }
       
        public void insert(T elem, int contador){
            //NodoGrafo<T> nuevo=new NodoGrafo(elem);
                               
               elementos[contador]=elem;
                contador++;
              
               
        }
       
    
    
    /*public String buscaOrden(NodoGrafo<T> inicial){
        Pila pila=new Pila();
        Queue cola = null;
        boolean done=done();
        
        if(inicial.getElem()==null)
            return "";
        if(done==true)//si ya visitaste todo
            return cola.toString();
            
        
        int x=buscarPos(inicial);
        int i=0;
        while(i<size && matrizAdyacencia[x][i]==0){//mientras no encuentres conexion buscar en adyacencia
              i++;
        }
        if(i==size){//nodo es final grafo
             pila.push(inicial.getElem());
             visitados[x]=true;
             
        }else{
            cola.add(inicial.getElem());
            visitados[x]=true;
            
        }
        
        
     
          
        
        
        
        return pila.toString();
}
    private boolean done(){
        boolean resp=false;
        int i=0;
      while(visitados[i]==true && i<size){
          i++;
      }
      if(i==size){
          //todo elemento en visitados es true
          resp=true;
      }
      return resp;
      
    }
    
    */
    public static void main(String[] args) {
        
        
       Grafo<Integer> g=new Grafo(5);
       for(int i=1;i<=5;i++){
           g.insert(i,0);
       }
       g.imprimeGrafo();
    
       g.join(1,2);
       g.join(2,3);
       g.join(2,4);
       g.join(3,5);
       g.join(4,6);
       g.join(1,7);
       g.join(7,8);
       g.join(7,9);
       g.join(8,10);
       g.join(9,10);
       
      
       g.DFS(1);
       
    }
    
}

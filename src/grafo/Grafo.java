
package grafo;

import java.util.ArrayList;
import java.util.Queue;


public class Grafo<T extends Comparable> {

    //atributos
        int [][]matrizAdyacencia;
        public boolean [] visitados;
        public T  [] elementos;
        int size=0;   
        public NodoGrafo<T> origen;
    //buscar Orden
    
        public Grafo(int n){//n>0
            matrizAdyacencia=new int[n][n];
            visitados=new boolean [n];
            elementos=(T[])new Comparable[n];
            for(int i=0;i<n;i++){
                 for(int j=0;j<n;j++){
                     matrizAdyacencia[i][j]=0;
                 }
            }
        }
        
       
        private int buscarPos(NodoGrafo<T> buscar){
            int x=0;
            while(x<elementos.length &&  !elementos[x].equals(buscar.getElem())){
                x++;
            }
            if(x==elementos.length){//no existe
                return -1;
            }
            else{//si lo encontro
                return x;
            }
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
        private Queue obtenerAdyacencia(NodoGrafo<T> uno){
            int pos= buscarPos(uno);
            Queue<T> cola = null; 
          
            for(int i=0;i<elementos.length;i++){
                if(matrizAdyacencia[pos][i]==1){
                    cola.add(elementos[i]);
                }
                  
            }
            return cola;
        
        }
        public void DFS(T i){//recorrido por profundidad
           NodoGrafo<T> inicio=new NodoGrafo<T>(i);
            T vertice = null;
            NodoGrafo<T> v;
            
            marcar(inicio);
            System.out.println(inicio.getElem().toString());
            Queue<T> adyacencia=obtenerAdyacencia(inicio);
            while(!adyacencia.isEmpty())
                 vertice=adyacencia.remove();
                 v=new NodoGrafo<T>(vertice);
                 if(marcado(v)==false){
                     DFS(vertice);
                }
                     
            
        }
     
        
        
        
        public void join(T origen, T destino){
            NodoGrafo<T> o=new NodoGrafo<T>(origen);
            NodoGrafo<T> d=new NodoGrafo<T>(destino);
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
       
        public void insert(T elem){
            NodoGrafo<T> nuevo=new NodoGrafo<T>(elem);
                                      
               if(size>=visitados.length){
                   expandCapacity();
               }
           
               visitados[size]=false;
               elementos[size]=elem;
               size=size+1;
              
               
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
        
        
       Grafo<Integer> g=new Grafo(10);
       for(int i=1;i<=10;i++){
           g.insert(i);
       }
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

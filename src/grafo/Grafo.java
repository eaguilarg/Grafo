
package grafo;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;


public class Grafo<T extends Comparable> {

    //atributos
        int [][]matrizAdyacencia;
        public boolean [] visitados;
        public T  [] elementos;
        int Tope=0; 
        int numElem=0;
        public NodoGrafo<T> origen;
    
        public void imprimeGrafo(){
            System.out.println("Elementos: ");
            imprimeElementos();
            System.out.println("");
            System.out.println("");
            System.out.println("Visitados: ");
            imprimeVisitados();
            System.out.println("");
            System.out.println("");
            System.out.println("Matriz Adyacencia: ");
            imprimeMatriz();
             System.out.println("");
                    
        }
        
        
        private void imprimeElementos(){
            for(int i=0;i<elementos.length;i++) 
                System.out.print(elementos[i]+" ");
        }
        private void imprimeVisitados(){
            for(int i=0;i<visitados.length;i++)
                System.out.println(elementos[i]+") "+visitados[i]);
        }
        private void imprimeMatriz() {
          System.out.print("  ");
          imprimeElementos();
          System.out.println("");
        for (int x=0; x <Tope; x++) {
            System.out.print(elementos[x]+" ");
            for (int y=0; y<Tope; y++) {
                if(y==Tope){
                    y=0;
                }
                System.out.print(matrizAdyacencia[x][y]+" ");
            }
            System.out.println("");
         }
       
        }
    
        //constructor grafo, inicializar todo en cero
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
            Tope=n;
        }
        
       //funcion que busca la posicion en []elementos==> fila [][]ady
        private int buscarPos(NodoGrafo<T> buscar){
            int x=0;
            
            while(x<numElem &&  elementos[x].compareTo(buscar.getElem())!=0){
               x++;
            }
            if(x==numElem)//no existe
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
        
        //modifica []visitados (funcion para buscar orden)
        private void marcar(NodoGrafo<T> inicial){
            int pos=buscarPos(inicial);
            visitados[pos]=true;
        }
        //funcion que verifica si elem []visitados ya fue visitado(true)
        private boolean marcado(NodoGrafo<T> inicial){
            boolean resp=false;
            int pos=buscarPos(inicial);
            if(visitados[pos]==true)
                resp=true;
            return resp;
        }
        //funcion que regresa cola de adyancencia de un nodo
        public Stack<T> obtenerAdyacencia(NodoGrafo<T> uno){
            int fila= buscarPos(uno);
            Stack<T> cola = new Stack(); 
          
            for(int columna=0;columna<numElem;columna++){
                if(matrizAdyacencia[fila][columna]==1){
                    cola.add(elementos[columna]);
                }
                  
            }
            if(cola.size()==0){
                return null;
            }
            return cola;
        
        }
        public void dfs(T i){
            Stack<T> pila=new Stack();
            DFS(i,false, pila);
            System.out.println(pila.toString());
        }
        //recorrido por profundidad
        private boolean DFS(T i, boolean done, Stack<T> ruta){
            NodoGrafo<T> inicio=new NodoGrafo(i);
            T vertice = null;
            NodoGrafo<T> v;
            boolean resp=false;
        
            
            marcar(inicio);
           ruta.addElement(i);
            done=done();
            //System.out.println(i.toString());
            Stack<T> adyacencia=obtenerAdyacencia(inicio);
            
            
            if(done==true)
                return true;
            
            if(done==false && adyacencia==null){//no recorriste todo el grafo
                int pos=done2();
                ruta.addElement(i);
                return DFS(elementos[pos],done,ruta);
                
            
            }            
            while(adyacencia!=null){
                 vertice=adyacencia.pop();
                 v=new NodoGrafo<T>(vertice);
                  
                 if(marcado(v)==false){
                      return DFS(vertice,done,ruta);
                 }
                  adyacencia=obtenerAdyacencia(inicio);
                 }
                 
            return resp;
            }
            
            public void TopologicalDFS(T i){
                for(int i=0; i<Grafo.tope;i++){
                    topological(i,pilaRuta);
                }
            }
            private boolean topological(T i, Stack pilaRuta){
                
            }
           
            
            
           
        
        
        
        
        
        
     private boolean done(){
         
         int i=0;
         while(i<numElem && visitados[i]==true){
             i++;
         }
         if(i==numElem){//todo []visitados es true
             return true;
         }
         else{ 
             return false;
         }
                     
     }
     private int done2(){//busca pos []visitados = false
         
         int i=0;
         while(i<numElem && visitados[i]==true){
             i++;
         }
         if(i==numElem){//todo []visitados es true
             return -1;
         }
         else{ 
             return i;
         }
                     
     }
     
     
        
        
        //funcion que une dos elementos, modifica [][]ady
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
                int fila=buscarPos(o);
                int columna=buscarPos(d);
                matrizAdyacencia[fila][columna]=1;
            }
    }
       //funcion que agrega elem a []elementos
        public void insert(T elem){
            
               if(numElem==Tope){
                   expandCapacity();
               }  
               if(elem==null){
                   return ;
               }
               
               elementos[numElem]=elem;
               numElem++;
              
        }
       
    
    
    
    public static void main(String[] args) {
        
        Grafo<Integer> g=new Grafo(7);
       for(int i=1;i<=g.Tope;i++){
           g.insert(i);
       }
       g.imprimeGrafo();
    
       g.join(1,4);
       g.join(2,3);
       g.join(3,4);
       g.join(3,5);
       g.join(5,6);
       g.join(5,7);
           
     System.out.println("");
      g.imprimeGrafo();
     
     g.dfs(1);
     
    }
    
}

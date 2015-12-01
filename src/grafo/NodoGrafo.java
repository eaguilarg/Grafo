
package grafo;

public class NodoGrafo <T extends Comparable>{
    
    //atributos
    T elem;
    NodoGrafo<T> siguiente;
    
    //constructor
    public NodoGrafo(T elem){
       this.elem=elem; 
       this.siguiente=null;
    }
    
    //gets y sets
    public T getElem(){
        return elem;
    }
    public void setElem(T elem){
        this.elem=elem;
    }
    
    
    public void setSiguiente(NodoGrafo<T> otro){
        this.siguiente=otro;
    }
    
    public NodoGrafo<T> getSiguiente(){
        return siguiente;
    }
    
    public int compareTo(NodoGrafo<T> otro) {
        return otro.getElem().compareTo(this.elem);
    }
    
    public String toString() {
        StringBuilder cad = new StringBuilder();

        cad.append("Elemento: " + elem.toString()+"\n");
        return cad.toString();
    }
    
}

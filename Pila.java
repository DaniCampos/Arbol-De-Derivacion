import java.util.*;

public class Pila{

    private Node ultimo;
    private int tamano;

    private class Node{
        Nodo value;
        Node sgte;
    }

    public Pila(){
        ultimo = null;
        tamano = 0;
    }

    boolean estaVacia(){
        if(tamano == 0){
            return true;
        }
        return false;
    }

    void apilar(Nodo valor){
        if(estaVacia()){
            ultimo = new Node();
            ultimo.value = valor;
            ultimo.sgte = null;
        }
        else{
            Node anterior = ultimo;
            ultimo = new Node();
            ultimo.value = valor;
            ultimo.sgte = anterior;
        }
        ++tamano;
    }

    Nodo desapilar() {

        Nodo pos = null;

        if (estaVacia()) {
            System.out.println("Pila Vacia");
            pos = null;
        }
        else {

            pos = ultimo.value;

            if (tamano == 1) {
                ultimo = null;
            } else {
                ultimo = ultimo.sgte;
            }
            --tamano;
        }

        return pos;

    }

}
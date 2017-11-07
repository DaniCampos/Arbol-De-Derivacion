public class Nodo {

    String info;
    Nodo izq;
    Nodo der;

    public Nodo(String info,Nodo i, Nodo d) {
        this.info=info;
        this.izq = i;
        this.der = d;
    }

    public void imprimir(){
        Nodo puntero = this;
        if (puntero != null){
            if(puntero.izq == null && puntero.der == null){
                System.out.print(puntero.info);
                System.out.print(" ");
            }
            else if(puntero.izq != null && puntero.der == null){
                puntero.izq.imprimir();
                System.out.print(puntero.info);
                System.out.print(" ");
            }
            else if(puntero.izq == null && puntero.der != null){
                System.out.print(puntero.info);
                System.out.print(" ");
                puntero.der.imprimir();
            }
            else if(puntero.izq != null && puntero.der != null){
                puntero.izq.imprimir();
                System.out.print(puntero.info);
                System.out.print(" ");
                puntero.der.imprimir();
            }
        }
    }
}

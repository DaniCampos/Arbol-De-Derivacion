import java.util.*;

public class Tarea4{

    public static Nodo  parentesis(Nodo tree){
        Nodo a,b;
        if(tree != null && (tree.info.charAt(0) == '*' || tree.info.charAt(0) == '/')){
            if(tree.izq != null && (tree.izq.info.charAt(0) == '+'|| tree.izq.info.charAt(0) == '-')){
                a = parentesisIzq(tree.izq);
                b = parentesisDer(tree.izq);

                tree.izq = new Nodo(b.info, b.izq, b.der);
            }

            if(tree.der != null && (tree.der.info.charAt(0) == '+'|| tree.der.info.charAt(0) == '-')){
                a = parentesisIzq(tree.der);
                b = parentesisDer(tree.der);

                tree.der = new Nodo(b.info, b.izq, b.der);
            }

        }


        a = null;
        b = null;
        if(tree.izq != null){
            a = parentesis(tree.izq);
        }
        if(tree.der != null){
            b = parentesis(tree.der);
        }

        tree = new Nodo(tree.info, a, b);

        return tree;
    }

    public static Nodo parentesisIzq(Nodo tree){
        if(tree.izq == null){
            tree.izq = new Nodo("(", null, null);
        }
        else{
            parentesisIzq(tree.izq);
        }
        return tree;
    }
    public static Nodo parentesisDer(Nodo tree){
        if(tree.der == null){
            tree.der = new Nodo(")", null, null);
        }
        else{
            parentesisDer(tree.der);
        }
        return tree;
    }

    public static Nodo simplificar(Nodo tree){
        char value = tree.info.charAt(0);
        if(value == '+'){
            if(tree.izq != null && tree.izq.info.charAt(0) == '0'){
                if(tree.der != null){
                    tree = new Nodo(tree.der.info, tree.der.izq, tree.der.der);
                }
                else{
                    tree = new Nodo(null, null, null);
                }

            }
            if(tree.der != null && tree.der.info.charAt(0) == '0'){
                if(tree.izq != null){
                    tree = new Nodo(tree.izq.info, tree.izq.izq, tree.izq.der);
                }
                else{
                    tree = new Nodo(null, null, null);
                }
            }
            if(tree.izq != null && tree.izq.info.charAt(0) == '0' && tree.der != null && tree.der.info.charAt(0) == '0'){
                tree = new Nodo("0", null, null);
            }
        }

        else if(value == '-'){
            if(tree.izq != null && tree.izq.info.charAt(0) == '0'){
                if(tree.der != null){
                    tree = new Nodo("-", null, tree.der);
                }
            }
            if(tree.der != null && tree.der.info.charAt(0) == '0'){
                if(tree.izq != null){
                    tree = new Nodo(tree.izq.info, tree.izq.izq, tree.izq.der);
                }
            }
            if(tree.izq == null && tree.der != null && tree.der.info.charAt(0) == '0'){
                tree = new Nodo("0", null, null);
            }
            if(tree.der == null && tree.izq != null && tree.izq.info.charAt(0) == '0'){
                tree =  new Nodo("0", null, null);
            }
        }

        else if(value == '*'){
            if((tree.izq != null && tree.izq.info.charAt(0) == '0' )||(tree.der != null && tree.der.info.charAt(0) == '0')){
                tree= new Nodo("0", null, null);
            }
            if(tree.izq != null && tree.izq.info.charAt(0) == '1'){
                if(tree.der != null){
                    tree = new Nodo(tree.der.info, tree.der.izq, tree.der.der);
                }
            }
            if(tree.der != null && tree.der.info.charAt(0) == '1'){
                if(tree.izq != null){
                    tree = new Nodo(tree.izq.info, tree.izq.izq, tree.izq.der);
                }
            }

        }

        else if(value == '/'){
            if(tree.der != null && tree.der.info.charAt(0) == '1'){
                tree = new Nodo(tree.izq.info, tree.izq.izq, tree.izq.der);
            }
            if(tree.izq != null && tree.izq.info.charAt(0) == '0'){
                tree = new Nodo("0", null, null);
            }
        }

        return tree;
    }

    public static Nodo derivar(Nodo tree, String variable){
        Nodo a,b, ap, bp, c, d, e, f, g, h, i;
        char value = tree.info.charAt(0);
        char variable2 = variable.charAt(0);

        if(value != variable2 && value != '*' && value != '/' && value != '+' && value != '-'){
            tree.info = "0";
        }
        else if(value == variable2){
            tree.info = "1";
        }
        else{
            if(value == '+' || value == '-'){
                a = derivar(tree.izq, variable);
                b = derivar(tree.der, variable);
                tree.izq = new Nodo(a.info, a.izq, a.der);
                tree.der = new Nodo(b.info, b.izq, b.der);
                c = simplificar(tree);
                tree = new Nodo(c.info, c.izq, c.der);

            }
            else if(value == '*'){
                tree.info = "+";
                a = new Nodo(tree.izq.info, tree.izq.izq, tree.izq.der);
                b = new Nodo(tree.der.info, tree.der.izq, tree.der.der);
                ap = new Nodo(tree.izq.info, tree.izq.izq, tree.izq.der);
                bp = new Nodo(tree.der.info, tree.der.izq, tree.der.der);
                c = derivar(ap,variable);
                d = derivar(bp,variable);
                ap = new Nodo(c.info, c.izq, c.der);
                bp = new Nodo(d.info, d.izq, d.der);


                tree.izq = new Nodo("*", ap, b);
                e = simplificar(tree.izq);
                tree.izq = new Nodo(e.info, e.izq, e.der);


                tree.der = new Nodo("*", a, bp);
                f = simplificar(tree.der);
                tree.der = new Nodo(f.info, f.izq, f.der);

                g = simplificar(tree);

                tree = new Nodo(g.info, g.izq, g.der);

            }
            else if(value == '/'){
                a = new Nodo(tree.izq.info, tree.izq.izq, tree.izq.der);
                b = new Nodo(tree.der.info, tree.der.izq, tree.der.der);
                ap = new Nodo(tree.izq.info, tree.izq.izq, tree.izq.der);
                bp = new Nodo(tree.der.info, tree.der.izq, tree.der.der);
                c = derivar(ap,variable);
                d = derivar(bp,variable);
                ap = new Nodo(c.info, c.izq, c.der);
                bp = new Nodo(d.info, d.izq, d.der);

                e = new Nodo("*", ap, b);
                f = new Nodo("*", a, bp);

                tree.izq = new Nodo("-", simplificar(e), simplificar(f));
                g = simplificar(tree.izq);
                tree.izq = new Nodo(g.info, g.izq, g.der);

                tree.der = new Nodo("*", b, b);
                h = simplificar(tree.der);
                tree.der = new Nodo(h.info, h.izq, h.der);

                i = simplificar(tree);
                tree = new Nodo(i.info, i.izq, i.der);

            }
        }
        return tree;
    }

    public static void makeTree(String[] S, Pila p){
        Nodo a,b;
        for(int i = 0; i < S.length; ++i){
            char value = S[i].charAt(0);
            if(value == '*' || value == '/' || value == '+' || value == '-'){
                a = p.desapilar();
                b = p.desapilar();
                Nodo op = new Nodo(S[i],b, a);
                p.apilar(op);
            }
            else{
                Nodo valor = new Nodo(S[i], null, null);
                p.apilar(valor);
            }
        }
    }


    static public void main(String[] args){

        Scanner s = new Scanner(System.in);
        System.out.print("Ingrese expresión en notación Polaca Inversa: ");
        String exp = s.nextLine();
        System.out.print("Respecto a que quiere derivar esta expresión? ");
        String variable = s.nextLine();

        Pila pila = new Pila();
        String[] S = exp.split(" ");

        makeTree(S, pila);

        Nodo treeDerivado = new Nodo(null, null, null);

        Nodo treeFinal = pila.desapilar();

        //treeFinal.imprimir();
        //System.out.println();

        Nodo a = derivar(treeFinal, variable);
        treeFinal = new Nodo(a.info, a.izq, a.der);

        Nodo b = parentesis(treeFinal);
        treeFinal = new Nodo(b.info, b.izq, b.der);

        treeFinal.imprimir();
        System.out.println();

    }




}
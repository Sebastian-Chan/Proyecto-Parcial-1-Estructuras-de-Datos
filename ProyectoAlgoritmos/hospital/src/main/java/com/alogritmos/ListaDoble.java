package com.alogritmos;

//ESTA ES UNA CLASE QUE IMPLEMENTA UNA LISTA DOBLEMENTE ENLAZADA
public class ListaDoble<T> extends ListaBase<T> {
    // REFERENCIA AL PRIMER NODO DE LA LISTA
    private NodoDoble<T> primero;
    // REFERENCIA AL ÚLTIMO NODO DE LA LISTA
    private NodoDoble<T> ultimo;
    
    // CONSTRUCTOR - INICIALIZA LA LISTA DOBLE VACÍA
    public ListaDoble() {
        super();
        this.primero = null;
        this.ultimo = null;
    }
    
    // IMPLEMENTACIÓN DEL MÉTODO AGREGAR - AGREGA AL FINAL POR DEFECTO
    @Override
    public boolean agregar(T elemento) {
        return agregarFinal(elemento);
    }
    
    // AGREGA UN ELEMENTO AL INICIO DE LA LISTA DOBLE
    @Override
    public boolean agregarInicio(T elemento) {
        // CREAR NUEVO NODO DOBLE
        NodoDoble<T> nuevo = new NodoDoble<>(elemento);
        
        // SI LA LISTA ESTÁ VACÍA
        if (primero == null) {
            primero = ultimo = nuevo;
        } else {
            // CONECTAR EL NUEVO NODO CON EL PRIMERO ACTUAL
            nuevo.setSiguiente(primero);
            primero.setAnterior(nuevo);
            // EL NUEVO NODO SE CONVIERTE EN EL PRIMERO
            primero = nuevo;
        }
        
        tamaño++;
        return true;
    }
    
    // AGREGA UN ELEMENTO AL FINAL DE LA LISTA DOBLE
    @Override
    public boolean agregarFinal(T elemento) {
        // CREAR NUEVO NODO DOBLE
        NodoDoble<T> nuevo = new NodoDoble<>(elemento);
        
        // SI LA LISTA ESTÁ VACÍA
        if (ultimo == null) {
            primero = ultimo = nuevo;
        } else {
            // CONECTAR EL ÚLTIMO NODO ACTUAL CON EL NUEVO
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            // EL NUEVO NODO SE CONVIERTE EN EL ÚLTIMO
            ultimo = nuevo;
        }
        
        tamaño++;
        return true;
    }
    
    // ELIMINA UN ELEMENTO DE LA LISTA DOBLE
    @Override
    public boolean eliminar(T elemento) {
        NodoDoble<T> actual = primero;
        
        // RECORRER LA LISTA BUSCANDO EL ELEMENTO
        while (actual != null) {
            if (actual.getDato().equals(elemento)) {
                // CASO 1: ES EL ÚNICO NODO
                if (actual == primero && actual == ultimo) {
                    primero = ultimo = null;
                }
                // CASO 2: ES EL PRIMER NODO
                else if (actual == primero) {
                    primero = actual.getSiguiente();
                    primero.setAnterior(null);
                }
                // CASO 3: ES EL ÚLTIMO NODO
                else if (actual == ultimo) {
                    ultimo = actual.getAnterior();
                    ultimo.setSiguiente(null);
                }
                // CASO 4: ESTÁ EN EL MEDIO
                else {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                }
                
                tamaño--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        
        return false;
    }
    
    // BUSCA UN ELEMENTO EN LA LISTA DOBLE
    @Override
    public T buscar(T elemento) {
        NodoDoble<T> actual = primero;
        // RECORRER LA LISTA DESDE EL INICIO
        while (actual != null) {
            if (actual.getDato().equals(elemento)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    // VERIFICA SI LA LISTA CONTIENE UN ELEMENTO
    @Override
    public boolean contiene(T elemento) {
        return buscar(elemento) != null;
    }
    
    // LIMPIA TODA LA LISTA DOBLE
    @Override
    public void limpiar() {
        primero = ultimo = null;
        tamaño = 0;
    }
    
    // MUESTRA TODOS LOS ELEMENTOS DE LA LISTA DOBLE
    @Override
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La lista doble está vacía");
            return;
        }
        
        System.out.print("Lista doble: null <- ");
        NodoDoble<T> actual = primero;
        while (actual != null) {
            System.out.print(actual.getDato());
            if (actual.getSiguiente() != null) {
                System.out.print(" <-> ");
            }
            actual = actual.getSiguiente();
        }
        System.out.println(" -> null");
    }
    
    // MÉTODO ADICIONAL PARA RECORRER LA LISTA HACIA ATRÁS
    public void mostrarReversa() {
        if (estaVacia()) {
            System.out.println("La lista doble está vacía");
            return;
        }
        
        System.out.print("Lista doble (reversa): null <- ");
        NodoDoble<T> actual = ultimo;
        // RECORRER DESDE EL ÚLTIMO HACIA EL PRIMERO
        while (actual != null) {
            System.out.print(actual.getDato());
            if (actual.getAnterior() != null) {
                System.out.print(" <-> ");
            }
            actual = actual.getAnterior();
        }
        System.out.println(" -> null");
    }
}
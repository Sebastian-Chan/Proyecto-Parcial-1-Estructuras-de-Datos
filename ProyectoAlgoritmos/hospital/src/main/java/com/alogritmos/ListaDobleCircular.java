package com.alogritmos;

public class ListaDobleCircular<T> extends ListaBase<T> {
    // APUNTA AL ÚLTIMO NODO DE LA LISTA CIRCULAR
    private NodoDoble<T> ultimo;
    
    // CONSTRUCTOR - INICIALIZA LA LISTA DOBLE CIRCULAR VACÍA
    public ListaDobleCircular() {
        super();
        this.ultimo = null;
    }
    
    // IMPLEMENTACIÓN DEL MÉTODO AGREGAR - AGREGA AL FINAL POR DEFECTO
    @Override
    public boolean agregar(T elemento) {
        return agregarFinal(elemento);
    }
    
    // AGREGA UN ELEMENTO AL INICIO DE LA LISTA DOBLE CIRCULAR
    @Override
    public boolean agregarInicio(T elemento) {
        // CREAR NUEVO NODO DOBLE
        NodoDoble<T> nuevo = new NodoDoble<>(elemento);
        
        // SI LA LISTA ESTÁ VACÍA
        if (ultimo == null) {
            ultimo = nuevo;
            // EL ÚNICO NODO SE CONECTA CONSIGO MISMO EN AMBAS DIRECCIONES
            ultimo.setSiguiente(ultimo);
            ultimo.setAnterior(ultimo);
        } else {
            // OBTENER EL PRIMER NODO
            NodoDoble<T> primero = ultimo.getSiguiente();
            // CONECTAR EL NUEVO NODO
            nuevo.setSiguiente(primero);
            nuevo.setAnterior(ultimo);
            // ACTUALIZAR LAS CONEXIONES EXISTENTES
            primero.setAnterior(nuevo);
            ultimo.setSiguiente(nuevo);
        }
        
        tamaño++;
        return true;
    }
    
    // AGREGA UN ELEMENTO AL FINAL DE LA LISTA DOBLE CIRCULAR
    @Override
    public boolean agregarFinal(T elemento) {
        // SI LA LISTA ESTÁ VACÍA, ES LO MISMO QUE AGREGAR AL INICIO
        if (ultimo == null) {
            return agregarInicio(elemento);
        }
        
        // CREAR NUEVO NODO
        NodoDoble<T> nuevo = new NodoDoble<>(elemento);
        // OBTENER EL PRIMER NODO
        NodoDoble<T> primero = ultimo.getSiguiente();
        
        // CONECTAR EL NUEVO NODO EN EL CÍRCULO
        nuevo.setSiguiente(primero);
        nuevo.setAnterior(ultimo);
        ultimo.setSiguiente(nuevo);
        primero.setAnterior(nuevo);
        // EL NUEVO NODO SE CONVIERTE EN EL ÚLTIMO
        ultimo = nuevo;
        
        tamaño++;
        return true;
    }
    
    // ELIMINA UN ELEMENTO DE LA LISTA DOBLE CIRCULAR
    @Override
    public boolean eliminar(T elemento) {
        if (ultimo == null) return false;
        
        // SI SOLO HAY UN ELEMENTO
        if (ultimo.getSiguiente() == ultimo && ultimo.getDato().equals(elemento)) {
            ultimo = null;
            tamaño--;
            return true;
        }
        
        // EMPEZAR DESDE EL PRIMER NODO
        NodoDoble<T> actual = ultimo.getSiguiente();
        do {
            if (actual.getDato().equals(elemento)) {
                // CONECTAR EL NODO ANTERIOR CON EL SIGUIENTE
                actual.getAnterior().setSiguiente(actual.getSiguiente());
                actual.getSiguiente().setAnterior(actual.getAnterior());
                
                // SI ELIMINAMOS EL ÚLTIMO NODO
                if (actual == ultimo) {
                    ultimo = actual.getAnterior();
                }
                
                tamaño--;
                return true;
            }
            actual = actual.getSiguiente();
        } while (actual != ultimo.getSiguiente()); // RECORRER EL CÍRCULO COMPLETO
        
        return false;
    }
    
    // BUSCA UN ELEMENTO EN LA LISTA DOBLE CIRCULAR
    @Override
    public T buscar(T elemento) {
        if (ultimo == null) return null;
        
        // EMPEZAR DESDE EL PRIMER NODO
        NodoDoble<T> actual = ultimo.getSiguiente();
        do {
            if (actual.getDato().equals(elemento)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        } while (actual != ultimo.getSiguiente()); // RECORRER EL CÍRCULO COMPLETO
        
        return null;
    }
    
    // VERIFICA SI LA LISTA CONTIENE UN ELEMENTO
    @Override
    public boolean contiene(T elemento) {
        return buscar(elemento) != null;
    }
    
    // LIMPIA TODA LA LISTA DOBLE CIRCULAR
    @Override
    public void limpiar() {
        ultimo = null;
        tamaño = 0;
    }
    
    // MUESTRA TODOS LOS ELEMENTOS DE LA LISTA DOBLE CIRCULAR
    @Override
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La lista doble circular está vacía");
            return;
        }
        
        System.out.print("Lista doble circular: ");
        NodoDoble<T> actual = ultimo.getSiguiente();
        do {
            System.out.print(actual.getDato());
            actual = actual.getSiguiente();
            if (actual != ultimo.getSiguiente()) {
                System.out.print(" <-> ");
            }
        } while (actual != ultimo.getSiguiente());
        System.out.println(" <-> [CIRCULAR]");
    }
}
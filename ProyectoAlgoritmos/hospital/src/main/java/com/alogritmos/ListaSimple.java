package com.alogritmos;

class ListaSimple<T> extends ListaBase<T> {
    private Nodo<T> cabeza;
    
    public ListaSimple() {
        super();
        this.cabeza = null;
    }
    
    @Override
    public boolean agregar(T elemento) {
        return agregarFinal(elemento);
    }
    
    @Override
    public boolean agregarInicio(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
        tamaño++;
        return true;
    }
    
    @Override
    public boolean agregarFinal(T elemento) {
        if (cabeza == null) {
            return agregarInicio(elemento);
        }
        
        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        
        Nodo<T> nuevo = new Nodo<>(elemento);
        actual.setSiguiente(nuevo);
        tamaño++;
        return true;
    }
    
    @Override
    public boolean eliminar(T elemento) {
        if (cabeza == null) return false;
        
        // Si el primer elemento es el que buscamos
        if (cabeza.getDato().equals(elemento)) {
            cabeza = cabeza.getSiguiente();
            tamaño--;
            return true;
        }
        
        // Buscar en el resto de la lista
        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().equals(elemento)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamaño--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        
        return false;
    }
    
    @Override
    public T buscar(T elemento) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(elemento)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    @Override
    public boolean contiene(T elemento) {
        return buscar(elemento) != null;
    }
    
    @Override
    public void limpiar() {
        cabeza = null;
        tamaño = 0;
    }
    
    @Override
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La lista está vacía");
            return;
        }
        
        System.out.print("Lista: ");
        Nodo<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.getDato());
            if (actual.getSiguiente() != null) {
                System.out.print(" -> ");
            }
            actual = actual.getSiguiente();
        }
        System.out.println(" -> null");
    }
}
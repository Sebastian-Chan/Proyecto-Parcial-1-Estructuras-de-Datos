package com.alogritmos;


public class ListaCircular<T> extends ListaBase<T> {
    // APUNTA AL ÚLTIMO NODO, QUE A SU VEZ APUNTA AL PRIMERO
    private Nodo<T> ultimo;
    
    // CONSTRUCTOR - INICIALIZA LA LISTA CIRCULAR VACÍA
    public ListaCircular() {
        super();
        this.ultimo = null;
    }
    
    // IMPLEMENTACIÓN DEL MÉTODO AGREGAR - AGREGA AL FINAL POR DEFECTO
    @Override
    public boolean agregar(T elemento) {
        return agregarFinal(elemento);
    }
    
    // AGREGA UN ELEMENTO AL INICIO DE LA LISTA CIRCULAR
    @Override
    public boolean agregarInicio(T elemento) {
        // CREAR NUEVO NODO CON EL ELEMENTO
        Nodo<T> nuevo = new Nodo<>(elemento);
        
        // SI LA LISTA ESTÁ VACÍA
        if (ultimo == null) {
            ultimo = nuevo;
            // EL ÚNICO NODO SE APUNTA A SÍ MISMO PARA FORMAR EL CÍRCULO
            ultimo.setSiguiente(ultimo);
        } else {
            // EL NUEVO NODO APUNTA AL PRIMER NODO (SIGUIENTE DEL ÚLTIMO)
            nuevo.setSiguiente(ultimo.getSiguiente());
            // EL ÚLTIMO NODO APUNTA AL NUEVO NODO
            ultimo.setSiguiente(nuevo);
        }
        
        tamaño++;
        return true;
    }
    
    // AGREGA UN ELEMENTO AL FINAL DE LA LISTA CIRCULAR
    @Override
    public boolean agregarFinal(T elemento) {
        // SI LA LISTA ESTÁ VACÍA, ES LO MISMO QUE AGREGAR AL INICIO
        if (ultimo == null) {
            return agregarInicio(elemento);
        }
        
        // CREAR NUEVO NODO
        Nodo<T> nuevo = new Nodo<>(elemento);
        // EL NUEVO NODO APUNTA AL PRIMER NODO
        nuevo.setSiguiente(ultimo.getSiguiente());
        // EL ÚLTIMO NODO ACTUAL APUNTA AL NUEVO NODO
        ultimo.setSiguiente(nuevo);
        // EL NUEVO NODO SE CONVIERTE EN EL ÚLTIMO
        ultimo = nuevo;
        
        tamaño++;
        return true;
    }
    
    // ELIMINA UN ELEMENTO DE LA LISTA CIRCULAR
    @Override
    public boolean eliminar(T elemento) {
        // SI LA LISTA ESTÁ VACÍA
        if (ultimo == null) return false;
        
        // SI SOLO HAY UN ELEMENTO Y ES EL QUE BUSCAMOS
        if (ultimo.getSiguiente() == ultimo && ultimo.getDato().equals(elemento)) {
            ultimo = null;
            tamaño--;
            return true;
        }
        
        // EMPEZAR DESDE EL PRIMER NODO
        Nodo<T> actual = ultimo.getSiguiente();
        Nodo<T> anterior = ultimo;
        
        // RECORRER LA LISTA CIRCULAR
        do {
            // SI ENCONTRAMOS EL ELEMENTO
            if (actual.getDato().equals(elemento)) {
                // CONECTAR EL ANTERIOR CON EL SIGUIENTE
                anterior.setSiguiente(actual.getSiguiente());
                // SI ELIMINAMOS EL ÚLTIMO NODO
                if (actual == ultimo) {
                    ultimo = anterior;
                }
                tamaño--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        } while (actual != ultimo.getSiguiente()); // HASTA COMPLETAR EL CÍRCULO
        
        return false;
    }
    
    // BUSCA UN ELEMENTO EN LA LISTA CIRCULAR
    @Override
    public T buscar(T elemento) {
        if (ultimo == null) return null;
        
        // EMPEZAR DESDE EL PRIMER NODO
        Nodo<T> actual = ultimo.getSiguiente();
        do {
            // SI ENCONTRAMOS EL ELEMENTO
            if (actual.getDato().equals(elemento)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        } while (actual != ultimo.getSiguiente()); // RECORRER HASTA COMPLETAR EL CÍRCULO
        
        return null;
    }
    
    // VERIFICA SI LA LISTA CONTIENE UN ELEMENTO
    @Override
    public boolean contiene(T elemento) {
        return buscar(elemento) != null;
    }
    
    // LIMPIA TODA LA LISTA CIRCULAR
    @Override
    public void limpiar() {
        ultimo = null;
        tamaño = 0;
    }
    
    // MUESTRA TODOS LOS ELEMENTOS DE LA LISTA CIRCULAR
    @Override
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La lista circular está vacía");
            return;
        }
        
        System.out.print("Lista circular: ");
        Nodo<T> actual = ultimo.getSiguiente();
        do {
            System.out.print(actual.getDato());
            actual = actual.getSiguiente();
            if (actual != ultimo.getSiguiente()) {
                System.out.print(" -> ");
            }
        } while (actual != ultimo.getSiguiente());
        System.out.println(" -> [CIRCULAR]");
    }
}
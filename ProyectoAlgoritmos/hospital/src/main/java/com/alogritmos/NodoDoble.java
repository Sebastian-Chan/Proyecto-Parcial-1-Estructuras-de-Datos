package com.alogritmos;

public class NodoDoble<T> {
    // DATOS QUE GUARDA ESTE NODO
    protected T dato;
    // REFERENCIA AL SIGUIENTE NODO EN LA LISTA
    protected NodoDoble<T> siguiente;
    // REFERENCIA AL NODO ANTERIOR EN LA LISTA
    protected NodoDoble<T> anterior;
    
    // CONSTRUCTOR - CREA UN NODO CON EL DATO ESPECIFICADO
    public NodoDoble(T dato) {
        this.dato = dato;
        // AL INICIO NO TIENE CONEXIONES
        this.siguiente = null;
        this.anterior = null;
    }
    
    // MÉTODOS GETTER Y SETTER PARA ACCEDER Y MODIFICAR LOS ATRIBUTOS
    public T getDato() { return dato; }
    public void setDato(T dato) { this.dato = dato; }
    public NodoDoble<T> getSiguiente() { return siguiente; }
    public void setSiguiente(NodoDoble<T> siguiente) { this.siguiente = siguiente; }
    public NodoDoble<T> getAnterior() { return anterior; }
    public void setAnterior(NodoDoble<T> anterior) { this.anterior = anterior; }
}

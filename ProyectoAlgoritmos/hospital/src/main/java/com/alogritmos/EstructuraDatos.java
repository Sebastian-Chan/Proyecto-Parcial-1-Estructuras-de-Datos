package com.alogritmos;

//ESTA ES UNA CLASE DE TIPO INTERFAZ QUE DEFINE LOS MÉTODOS BÁSICOS PARA UNA ESTRUCTURA DE DATOS GENÉRICA
//DE ESTA NACERÁN LAS CLASES QUE IMPLEMENTEN PILA, COLA, LISTA, ETC.
public interface EstructuraDatos<T> {
    boolean agregar(T elemento);
    boolean eliminar(T elemento);
    T buscar(T elemento);
    boolean contiene(T elemento);
    int tamaño();
    boolean estaVacia();
    void limpiar();
    void mostrar();
}
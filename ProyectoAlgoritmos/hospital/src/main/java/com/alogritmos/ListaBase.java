package com.alogritmos;
//ESTA ES UNA CLASE ABSTRACTA QUE PROPORCIONA UNA IMPLEMENTACIÓN BASE PARA LISTAS ENLAZADAS
//IMPLEMENTA LA INTERFAZ EstructuraDatos Y DEFINE MÉTODOS COMUNES PARA LISTAS

abstract class ListaBase<T> implements EstructuraDatos<T> {
    protected int tamaño;
    
    public ListaBase() {
        this.tamaño = 0;
    }
    
    @Override
    public int tamaño() {
        return tamaño;
    }
    
    @Override
    public boolean estaVacia() {
        return tamaño == 0;
    }
    
    // Métodos que las clases hijas DEBEN implementar
    public abstract boolean agregarInicio(T elemento);
    public abstract boolean agregarFinal(T elemento);
}
package com.alogritmos;


public class Medicamento {
    // CÓDIGO ÚNICO DEL MEDICAMENTO
    private String codigo;
    // NOMBRE DEL MEDICAMENTO
    private String nombre;
    // DESCRIPCIÓN O PRINCIPIO ACTIVO
    private String descripcion;
    // CANTIDAD DISPONIBLE EN STOCK
    private int cantidadStock;
    // PRECIO UNITARIO
    private double precio;
    // FECHA DE VENCIMIENTO
    private String fechaVencimiento;
    
    // CONSTRUCTOR COMPLETO
    public Medicamento(String codigo, String nombre, String descripcion, 
                      int cantidadStock, double precio, String fechaVencimiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadStock = cantidadStock;
        this.precio = precio;
        this.fechaVencimiento = fechaVencimiento;
    }
    
    // GETTERS Y SETTERS
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getCantidadStock() { return cantidadStock; }
    public void setCantidadStock(int cantidadStock) { this.cantidadStock = cantidadStock; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    
    // MÉTODO PARA REDUCIR EL STOCK
    public boolean reducirStock(int cantidad) {
        if (cantidadStock >= cantidad) {
            cantidadStock -= cantidad;
            return true;
        }
        return false;
    }
    
    // MÉTODO PARA AUMENTAR EL STOCK
    public void aumentarStock(int cantidad) {
        cantidadStock += cantidad;
    }
    
    // VERIFICAR SI HAY STOCK DISPONIBLE
    public boolean hayStock() {
        return cantidadStock > 0;
    }
    
    // MÉTODO EQUALS PARA COMPARAR MEDICAMENTOS (USANDO CÓDIGO)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        Medicamento otro = (Medicamento) obj;
        return this.codigo.equals(otro.codigo);
    }
    
    // MÉTODO TOSTRING
    @Override
    public String toString() {
        return String.format("Medicamento{Código: %s, Nombre: %s, Stock: %d, Precio: $%.2f}", 
                           codigo, nombre, cantidadStock, precio);
    }
    
    // MÉTODO PARA MOSTRAR INFORMACIÓN DETALLADA
    public void mostrarDetalle() {
        System.out.println("=== INFORMACIÓN DEL MEDICAMENTO ===");
        System.out.println("Código: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Stock disponible: " + cantidadStock);
        System.out.println("Precio: $" + precio);
        System.out.println("Vencimiento: " + fechaVencimiento);
        System.out.println("Estado: " + (hayStock() ? "DISPONIBLE" : "AGOTADO"));
        System.out.println("=================================");
    }
}
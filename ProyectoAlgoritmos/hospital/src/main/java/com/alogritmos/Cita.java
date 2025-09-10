package com.alogritmos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cita {
    // ID ÚNICO DE LA CITA
    private String idCita;
    // ID DEL PACIENTE QUE TIENE LA CITA
    private String idPaciente;
    // NOMBRE DEL DOCTOR QUE ATENDERÁ
    private String nombreDoctor;
    // FECHA Y HORA DE LA CITA
    private LocalDateTime fechaHora;
    // TIPO DE CONSULTA (GENERAL, CARDIOLOGÍA, PEDIATRÍA, ETC.)
    private String tipoConsulta;
    // ESTADO DE LA CITA (PENDIENTE, COMPLETADA, CANCELADA)
    private String estado;
    
    // CONSTRUCTOR COMPLETO
    public Cita(String idCita, String idPaciente, String nombreDoctor, 
                LocalDateTime fechaHora, String tipoConsulta) {
        this.idCita = idCita;
        this.idPaciente = idPaciente;
        this.nombreDoctor = nombreDoctor;
        this.fechaHora = fechaHora;
        this.tipoConsulta = tipoConsulta;
        // POR DEFECTO LA CITA ESTÁ PENDIENTE
        this.estado = "PENDIENTE";
    }
    
    // GETTERS Y SETTERS
    public String getIdCita() { return idCita; }
    public void setIdCita(String idCita) { this.idCita = idCita; }
    public String getIdPaciente() { return idPaciente; }
    public void setIdPaciente(String idPaciente) { this.idPaciente = idPaciente; }
    public String getNombreDoctor() { return nombreDoctor; }
    public void setNombreDoctor(String nombreDoctor) { this.nombreDoctor = nombreDoctor; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getTipoConsulta() { return tipoConsulta; }
    public void setTipoConsulta(String tipoConsulta) { this.tipoConsulta = tipoConsulta; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    // MÉTODO EQUALS PARA COMPARAR CITAS (USANDO ID)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        Cita otra = (Cita) obj;
        return this.idCita.equals(otra.idCita);
    }
    
    // MÉTODO TOSTRING PARA MOSTRAR LA INFORMACIÓN DE LA CITA
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("Cita{ID: %s, Paciente: %s, Doctor: %s, Fecha: %s, Tipo: %s, Estado: %s}", 
                           idCita, idPaciente, nombreDoctor, fechaHora.format(formatter), tipoConsulta, estado);
    }
    
    // MÉTODO PARA MOSTRAR INFORMACIÓN DETALLADA DE LA CITA
    public void mostrarDetalle() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("=== DETALLE DE LA CITA ===");
        System.out.println("ID Cita: " + idCita);
        System.out.println("Paciente: " + idPaciente);
        System.out.println("Doctor: " + nombreDoctor);
        System.out.println("Fecha y Hora: " + fechaHora.format(formatter));
        System.out.println("Tipo: " + tipoConsulta);
        System.out.println("Estado: " + estado);
        System.out.println("========================");
    }
}
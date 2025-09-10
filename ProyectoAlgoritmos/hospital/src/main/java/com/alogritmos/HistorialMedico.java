package com.alogritmos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HistorialMedico {
    // ID ÚNICO DEL REGISTRO MÉDICO
    private String idRegistro;
    // ID DEL PACIENTE AL QUE PERTENECE
    private String idPaciente;
    // FECHA DEL REGISTRO
    private LocalDate fecha;
    // DIAGNÓSTICO REALIZADO
    private String diagnostico;
    // TRATAMIENTO PRESCRITO
    private String tratamiento;
    // DOCTOR QUE REALIZÓ EL REGISTRO
    private String doctor;
    // OBSERVACIONES ADICIONALES
    private String observaciones;
    
    // CONSTRUCTOR COMPLETO
    public HistorialMedico(String idRegistro, String idPaciente, LocalDate fecha,
                          String diagnostico, String tratamiento, String doctor, String observaciones) {
        this.idRegistro = idRegistro;
        this.idPaciente = idPaciente;
        this.fecha = fecha;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.doctor = doctor;
        this.observaciones = observaciones;
    }
    
    // GETTERS Y SETTERS
    public String getIdRegistro() { return idRegistro; }
    public void setIdRegistro(String idRegistro) { this.idRegistro = idRegistro; }
    public String getIdPaciente() { return idPaciente; }
    public void setIdPaciente(String idPaciente) { this.idPaciente = idPaciente; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }
    public String getDoctor() { return doctor; }
    public void setDoctor(String doctor) { this.doctor = doctor; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    
    // MÉTODO EQUALS PARA COMPARAR REGISTROS (USANDO ID)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        HistorialMedico otro = (HistorialMedico) obj;
        return this.idRegistro.equals(otro.idRegistro);
    }
    
    // MÉTODO TOSTRING
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Historial{ID: %s, Paciente: %s, Fecha: %s, Diagnóstico: %s}", 
                           idRegistro, idPaciente, fecha.format(formatter), diagnostico);
    }
    
    // MÉTODO PARA MOSTRAR INFORMACIÓN DETALLADA
    public void mostrarDetalle() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("=== REGISTRO MÉDICO ===");
        System.out.println("ID: " + idRegistro);
        System.out.println("Paciente: " + idPaciente);
        System.out.println("Fecha: " + fecha.format(formatter));
        System.out.println("Doctor: " + doctor);
        System.out.println("Diagnóstico: " + diagnostico);
        System.out.println("Tratamiento: " + tratamiento);
        System.out.println("Observaciones: " + observaciones);
        System.out.println("=====================");
    }
}
package com.alogritmos;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class SistemaHospitalarioCompleto {
    // === GESTIÓN DE PACIENTES (LISTA SIMPLE) ===
    // LISTA PRINCIPAL DE PACIENTES ACTIVOS EN EL HOSPITAL
    private ListaSimple<Paciente> pacientesActivos;
    
    // === GESTIÓN DE HABITACIONES ===
    // GESTOR QUE MANEJA LAS HABITACIONES DEL HOSPITAL
    private GestorHabitaciones gestorHabitaciones;
    
    // === COLA DE EMERGENCIAS (COLA FIFO) ===
    // COLA PARA PACIENTES QUE ESPERAN ATENCIÓN DE EMERGENCIA
    private Cola<Paciente> colaEmergencias;
    
    // === PILA DE ALTAS RECIENTES (PILA LIFO) ===
    // PILA PARA LLEVAR REGISTRO DE PACIENTES DADOS DE ALTA RECIENTEMENTE
    private Pila<Paciente> pilaAltasRecientes;
    
    // === HISTORIAL MÉDICO (LISTA DOBLE) ===
    // LISTA DOBLE PARA NAVEGAR EL HISTORIAL HACIA ADELANTE Y ATRÁS
    private ListaDoble<HistorialMedico> historialGeneral;
    
    // === CITAS PROGRAMADAS (LISTA CIRCULAR) ===
    // LISTA CIRCULAR PARA MANEJAR CITAS QUE SE REPITEN CÍCLICAMENTE
    private ListaCircular<Cita> citasProgramadas;
    
    // === PERSONAL MÉDICO (LISTA DOBLE CIRCULAR) ===
    // LISTA DOBLE CIRCULAR PARA ROTACIÓN DE TURNOS DEL PERSONAL
    private ListaDobleCircular<String> personalMedico;
    
    // === FARMACIA (LISTA SIMPLE) ===
    // INVENTARIO DE MEDICAMENTOS
    private ListaSimple<Medicamento> inventarioFarmacia;
    
    // CONTADORES PARA GENERAR IDS ÚNICOS
    private int contadorCitas;
    private int contadorHistorial;
    
    // CONSTRUCTOR - INICIALIZA TODO EL SISTEMA
    public SistemaHospitalarioCompleto(int pisos, int habitacionesPorPiso) {
        // INICIALIZAR TODAS LAS ESTRUCTURAS DE DATOS
        this.pacientesActivos = new ListaSimple<>();
        this.gestorHabitaciones = new GestorHabitaciones(pisos, habitacionesPorPiso);
        this.colaEmergencias = new Cola<>();
        this.pilaAltasRecientes = new Pila<>();
        this.historialGeneral = new ListaDoble<>();
        this.citasProgramadas = new ListaCircular<>();
        this.personalMedico = new ListaDobleCircular<>();
        this.inventarioFarmacia = new ListaSimple<>();
        
        // INICIALIZAR CONTADORES
        this.contadorCitas = 1000;
        this.contadorHistorial = 5000;
        
        // CARGAR PERSONAL MÉDICO INICIAL
        cargarPersonalInicial();
        // CARGAR MEDICAMENTOS BÁSICOS
        cargarMedicamentosBasicos();
        
        System.out.println(" SISTEMA HOSPITALARIO COMPLETO INICIADO ");
        System.out.println(" Gestión de pacientes activos (Lista Simple)");
        System.out.println(" Cola de emergencias (Cola FIFO)");
        System.out.println(" Historial de altas (Pila LIFO)");
        System.out.println(" Historial médico (Lista Doble)");
        System.out.println(" Citas programadas (Lista Circular)");
        System.out.println(" Personal médico (Lista Doble Circular)");
        System.out.println(" Farmacia (Lista Simple)");
    }
    
    // MÉTODO PARA CARGAR PERSONAL MÉDICO INICIAL
    private void cargarPersonalInicial() {
        personalMedico.agregar("Dr. García - Cardiología");
        personalMedico.agregar("Dra. López - Pediatría");
        personalMedico.agregar("Dr. Martínez - Cirugía");
        personalMedico.agregar("Dra. Rodríguez - Medicina General");
        personalMedico.agregar("Dr. Fernández - Neurología");
    }
    
    // MÉTODO PARA CARGAR MEDICAMENTOS BÁSICOS
    private void cargarMedicamentosBasicos() {
        inventarioFarmacia.agregar(new Medicamento("MED001", "Paracetamol", "Analgésico y antipirético", 100, 2.50, "2025-12-31"));
        inventarioFarmacia.agregar(new Medicamento("MED002", "Ibuprofeno", "Antiinflamatorio", 80, 3.75, "2025-10-15"));
        inventarioFarmacia.agregar(new Medicamento("MED003", "Amoxicilina", "Antibiótico", 50, 8.20, "2025-08-20"));
        inventarioFarmacia.agregar(new Medicamento("MED004", "Omeprazol", "Protector gástrico", 60, 5.40, "2025-11-30"));
    }
    
    // === MÉTODOS PARA PACIENTES NORMALES ===
    
    // REGISTRAR PACIENTE NORMAL (HOSPITALIZACIÓN)
    public boolean registrarPaciente(String id, String nombre) {
        // VERIFICAR QUE EL PACIENTE NO EXISTA
        if (buscarPacientePorId(id) != null) {
            System.out.println(" ERROR: YA EXISTE UN PACIENTE CON ESE ID");
            return false;
        }
        
        // ASIGNAR HABITACIÓN
        String habitacionAsignada = gestorHabitaciones.asignarHabitacion();
        if (habitacionAsignada == null) {
            System.out.println("ERROR: NO HAY HABITACIONES DISPONIBLES");
            return false;
        }
        
        // CREAR Y AGREGAR EL PACIENTE
        Paciente nuevoPaciente = new Paciente(id, nombre, habitacionAsignada);
        pacientesActivos.agregar(nuevoPaciente);
        
        System.out.println(" PACIENTE REGISTRADO EXITOSAMENTE:");
        nuevoPaciente.mostrarInfo();
        
        return true;
    }
    
    // === MÉTODOS PARA EMERGENCIAS ===
    
    // AGREGAR PACIENTE A LA COLA DE EMERGENCIAS
    public boolean agregarEmergencia(String id, String nombre) {
        // VERIFICAR QUE NO ESTÉ YA EN EMERGENCIAS
        Paciente temporal = new Paciente(id, nombre);
        if (colaEmergencias.contiene(temporal)) {
            System.out.println("ERROR: EL PACIENTE YA ESTÁ EN LA COLA DE EMERGENCIAS");
            return false;
        }
        
        // AGREGAR A LA COLA
        colaEmergencias.encolar(temporal);
        System.out.println(" PACIENTE AGREGADO A EMERGENCIAS: " + nombre);
        System.out.println(" Posición en cola: " + colaEmergencias.tamaño());
        
        return true;
    }
    
    // ATENDER SIGUIENTE EMERGENCIA
    public boolean atenderEmergencia() {
        Paciente pacienteEmergencia = colaEmergencias.desencolar();
        
        if (pacienteEmergencia == null) {
            System.out.println("NO HAY PACIENTES EN LA COLA DE EMERGENCIAS");
            return false;
        }
        
        // INTENTAR REGISTRAR EL PACIENTE DESPUÉS DE ATENDER LA EMERGENCIA
        boolean registrado = registrarPaciente(pacienteEmergencia.getId(), 
                                             pacienteEmergencia.getNombre());
        
        if (registrado) {
            System.out.println(" EMERGENCIA ATENDIDA Y PACIENTE HOSPITALIZADO:");
            System.out.println(pacienteEmergencia.getNombre());
        } else {
            System.out.println(" EMERGENCIA ATENDIDA PERO NO SE PUDO HOSPITALIZAR");
            System.out.println(" PACIENTE ENVIADO A CASA: " + pacienteEmergencia.getNombre());
        }
        
        return true;
    }
    
    // MOSTRAR COLA DE EMERGENCIAS
    public void mostrarColaEmergencias() {
        System.out.println("\n === COLA DE EMERGENCIAS === 🚨");
        if (colaEmergencias.estaVacia()) {
            System.out.println(" No hay emergencias pendientes");
        } else {
            colaEmergencias.mostrar();
        }
        System.out.println("==============================\n");
    }
    
    // === MÉTODOS PARA ALTAS ===
    
    // DAR DE ALTA A UN PACIENTE
    public boolean darDeAltaPaciente(String id) {
        Paciente paciente = buscarPacientePorId(id);
        
        if (paciente == null) {
            System.out.println(" ERROR: NO SE ENCONTRÓ EL PACIENTE CON ID: " + id);
            return false;
        }
        
        // LIBERAR HABITACIÓN
        String habitacion = paciente.getHabitacion();
        gestorHabitaciones.liberarHabitacion(habitacion);
        
        // REMOVER DE PACIENTES ACTIVOS
        pacientesActivos.eliminar(paciente);
        
        // AGREGAR A LA PILA DE ALTAS RECIENTES
        pilaAltasRecientes.apilar(paciente);
        
        System.out.println(" PACIENTE DADO DE ALTA:");
        System.out.println(" " + paciente.getNombre() + " - Habitación " + habitacion + " liberada");
        
        return true;
    }
    
    // MOSTRAR ÚLTIMAS ALTAS
    public void mostrarUltimasAltas() {
        System.out.println("\n === ÚLTIMAS ALTAS === ");
        if (pilaAltasRecientes.estaVacia()) {
            System.out.println("No hay altas recientes");
        } else {
            pilaAltasRecientes.mostrar();
        }
        System.out.println("=======================\n");
    }
    
    // RECUPERAR ÚLTIMA ALTA (EN CASO DE ERROR)
    public boolean deshacerUltimaAlta() {
        Paciente ultimaAlta = pilaAltasRecientes.desapilar();
        
        if (ultimaAlta == null) {
            System.out.println(" NO HAY ALTAS PARA DESHACER");
            return false;
        }
        
        // INTENTAR RE-REGISTRAR EL PACIENTE
        boolean reRegistrado = registrarPaciente(ultimaAlta.getId(), ultimaAlta.getNombre());
        
        if (reRegistrado) {
            System.out.println(" ALTA DESHECHA - PACIENTE RE-INGRESADO:");
            System.out.println("👤 " + ultimaAlta.getNombre());
        } else {
            // SI NO SE PUEDE RE-REGISTRAR, DEVOLVERLO A LA PILA
            pilaAltasRecientes.apilar(ultimaAlta);
            System.out.println(" NO SE PUDO DESHACER LA ALTA (SIN HABITACIONES)");
        }
        
        return reRegistrado;
    }
    
    // === MÉTODOS PARA HISTORIAL MÉDICO ===
    
    // AGREGAR REGISTRO AL HISTORIAL
    public boolean agregarHistorial(String idPaciente, String diagnostico, 
                                   String tratamiento, String doctor, String observaciones) {
        // GENERAR ID ÚNICO PARA EL REGISTRO
        String idRegistro = "HIST" + (++contadorHistorial);
        
        // CREAR NUEVO REGISTRO
        HistorialMedico registro = new HistorialMedico(idRegistro, idPaciente, 
                                                      LocalDate.now(), diagnostico, 
                                                      tratamiento, doctor, observaciones);
        
        // AGREGAR AL HISTORIAL GENERAL
        historialGeneral.agregar(registro);
        
        System.out.println(" REGISTRO MÉDICO AGREGADO:");
        registro.mostrarDetalle();
        
        return true;
    }
    
    // MOSTRAR HISTORIAL COMPLETO
    public void mostrarHistorialCompleto() {
        System.out.println("\n === HISTORIAL MÉDICO GENERAL === ");
        if (historialGeneral.estaVacia()) {
            System.out.println("No hay registros médicos");
        } else {
            historialGeneral.mostrar();
        }
        System.out.println("==================================\n");
    }
    
    // BUSCAR HISTORIAL DE UN PACIENTE ESPECÍFICO
    public void buscarHistorialPaciente(String idPaciente) {
        System.out.println("\n === HISTORIAL DEL PACIENTE " + idPaciente + " === ");
        
        // CREAR UNA LISTA TEMPORAL PARA ALMACENAR LOS REGISTROS DEL PACIENTE
        ListaSimple<HistorialMedico> historialPaciente = new ListaSimple<>();
        
        // RECORRER EL HISTORIAL GENERAL Y BUSCAR REGISTROS DEL PACIENTE
        // (AQUÍ SIMULARÍAMOS EL RECORRIDO MANUAL YA QUE NO TENEMOS ITERADOR)
        
        System.out.println("Historial encontrado para el paciente " + idPaciente);
        System.out.println("===============================================\n");
    }
    
    // === MÉTODOS PARA CITAS MÉDICAS ===
    
    // PROGRAMAR NUEVA CITA
    public boolean programarCita(String idPaciente, String nombreDoctor, 
                                String tipoConsulta, int diasDespues, int hora) {
        // GENERAR ID ÚNICO PARA LA CITA
        String idCita = "CITA" + (++contadorCitas);
        
        // CALCULAR FECHA Y HORA DE LA CITA
        LocalDateTime fechaHora = LocalDateTime.now().plusDays(diasDespues).withHour(hora).withMinute(0);
        
        // CREAR NUEVA CITA
        Cita nuevaCita = new Cita(idCita, idPaciente, nombreDoctor, fechaHora, tipoConsulta);
        
        // AGREGAR A LA LISTA CIRCULAR DE CITAS
        citasProgramadas.agregar(nuevaCita);
        
        System.out.println(" CITA PROGRAMADA EXITOSAMENTE:");
        nuevaCita.mostrarDetalle();
        
        return true;
    }
    
    // MOSTRAR TODAS LAS CITAS PROGRAMADAS
    public void mostrarCitasProgramadas() {
        System.out.println("\n === CITAS PROGRAMADAS === ");
        if (citasProgramadas.estaVacia()) {
            System.out.println("No hay citas programadas");
        } else {
            citasProgramadas.mostrar();
        }
        System.out.println("============================\n");
    }
    
    // CANCELAR UNA CITA
    public boolean cancelarCita(String idCita) {
        // CREAR CITA TEMPORAL PARA BUSCAR
        Cita temporal = new Cita(idCita, "", "", LocalDateTime.now(), "");
        
        if (citasProgramadas.eliminar(temporal)) {
            System.out.println(" CITA CANCELADA: " + idCita);
            return true;
        } else {
            System.out.println(" NO SE ENCONTRÓ LA CITA: " + idCita);
            return false;
        }
    }
    
    // === MÉTODOS PARA PERSONAL MÉDICO ===
    
    // AGREGAR NUEVO PERSONAL MÉDICO
    public boolean agregarPersonalMedico(String nombreDoctor) {
        personalMedico.agregar(nombreDoctor);
        System.out.println(" PERSONAL MÉDICO AGREGADO: " + nombreDoctor);
        return true;
    }
    
    // MOSTRAR PERSONAL MÉDICO EN ROTACIÓN
    public void mostrarPersonalMedico() {
        System.out.println("\n === PERSONAL MÉDICO === ");
        if (personalMedico.estaVacia()) {
            System.out.println("No hay personal registrado");
        } else {
            personalMedico.mostrar();
        }
        System.out.println("=========================\n");
    }
    
    // OBTENER SIGUIENTE DOCTOR EN TURNO (ROTACIÓN CIRCULAR)
    public String obtenerSiguienteDoctor() {
        // EN UNA IMPLEMENTACIÓN REAL, AQUÍ ROTARÍAMOS AL SIGUIENTE DOCTOR
        // POR SIMPLICIDAD, RETORNAMOS UN MENSAJE
        return "Dr. García - Cardiología (Siguiente en turno)";
    }
    
    // === MÉTODOS PARA FARMACIA ===
    
    // AGREGAR MEDICAMENTO AL INVENTARIO
    public boolean agregarMedicamento(String codigo, String nombre, String descripcion, 
                                     int cantidad, double precio, String fechaVencimiento) {
        Medicamento nuevoMed = new Medicamento(codigo, nombre, descripcion, 
                                             cantidad, precio, fechaVencimiento);
        
        // VERIFICAR SI YA EXISTE
        if (inventarioFarmacia.contiene(nuevoMed)) {
            System.out.println(" MEDICAMENTO YA EXISTE EN INVENTARIO");
            return false;
        }
        
        inventarioFarmacia.agregar(nuevoMed);
        System.out.println(" MEDICAMENTO AGREGADO AL INVENTARIO:");
        nuevoMed.mostrarDetalle();
        
        return true;
    }
    
    // DISPENSAR MEDICAMENTO
    public boolean dispensarMedicamento(String codigo, int cantidad) {
        // BUSCAR EL MEDICAMENTO
        Medicamento temporal = new Medicamento(codigo, "", "", 0, 0.0, "");
        Medicamento medicamento = inventarioFarmacia.buscar(temporal);
        
        if (medicamento == null) {
            System.out.println(" MEDICAMENTO NO ENCONTRADO: " + codigo);
            return false;
        }
        
        // VERIFICAR STOCK
        if (!medicamento.reducirStock(cantidad)) {
            System.out.println(" STOCK INSUFICIENTE. Disponible: " + medicamento.getCantidadStock());
            return false;
        }
        
        System.out.println(" MEDICAMENTO DISPENSADO:");
        System.out.println(" " + medicamento.getNombre() + " - Cantidad: " + cantidad);
        System.out.println(" Stock restante: " + medicamento.getCantidadStock());
        
        return true;
    }
    
    // MOSTRAR INVENTARIO DE FARMACIA
    public void mostrarInventarioFarmacia() {
        System.out.println("\n === INVENTARIO FARMACIA === ");
        if (inventarioFarmacia.estaVacia()) {
            System.out.println("No hay medicamentos en inventario");
        } else {
            inventarioFarmacia.mostrar();
        }
        System.out.println("==============================\n");
    }
    
    // RESTOCK DE MEDICAMENTO
    public boolean restockMedicamento(String codigo, int cantidad) {
        Medicamento temporal = new Medicamento(codigo, "", "", 0, 0.0, "");
        Medicamento medicamento = inventarioFarmacia.buscar(temporal);
        
        if (medicamento == null) {
            System.out.println(" MEDICAMENTO NO ENCONTRADO: " + codigo);
            return false;
        }
        
        medicamento.aumentarStock(cantidad);
        System.out.println("📦 RESTOCK REALIZADO:");
        System.out.println("💊 " + medicamento.getNombre() + " - Agregadas: " + cantidad);
        System.out.println("📈 Nuevo stock: " + medicamento.getCantidadStock());
        
        return true;
    }
    
    // === MÉTODOS AUXILIARES EXISTENTES ===
    
    // BUSCAR PACIENTE POR ID
    public Paciente buscarPacientePorId(String id) {
        Paciente temporal = new Paciente();
        temporal.setId(id);
        return pacientesActivos.buscar(temporal);
    }
    
    // MOSTRAR TODOS LOS PACIENTES ACTIVOS
    public void mostrarTodosLosPacientes() {
        System.out.println("\n👥 === PACIENTES ACTIVOS === 👥");
        if (pacientesActivos.estaVacia()) {
            System.out.println("No hay pacientes registrados");
        } else {
            pacientesActivos.mostrar();
            System.out.println("TOTAL DE PACIENTES: " + pacientesActivos.tamaño());
        }
        System.out.println("===============================\n");
    }
    
    // MOSTRAR ESTADÍSTICAS COMPLETAS
    public void mostrarEstadisticasCompletas() {
        System.out.println("\n=== ESTADÍSTICAS DEL HOSPITAL === ");
        System.out.println(" Pacientes activos: " + pacientesActivos.tamaño());
        System.out.println(" Emergencias pendientes: " + colaEmergencias.tamaño());
        System.out.println(" Altas recientes: " + pilaAltasRecientes.tamaño());
        System.out.println(" Habitaciones libres: " + gestorHabitaciones.contarHabitacionesLibres());
        System.out.println(" Registros médicos: " + historialGeneral.tamaño());
        System.out.println(" Citas programadas: " + citasProgramadas.tamaño());
        System.out.println(" Personal médico: " + personalMedico.tamaño());
        System.out.println(" Medicamentos en inventario: " + inventarioFarmacia.tamaño());
        System.out.println("=====================================\n");
    }
    
    // MOSTRAR MAPA DEL HOSPITAL
    public void mostrarMapaHospital() {
        gestorHabitaciones.mostrarMapaHospital();
    }
    
    // CONSULTAR PACIENTE
    public void consultarPaciente(String id) {
        Paciente paciente = buscarPacientePorId(id);
        
        if (paciente != null) {
            paciente.mostrarInfo();
        } else {
            System.out.println(" NO SE ENCONTRÓ PACIENTE CON ID: " + id);
        }
    }
    
    // OBTENER TOTAL DE PACIENTES
    public int getTotalPacientes() {
        return pacientesActivos.tamaño();
    }
    
    // VERIFICAR SI EL HOSPITAL ESTÁ LLENO
    public boolean estaLleno() {
        return gestorHabitaciones.contarHabitacionesLibres() == 0;
    }
}
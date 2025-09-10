package com.alogritmos;

import java.util.Scanner;

public class MainCompleto {

    // VARIABLE GLOBAL DEL SISTEMA HOSPITALARIO COMPLETO
    static SistemaHospitalarioCompleto hospital;
    static Scanner sc = new Scanner(System.in);

    // MÉTODO PARA LIMPIAR LA CONSOLA
    public static void cleanConsole(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    // INTERFAZ DE USUARIO PRINCIPAL
    public static int ui() throws InterruptedException {
        int answer; 
        boolean repeat;
        do{
            repeat = false;
            cleanConsole(); // LIMPIAR CONSOLA
            
            // MOSTRAR EL MENÚ PRINCIPAL
            System.out.println("=== SISTEMA HOSPITALARIO COMPLETO ===");
            System.out.println("=== GESTION DE PACIENTES ===");
            System.out.println("1.  Registrar paciente (hospitalización)");
            System.out.println("2.  Dar de alta paciente");
            System.out.println("3.  Consultar paciente");
            System.out.println("4.  Ver todos los pacientes activos");
            System.out.println();
            System.out.println("=== EMERGENCIAS ===");
            System.out.println("5.  Agregar paciente a emergencias");
            System.out.println("6.  Atender siguiente emergencia");
            System.out.println("7.  Ver cola de emergencias");
            System.out.println();
            System.out.println("=== HISTORIAL Y ALTAS ===");
            System.out.println("8.  Ver últimas altas");
            System.out.println("9.  Deshacer última alta");
            System.out.println("10. Agregar registro médico");
            System.out.println("11. Ver historial médico completo");
            System.out.println();
            System.out.println("=== CITAS MÉDICAS ===");
            System.out.println("12. Programar cita");
            System.out.println("13. Ver citas programadas");
            System.out.println("14. Cancelar cita");
            System.out.println();
            System.out.println("=== PERSONAL MÉDICO ===");
            System.out.println("15. Agregar personal médico");
            System.out.println("16. Ver personal médico");
            System.out.println();
            System.out.println("=== FARMACIA ===");
            System.out.println("17. Agregar medicamento");
            System.out.println("18. Dispensar medicamento");
            System.out.println("19. Restock medicamento");
            System.out.println("20. Ver inventario farmacia");
            System.out.println();
            System.out.println("=== INFORMACIÓN GENERAL ===");
            System.out.println("21. Ver mapa del hospital");
            System.out.println("22. Ver estadísticas completas");
            System.out.println("23. Simulación automática completa");
            System.out.println();
            System.out.println("0.  Salir");
            System.out.println("=========================================");
            System.out.print("Selecciona una opción: ");
            
            answer = sc.nextInt();
            sc.nextLine(); // LIMPIAR BUFFER
            
            // VALIDAR LA RESPUESTA
            if(answer > 23 || answer < 0){
                System.out.println("ERROR: Ingresa un valor entre 0 y 23");
                Thread.sleep(1500);
                repeat = true;
            }
        }while(repeat);
        return answer;
    }

    // OPCIÓN 1: REGISTRAR PACIENTE
    public static void registrarPaciente() throws InterruptedException {
        cleanConsole();
        System.out.println("=== REGISTRAR NUEVO PACIENTE ===");
        
        System.out.print("Ingresa el ID del paciente: ");
        String id = sc.nextLine();
        
        System.out.print("Ingresa el nombre del paciente: ");
        String nombre = sc.nextLine();
        
        System.out.println("\nPROCESANDO REGISTRO...");
        Thread.sleep(1000);
        
        boolean registrado = hospital.registrarPaciente(id, nombre);
        
        if (registrado) {
            System.out.println("\nPACIENTE REGISTRADO EXITOSAMENTE!");
        } else {
            System.out.println("\nNO SE PUDO REGISTRAR EL PACIENTE");
        }
        
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 2: DAR DE ALTA PACIENTE
    public static void darDeAltaPaciente() throws InterruptedException {
        cleanConsole();
        System.out.println("=== DAR DE ALTA PACIENTE ===");
        
        if (hospital.getTotalPacientes() == 0) {
            System.out.println("NO HAY PACIENTES REGISTRADOS");
            Thread.sleep(2000);
            return;
        }
        
        System.out.print("Ingresa el ID del paciente a dar de alta: ");
        String id = sc.nextLine();
        
        System.out.println("\nPROCESANDO ALTA...");
        Thread.sleep(1000);
        
        boolean dado = hospital.darDeAltaPaciente(id);
        
        if (dado) {
            System.out.println("\nPACIENTE DADO DE ALTA EXITOSAMENTE!");
        } else {
            System.out.println("\nNO SE PUDO DAR DE ALTA AL PACIENTE");
        }
        
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 3: CONSULTAR PACIENTE
    public static void consultarPaciente() throws InterruptedException {
        cleanConsole();
        System.out.println("=== CONSULTAR PACIENTE ===");
        
        if (hospital.getTotalPacientes() == 0) {
            System.out.println("NO HAY PACIENTES REGISTRADOS");
            Thread.sleep(2000);
            return;
        }
        
        System.out.print("Ingresa el ID del paciente: ");
        String id = sc.nextLine();
        
        System.out.println("\nBUSCANDO PACIENTE...");
        Thread.sleep(800);
        
        hospital.consultarPaciente(id);
        
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 4: VER TODOS LOS PACIENTES
    public static void verTodosPacientes() throws InterruptedException {
        cleanConsole();
        hospital.mostrarTodosLosPacientes();
        System.out.println("Presiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 5: AGREGAR PACIENTE A EMERGENCIAS
    public static void agregarEmergencia() throws InterruptedException {
        cleanConsole();
        System.out.println("=== AGREGAR EMERGENCIA ===");
        
        System.out.print("Ingresa el ID del paciente: ");
        String id = sc.nextLine();
        
        System.out.print("Ingresa el nombre del paciente: ");
        String nombre = sc.nextLine();
        
        System.out.println("\nAGREGANDO A EMERGENCIAS...");
        Thread.sleep(1000);
        
        boolean agregado = hospital.agregarEmergencia(id, nombre);
        
        if (agregado) {
            System.out.println("\nPACIENTE AGREGADO A EMERGENCIAS!");
        } else {
            System.out.println("\nNO SE PUDO AGREGAR A EMERGENCIAS");
        }
        
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 6: ATENDER SIGUIENTE EMERGENCIA
    public static void atenderEmergencia() throws InterruptedException {
        cleanConsole();
        System.out.println("=== ATENDER EMERGENCIA ===");
        
        System.out.println("ATENDIENDO SIGUIENTE EMERGENCIA...");
        Thread.sleep(1500);
        
        hospital.atenderEmergencia();
        
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 7: VER COLA DE EMERGENCIAS
    public static void verColaEmergencias() throws InterruptedException {
        cleanConsole();
        hospital.mostrarColaEmergencias();
        System.out.println("Presiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 8: VER ÚLTIMAS ALTAS
    public static void verUltimasAltas() throws InterruptedException {
        cleanConsole();
        hospital.mostrarUltimasAltas();
        System.out.println("Presiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 9: DESHACER ÚLTIMA ALTA
    public static void deshacerUltimaAlta() throws InterruptedException {
        cleanConsole();
        System.out.println("=== DESHACER ÚLTIMA ALTA ===");
        
        System.out.println("PROCESANDO...");
        Thread.sleep(1000);
        
        boolean deshecho = hospital.deshacerUltimaAlta();
        
        if (deshecho) {
            System.out.println("\nALTA DESHECHA EXITOSAMENTE!");
        } else {
            System.out.println("\nNO SE PUDO DESHACER LA ALTA");
        }
        
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 10: AGREGAR REGISTRO MÉDICO
    public static void agregarRegistroMedico() throws InterruptedException {
        cleanConsole();
        System.out.println("=== AGREGAR REGISTRO MÉDICO ===");
        
        System.out.print("ID del paciente: ");
        String idPaciente = sc.nextLine();
        
        System.out.print("Diagnóstico: ");
        String diagnostico = sc.nextLine();
        
        System.out.print("Tratamiento: ");
        String tratamiento = sc.nextLine();
        
        System.out.print("Doctor: ");
        String doctor = sc.nextLine();
        
        System.out.print("Observaciones: ");
        String observaciones = sc.nextLine();
        
        System.out.println("\nGUARDANDO REGISTRO...");
        Thread.sleep(1000);
        
        hospital.agregarHistorial(idPaciente, diagnostico, tratamiento, doctor, observaciones);
        
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 11: VER HISTORIAL MÉDICO
    public static void verHistorialMedico() throws InterruptedException {
        cleanConsole();
        hospital.mostrarHistorialCompleto();
        System.out.println("Presiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 12: PROGRAMAR CITA
    public static void programarCita() throws InterruptedException {
        cleanConsole();
        System.out.println("=== PROGRAMAR CITA ===");
        
        System.out.print("ID del paciente: ");
        String idPaciente = sc.nextLine();
        
        System.out.print("Nombre del doctor: ");
        String nombreDoctor = sc.nextLine();
        
        System.out.print("Tipo de consulta: ");
        String tipoConsulta = sc.nextLine();
        
        System.out.print("Días desde hoy: ");
        int dias = sc.nextInt();
        
        System.out.print("Hora (formato 24h): ");
        int hora = sc.nextInt();
        sc.nextLine(); // LIMPIAR BUFFER
        
        System.out.println("\nPROGRAMANDO CITA...");
        Thread.sleep(1000);
        
        hospital.programarCita(idPaciente, nombreDoctor, tipoConsulta, dias, hora);
        
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 13: VER CITAS PROGRAMADAS
    public static void verCitasProgramadas() throws InterruptedException {
        cleanConsole();
        hospital.mostrarCitasProgramadas();
        System.out.println("Presiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 14: CANCELAR CITA
    public static void cancelarCita() throws InterruptedException {
        cleanConsole();
        System.out.println("=== CANCELAR CITA ===");
        
        System.out.print("ID de la cita a cancelar: ");
        String idCita = sc.nextLine();
        
        System.out.println("\n🔄 CANCELANDO CITA...");
        Thread.sleep(1000);
        
        boolean cancelada = hospital.cancelarCita(idCita);
        
        if (cancelada) {
            System.out.println("\nCITA CANCELADA EXITOSAMENTE!");
        } else {
            System.out.println("\nNO SE PUDO CANCELAR LA CITA");
        }
        
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 15: AGREGAR PERSONAL MÉDICO
    public static void agregarPersonalMedico() throws InterruptedException {
        cleanConsole();
        System.out.println("=== AGREGAR PERSONAL MÉDICO ===");
        
        System.out.print("Nombre y especialidad del doctor: ");
        String nombreDoctor = sc.nextLine();
        
        System.out.println("\nAGREGANDO PERSONAL...");
        Thread.sleep(1000);
        
        hospital.agregarPersonalMedico(nombreDoctor);
        
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 16: VER PERSONAL MÉDICO
    public static void verPersonalMedico() throws InterruptedException {
        cleanConsole();
        hospital.mostrarPersonalMedico();
        System.out.println("Presiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 17: AGREGAR MEDICAMENTO
    public static void agregarMedicamento() throws InterruptedException {
        cleanConsole();
        System.out.println("=== AGREGAR MEDICAMENTO ===");
        
        System.out.print("Código del medicamento: ");
        String codigo = sc.nextLine();
        
        System.out.print("Nombre del medicamento: ");
        String nombre = sc.nextLine();
        
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        
        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();
        
        System.out.print("Precio: ");
        double precio = sc.nextDouble();
        sc.nextLine(); // LIMPIAR BUFFER
        
        System.out.print("Fecha de vencimiento (DD/MM/YYYY): ");
        String fechaVenc = sc.nextLine();
        
        System.out.println("\nAGREGANDO MEDICAMENTO...");
        Thread.sleep(1000);
        
        hospital.agregarMedicamento(codigo, nombre, descripcion, cantidad, precio, fechaVenc);
        
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 18: DISPENSAR MEDICAMENTO
    public static void dispensarMedicamento() throws InterruptedException {
        cleanConsole();
        System.out.println(" === DISPENSAR MEDICAMENTO === ");
        
        System.out.print("Código del medicamento: ");
        String codigo = sc.nextLine();
        
        System.out.print("Cantidad a dispensar: ");
        int cantidad = sc.nextInt();
        sc.nextLine(); // LIMPIAR BUFFER
        
        System.out.println("\n DISPENSANDO MEDICAMENTO...");
        Thread.sleep(1000);
        
        hospital.dispensarMedicamento(codigo, cantidad);
        
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 19: RESTOCK MEDICAMENTO
    public static void restockMedicamento() throws InterruptedException {
        cleanConsole();
        System.out.println(" === RESTOCK MEDICAMENTO === ");
        
        System.out.print("Código del medicamento: ");
        String codigo = sc.nextLine();
        
        System.out.print("Cantidad a agregar: ");
        int cantidad = sc.nextInt();
        sc.nextLine(); // LIMPIAR BUFFER
        
        System.out.println("\nREALIZANDO RESTOCK...");
        Thread.sleep(1000);
        
        hospital.restockMedicamento(codigo, cantidad);
        
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 20: VER INVENTARIO FARMACIA
    public static void verInventarioFarmacia() throws InterruptedException {
        cleanConsole();
        hospital.mostrarInventarioFarmacia();
        System.out.println("Presiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 21: VER MAPA DEL HOSPITAL
    public static void verMapaHospital() throws InterruptedException {
        cleanConsole();
        hospital.mostrarMapaHospital();
        System.out.println("Presiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 22: VER ESTADÍSTICAS COMPLETAS
    public static void verEstadisticas() throws InterruptedException {
        cleanConsole();
        hospital.mostrarEstadisticasCompletas();
        
        if (hospital.estaLleno()) {
            System.out.println(" ¡ATENCIÓN: EL HOSPITAL ESTÁ LLENO! ");
        }
        
        System.out.println("Presiona ENTER para continuar...");
        sc.nextLine();
    }

    // OPCIÓN 23: SIMULACIÓN AUTOMÁTICA COMPLETA
    public static void simulacionCompleta() throws InterruptedException {
        cleanConsole();
        System.out.println(" === SIMULACIÓN AUTOMÁTICA COMPLETA === ");
        System.out.println("Iniciando simulación de todas las funcionalidades...\n");
        
        // SIMULACIÓN DE EMERGENCIAS
        System.out.println(" === SIMULANDO EMERGENCIAS ===");
        hospital.agregarEmergencia("EMG001", "Juan Urgente");
        Thread.sleep(1000);
        hospital.agregarEmergencia("EMG002", "María Crisis");
        Thread.sleep(1000);
        hospital.agregarEmergencia("EMG003", "Pedro Emergencia");
        Thread.sleep(1000);
        
        hospital.mostrarColaEmergencias();
        Thread.sleep(2000);
        
        // ATENDER EMERGENCIAS
        System.out.println(" === ATENDIENDO EMERGENCIAS ===");
        hospital.atenderEmergencia();
        Thread.sleep(1500);
        hospital.atenderEmergencia();
        Thread.sleep(1500);
        
        // REGISTRAR PACIENTES NORMALES
        System.out.println(" === REGISTRANDO PACIENTES NORMALES ===");
        hospital.registrarPaciente("PAC001", "Ana González");
        Thread.sleep(1000);
        hospital.registrarPaciente("PAC002", "Carlos López");
        Thread.sleep(1000);
        
        // AGREGAR REGISTROS MÉDICOS
        System.out.println(" === AGREGANDO HISTORIALES ===");
        hospital.agregarHistorial("PAC001", "Hipertensión", "Medicamento antihipertensivo", "Dr. García", "Paciente estable");
        Thread.sleep(1000);
        hospital.agregarHistorial("EMG001", "Apendicitis", "Cirugía de emergencia", "Dr. Martínez", "Operación exitosa");
        Thread.sleep(1000);
        
        // PROGRAMAR CITAS
        System.out.println(" === PROGRAMANDO CITAS ===");
        hospital.programarCita("PAC001", "Dr. López", "Control", 7, 10);
        Thread.sleep(1000);
        hospital.programarCita("PAC002", "Dra. Rodríguez", "Pediatría", 3, 14);
        Thread.sleep(1000);
        // DISPENSAR MEDICAMENTOS
        System.out.println(" === DISPENSANDO MEDICAMENTOS ===");
        hospital.dispensarMedicamento("MED001", 5);
        Thread.sleep(1000);
        hospital.dispensarMedicamento("MED003", 2);
        Thread.sleep(1000);
        
        // AGREGAR PERSONAL MÉDICO
        System.out.println(" === AGREGANDO PERSONAL ===");
        hospital.agregarPersonalMedico("Dr. Nuevo - Dermatología");
        Thread.sleep(1000);
        
        // DAR ALTAS
        System.out.println(" === DANDO ALTAS ===");
        hospital.darDeAltaPaciente("EMG001");
        Thread.sleep(1500);
        hospital.darDeAltaPaciente("PAC001");
        Thread.sleep(1500);
        
        // MOSTRAR ESTADO FINAL
        System.out.println(" === ESTADO FINAL DESPUÉS DE LA SIMULACIÓN ===");
        hospital.mostrarEstadisticasCompletas();
        Thread.sleep(2000);
        
        hospital.mostrarMapaHospital();
        Thread.sleep(2000);
        
        hospital.mostrarUltimasAltas();
        Thread.sleep(2000);
        
        // DEMOSTRAR FUNCIONALIDAD DE DESHACER
        System.out.println(" === DEMOSTRANDO DESHACER ALTA ===");
        hospital.deshacerUltimaAlta();
        Thread.sleep(2000);
        
        System.out.println("\n ¡SIMULACIÓN COMPLETADA! ");
        System.out.println("Se han demostrado todas las estructuras de datos:");
        System.out.println("✅ Lista Simple (Pacientes y Medicamentos)");
        System.out.println("✅ Cola FIFO (Emergencias)");
        System.out.println("✅ Pila LIFO (Altas Recientes)");
        System.out.println("✅ Lista Doble (Historial Médico)");
        System.out.println("✅ Lista Circular (Citas Programadas)");
        System.out.println("✅ Lista Doble Circular (Personal Médico)");
        
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    // MÉTODO PRINCIPAL
    public static void main(String[] args) throws InterruptedException {
        
        // INICIALIZAR EL SISTEMA HOSPITALARIO COMPLETO
        System.out.println("=== BIENVENIDO AL SISTEMA HOSPITALARIO COMPLETO === 🏥");
        System.out.println("Inicializando sistema...");
        Thread.sleep(2000);
        
        // CREAR UN HOSPITAL CON 4 PISOS Y 6 HABITACIONES POR PISO (24 HABITACIONES TOTAL)
        hospital = new SistemaHospitalarioCompleto(4, 6);
        
        System.out.println("\nSistema listo para usar!");
        System.out.println("ESTRUCTURAS DE DATOS IMPLEMENTADAS:");
        System.out.println("   Lista Simple: Pacientes activos y medicamentos");
        System.out.println("   Cola FIFO: Emergencias pendientes");
        System.out.println("   Pila LIFO: Altas recientes (con deshacer)");
        System.out.println("   Lista Doble: Historial médico (navegación bidireccional)");
        System.out.println("   Lista Circular: Citas programadas");
        System.out.println("   Lista Doble Circular: Personal médico en rotación");
        Thread.sleep(3000);
        
        int answer;
        boolean continuar = true;
        
        // BUCLE PRINCIPAL DEL PROGRAMA
        while (continuar) {
            answer = ui();
            
            try {
                switch (answer) {
                    case 1:
                        registrarPaciente();
                        break;
                    case 2:
                        darDeAltaPaciente();
                        break;
                    case 3:
                        consultarPaciente();
                        break;
                    case 4:
                        verTodosPacientes();
                        break;
                    case 5:
                        agregarEmergencia();
                        break;
                    case 6:
                        atenderEmergencia();
                        break;
                    case 7:
                        verColaEmergencias();
                        break;
                    case 8:
                        verUltimasAltas();
                        break;
                    case 9:
                        deshacerUltimaAlta();
                        break;
                    case 10:
                        agregarRegistroMedico();
                        break;
                    case 11:
                        verHistorialMedico();
                        break;
                    case 12:
                        programarCita();
                        break;
                    case 13:
                        verCitasProgramadas();
                        break;
                    case 14:
                        cancelarCita();
                        break;
                    case 15:
                        agregarPersonalMedico();
                        break;
                    case 16:
                        verPersonalMedico();
                        break;
                    case 17:
                        agregarMedicamento();
                        break;
                    case 18:
                        dispensarMedicamento();
                        break;
                    case 19:
                        restockMedicamento();
                        break;
                    case 20:
                        verInventarioFarmacia();
                        break;
                    case 21:
                        verMapaHospital();
                        break;
                    case 22:
                        verEstadisticas();
                        break;
                    case 23:
                        simulacionCompleta();
                        break;
                    case 0:
                        cleanConsole();
                        System.out.println(" === CERRANDO SISTEMA HOSPITALARIO === 🏥");
                        System.out.println(" Guardando datos...");
                        Thread.sleep(1000);
                        
                        // MOSTRAR ESTADÍSTICAS FINALES
                        System.out.println("\n ESTADÍSTICAS FINALES:");
                        hospital.mostrarEstadisticasCompletas();
                        
                        System.out.println(" Datos guardados correctamente");
                        System.out.println(" ESTRUCTURAS DE DATOS UTILIZADAS:");
                        System.out.println("    Lista Simple (pacientes y medicamentos)");
                        System.out.println("    Lista Circular (citas programadas)");
                        System.out.println("    Lista Doblemente Ligada (historial médico)");
                        System.out.println("    Lista Doble Circular (personal médico)");
                        System.out.println("    Pila LIFO (altas recientes)");
                        System.out.println("    Cola FIFO (emergencias)");
                        System.out.println("\n ¡Gracias por usar el Sistema Hospitalario Completo!");
                        System.out.println(" Desarrollado con todas las estructuras de datos solicitadas");
                        continuar = false;
                        break;
                    default:
                        System.out.println("❌ Opción no válida");
                        Thread.sleep(1000);
                        break;
                }
            } catch (Exception e) {
                System.out.println(" ERROR: " + e.getMessage());
                System.out.println("Presiona ENTER para continuar...");
                sc.nextLine();
            }
        }
        
        sc.close();
    }
}
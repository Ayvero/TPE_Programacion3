package TPE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Servicios servicios = new Servicios("./src/TPE_DataSet/Procesadores.csv", "./src/TPE_DataSet/Tareas.csv");
/*
 
        // Obtener la lista de tareas
        HashMap<String, MyTask> tareas = servicios.getTasks();

        // Iterar sobre las tareas y realizar operaciones según sea necesario
        for (MyTask tarea : tareas.values()) {
            // Acceder a los campos de la tarea
            String id = tarea.getId();
            String nombre = tarea.getNombre();
            int tiempoEjecucion = tarea.getTiempoEjecucion();
            boolean esCritica = tarea.isCritica();
            int nivelPrioridad = tarea.getNivelPrioridad();

            // Realizar operaciones con los datos de la tarea, por ejemplo:
            if (esCritica) {
                System.out.println("La tarea " + nombre + " es crítica.");
            } else {
                System.out.println("La tarea " + nombre + " no es crítica.");
            }
        }
    }
   */
    
    // segunda prueba-----------------------

            // Crear una instancia de Servicios
          

            // Probar el servicio 1: Obtener información de una tarea por ID
            String tareaID = "T1"; // Suponiendo que T1 es un ID válido en Tareas.csv
            MyTask tarea = servicios.servicio1(tareaID);
            if (tarea != null) {
                System.out.println("Información de la tarea " + tareaID + ":");
                System.out.println("Nombre: " + tarea.getNombre());
                System.out.println("Tiempo de ejecución: " + tarea.getTiempoEjecucion());
                System.out.println("Es crítica: " + tarea.isCritica());
                System.out.println("Nivel de prioridad: " + tarea.getNivelPrioridad());
            } else {
                System.out.println("No se encontró la tarea con ID " + tareaID);
            }

            // Probar el servicio 2: Obtener todas las tareas críticas o no críticas
            boolean esCritica = true; // Cambia esto para probar tareas críticas o no críticas
            List<MyTask> tareasCriticas = servicios.servicio2(esCritica);
            System.out.println("Tareas " + (esCritica ? "críticas" : "no críticas") + ":");
            for (MyTask t : tareasCriticas) {
                System.out.println(t.getNombre() + " (ID: " + t.getId() + ")");
            }

            // Probar el servicio 3: Obtener todas las tareas entre dos niveles de prioridad
            int prioridadInferior = 10;
            int prioridadSuperior = 50;
            List<MyTask> tareasEnRango = servicios.servicio3(prioridadInferior, prioridadSuperior);
            System.out.println("Tareas entre las prioridades " + prioridadInferior + " y " + prioridadSuperior + ":");
            for (MyTask t : tareasEnRango) {
                System.out.println(t.getNombre() + " (ID: " + t.getId() + ", Prioridad: " + t.getNivelPrioridad() + ")");
            }
            
            //---------------segunda parte----------------------
            
         // Leer los datos de los archivos
            CSVReader reader = new CSVReader();
            reader.readProcessors("./src/TPE_DataSet/Procesadores.csv");
            reader.readTasks("./src/TPE_DataSet/Tareas.csv");

            // Obtener las listas de procesadores y tareas
            List<Processor> processors = new ArrayList<>(reader.getProcessors().values());
            List<MyTask> tasks = new ArrayList<>(reader.getTasks().values());

            // Parámetro X para procesadores no refrigerados
            int X = 100; // Ajustar según sea necesario

            // Crear una instancia de TaskAssignment
            TaskAssignment taskAssignment = new TaskAssignment(processors, tasks, X);

            // Asignación usando Backtracking
            Map<Processor, List<MyTask>> backtrackingAssignment = taskAssignment.assignTasksBacktracking();
            if (backtrackingAssignment != null) {
                System.out.println("Asignación Backtracking:");
                printAssignment(backtrackingAssignment);
                System.out.println("Tiempo final de ejecución: " + taskAssignment.getFinalExecutionTime(backtrackingAssignment));
            } else {
                System.out.println("No se encontró una asignación válida usando Backtracking.");
            }

            // Asignación usando Greedy
            Map<Processor, List<MyTask>> greedyAssignment = taskAssignment.assignTasksGreedy();
            System.out.println("\nAsignación Greedy:");
            printAssignment(greedyAssignment);
            System.out.println("Tiempo final de ejecución: " + taskAssignment.getFinalExecutionTime(greedyAssignment));
        }

        private static void printAssignment(Map<Processor, List<MyTask>> assignment) {
            for (Map.Entry<Processor, List<MyTask>> entry : assignment.entrySet()) {
                Processor p = entry.getKey();
                List<MyTask> assignedTasks = entry.getValue();
                System.out.println("Procesador " + p.getId() + " (" + p.getCodigo() + "):");
                for (MyTask task : assignedTasks) {
                    System.out.println("  - Tarea " + task.getId() + " (" + task.getNombre() + ")");
                }
            }
        }
            
            
        }
    
    
    
    
    
    

    



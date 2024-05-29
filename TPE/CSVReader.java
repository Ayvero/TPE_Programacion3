package TPE;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CSVReader {
    private HashMap<String, Processor> processors;
    private HashMap<String, MyTask> tasks;
    private TaskBinaryTree tasksByPriority;  // Usar TaskBinaryTree para las tareas por prioridad

    public CSVReader() {
        this.processors = new HashMap<>();
        this.tasks = new HashMap<>();
        this.tasksByPriority = new TaskBinaryTree();
    }

    public void readTasks(String taskPath) {
        File file = new File(taskPath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String id = parts[0].trim();
                String nombre = parts[1].trim();
                int tiempoEjecucion = Integer.parseInt(parts[2].trim());
                boolean critica = Boolean.parseBoolean(parts[3].trim());
                int nivelPrioridad = Integer.parseInt(parts[4].trim());
                MyTask tarea = new MyTask(id, nombre, tiempoEjecucion, critica, nivelPrioridad);
                tasks.put(id, tarea);
                tasksByPriority.insert(tarea);  // Insertar la tarea en el árbol binario
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readProcessors(String processorPath) {
        File file = new File(processorPath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String id = parts[0].trim();
                String codigo = parts[1].trim();
                boolean refrigerado = Boolean.parseBoolean(parts[2].trim());
                int anio = Integer.parseInt(parts[3].trim());
                Processor procesador = new Processor(id, codigo, refrigerado, anio);
                processors.put(id, procesador);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, MyTask> getTasks() {
        return tasks;
    }

    public HashMap<String, Processor> getProcessors() {
        return processors;
    }

    public List<MyTask> getTasksInPriorityRange(int low, int high) {
        return tasksByPriority.getTasksInPriorityRange(low, high);
    }
}

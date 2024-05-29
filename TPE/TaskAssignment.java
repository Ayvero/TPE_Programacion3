package TPE;

import java.util.*;

public class TaskAssignment {
    private List<Processor> processors;
    private List<MyTask> tasks;
    private int X; // Tiempo máximo para procesadores no refrigerados

    public TaskAssignment(List<Processor> processors, List<MyTask> tasks, int X) {
        this.processors = processors;
        this.tasks = tasks;
        this.X = X;
    }

    // Método para asignar tareas usando Backtracking
    public Map<Processor, List<MyTask>> assignTasksBacktracking() {
        Map<Processor, List<MyTask>> assignment = new HashMap<>();
        for (Processor p : processors) {
            assignment.put(p, new ArrayList<>());
        }
        if (backtrack(assignment, 0)) {
            return assignment;
        }
        return null; // Si no se encuentra una asignación válida
    }

    private boolean backtrack(Map<Processor, List<MyTask>> assignment, int taskIndex) {
        if (taskIndex == tasks.size()) {
            return true; // Se asignaron todas las tareas
        }
        MyTask currentTask = tasks.get(taskIndex);
        for (Processor p : processors) {
            if (isValidAssignment(p, currentTask, assignment)) {
                assignment.get(p).add(currentTask);
                if (backtrack(assignment, taskIndex + 1)) {
                    return true;
                }
                assignment.get(p).remove(currentTask); // Backtrack
            }
        }
        return false;
    }

    private boolean isValidAssignment(Processor p, MyTask task, Map<Processor, List<MyTask>> assignment) {
        if (task.isCritica()) {
            for (MyTask t : assignment.get(p)) {
                if (t.isCritica()) {
                    return false; // No puede tener dos tareas críticas
                }
            }
        }
        if (!p.isRefrigerado()) {
            int totalTime = 0;
            for (MyTask t : assignment.get(p)) {
                totalTime += t.getTiempoEjecucion();
            }
            if (totalTime + task.getTiempoEjecucion() > X) {
                return false; // Excede el tiempo máximo para procesadores no refrigerados
            }
        }
        return true;
    }

    // Método para asignar tareas usando Greedy
    public Map<Processor, List<MyTask>> assignTasksGreedy() {
        Map<Processor, List<MyTask>> assignment = new HashMap<>();
        for (Processor p : processors) {
            assignment.put(p, new ArrayList<>());
        }
        tasks.sort(Comparator.comparingInt(MyTask::getNivelPrioridad).reversed()); // Ordenar tareas por prioridad

        for (MyTask task : tasks) {
            Processor bestProcessor = null;
            for (Processor p : processors) {
                if (isValidAssignment(p, task, assignment)) {
                    if (bestProcessor == null || getTotalTime(assignment.get(p)) < getTotalTime(assignment.get(bestProcessor))) {
                        bestProcessor = p;
                    }
                }
            }
            if (bestProcessor != null) {
                assignment.get(bestProcessor).add(task);
            }
        }
        return assignment;
    }

    private int getTotalTime(List<MyTask> tasks) {
        int totalTime = 0;
        for (MyTask t : tasks) {
            totalTime += t.getTiempoEjecucion();
        }
        return totalTime;
    }

    // Método para calcular el tiempo final de ejecución
    public int getFinalExecutionTime(Map<Processor, List<MyTask>> assignment) {
        int maxTime = 0;
        for (Map.Entry<Processor, List<MyTask>> entry : assignment.entrySet()) {
            int totalTime = getTotalTime(entry.getValue());
            if (totalTime > maxTime) {
                maxTime = totalTime;
            }
        }
        return maxTime;
    }
}


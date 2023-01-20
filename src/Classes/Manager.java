package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Manager {
    private int id;
    private static HashMap<Integer, Task> tasks = new HashMap<>();
    private static HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private static HashMap<Integer, Epic> epics = new HashMap<>();
    private static List<HashMap> listHashMaps = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    Task task;
    Subtask subtask;
    Epic epic;

    public Manager() {
        int id = 0;
        listHashMaps.add(tasks);
        listHashMaps.add(subtasks);
        listHashMaps.add(epics);
    }

    public int addId() {
        id++;
        return this.id;
    }

    public int getId() {
        return this.id;
    }
    public int setId(int id) {
        this.id = id;
        return this.id;
    }

    public String outTasks(HashMap<Integer, Task> tasks){
        String out_string = "\nTasks list:";
        for(Integer key: tasks.keySet()) {
            Task task_out = tasks.get(key);
            out_string += (task_out.toString() + "\n------");
        }
        return out_string;
    }

    public String outSubtasks(HashMap<Integer, Subtask> subtasks) {
        String out_string = "\nSubtasks list:";
        for (Integer key : subtasks.keySet()) {
            Subtask subtask_out = subtasks.get(key);
            out_string += (subtask_out.toString() + "\n------");
        }
        return out_string;
    }

    public String outEpics(HashMap<Integer, Epic> epics) {
        String out_string = "\nEpics list:";
        for (Integer key : epics.keySet()) {
            Epic epic_out = epics.get(key);
            out_string += (epic_out.toString() + "\n------");
        }
        return out_string;
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);//Добавить контроль статуса епика
        Epic epic = epics.get(subtask.getIdEpicTask());
        epic.setSubtasks(subtask,1);
    }
    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic); //Добавить контроль статуса епика
    }

    public void showAllTask(){
        if(!tasks.isEmpty() || !subtasks.isEmpty() || !epics.isEmpty()) {
            if(!tasks.isEmpty())
                System.out.println(this.outTasks(tasks));
            if(!subtasks.isEmpty())
                System.out.println(this.outSubtasks(subtasks));
            if(!epics.isEmpty())
                System.out.println(this.outEpics(epics));
        }
        else{
            System.out.println("Task list is empty");
        }
    }

    public void deleteAllTask(){
        System.out.println("Are you sure you want to delete all tasks??(Y/N)");
        String answerDelete = scanner.next();
        if (answerDelete.equals("Y")) {
            tasks.clear();
            subtasks.clear();
            epics.clear();
            listHashMaps.clear();
            this.setId(0);
            System.out.println("Deletion completed");
        } else if (answerDelete.equals("N")) {
            System.out.println("Deletion canceled");
        } else {
            System.out.println("Command not supported");
        }
    }

    public void createTask(){
        int taskType;
        int statusId;
        int idToEpic;
        String name;
        String description;
        String status;

        System.out.println("Select task type(1 - task, 2 - subtask, 3 - epic): ");
        taskType = scanner.nextInt();
        System.out.println("Input name task: ");
        name = scanner.next();
        System.out.println("Input description task: ");
        description = scanner.next();
        switch (taskType){
            case 1: {
                System.out.println("Select status task(0 - new, 1 - working, 2 - done): ");
                statusId = scanner.nextInt();
                if(statusId == 0) {
                    status = "NEW";
                } else if(statusId == 1) {
                    status = "WORKING";
                } else if(statusId == 2) {
                    status = "DONE";
                } else {
                    System.out.println("Command not support");
                    break;
                }
                task = new Task(name, description, status, this.addId());
                this.updateTask(task);
                break;
            }
            case 2: {
                System.out.println("Select status task(0 - new, 1 - working, 2 - done): ");
                statusId = scanner.nextInt();
                if(statusId == 0) {
                    status = "NEW";
                } else if(statusId == 1) {
                    status = "WORKING";
                } else if(statusId == 2) {
                    status = "DONE";
                } else {
                    System.out.println("Command not support");
                    break;
                }
                System.out.println("Input epic ID: ");
                idToEpic = scanner.nextInt();
                if(epics.get(idToEpic) != null) {
                    subtask = new Subtask(name, description, status, this.addId(), idToEpic);
                    updateSubtask(subtask);
                }
                else {
                    System.out.println("Epic not found");
                }
                break;
            }
            case 3:{
                status = "NEW";
                epic = new Epic(name, description, status, this.addId());
                updateEpic(epic);
                break;
            }
            default:{
                System.out.println("Command not support");
            }
        }
    }

    public void updateTaskFromMenu(){
        String name;
        String description;
        String status;
        Task oldTask = null;
        Subtask oldSubtask = null;
        Epic oldEpic = null;
        int statusId = 0;

        System.out.println("Input task ID: ");
        int id = scanner.nextInt();
        for (HashMap hashMapIterator : listHashMaps) {
            if (hashMapIterator.get(id) != null) {
                if(hashMapIterator.get(id).getClass() == Task.class) {
                    oldTask = (Task) hashMapIterator.get(id);
                    System.out.println("Old name:" + oldTask.name + "\nInput new task name: ");
                    name = scanner.next();
                    System.out.println("Old description:" + oldTask.description + "\nInput new description task: ");
                    description = scanner.next();
                    System.out.println("Old status:" + oldTask.status + "\nSelect new status task(0 - new, 1 - working, 2 - done): ");
                    statusId = scanner.nextInt();
                    if(statusId == 0) {
                        status = "NEW";
                    } else if(statusId == 1) {
                        status = "WORKING";
                    } else if(statusId == 2) {
                        status = "DONE";
                    } else {
                        status = "ERROR";
                    }
                    task = new Task(name, description, status, id);
                    updateTask(task);
                }
                if(hashMapIterator.get(id).getClass() == Subtask.class) {
                    oldSubtask = (Subtask) hashMapIterator.get(id);
                    System.out.println("Old name:" + oldSubtask.name + "\nInput new task name: ");
                    name = scanner.next();
                    System.out.println("Old description:" + oldSubtask.description + "\nInput new description task: ");
                    description = scanner.next();
                    System.out.println("Old status:" + oldSubtask.status + "\nSelect new status task(0 - new, 1 - working, 2 - done): ");
                    statusId = scanner.nextInt();
                    if(statusId == 0) {
                        status = "NEW";
                    } else if(statusId == 1) {
                        status = "WORKING";
                    } else if(statusId == 2) {
                        status = "DONE";
                    } else {
                        status = "ERROR";
                    }
                    subtask = new Subtask(name, description, status, id, oldSubtask.idEpicTask);
                    subtasks.put(subtask.getId(), subtask);
                }
                if(hashMapIterator.get(id).getClass() == Epic.class) {
                    oldEpic = (Epic) hashMapIterator.get(id);
                    System.out.println("Old name:" + oldEpic.name + "\nInput new task name: ");
                    name = scanner.next();
                    System.out.println("Old description:" + oldEpic.description + "\nInput new description task: ");
                    description = scanner.next();
                    System.out.println("Old status:" + oldEpic.status + "\nSelect new status task(0 - new, 1 - working, 2 - done): ");
                    statusId = scanner.nextInt();
                    if(statusId == 0) {
                        status = "NEW";
                    } else if(statusId == 1) {
                        status = "WORKING";
                    } else if(statusId == 2) {
                        status = "DONE";
                    } else {
                        status = "ERROR";
                    }
                    epic.getSubtasks();//Передать лист сабтасков
                    epic = new Epic(name, description, status, id);
                    updateEpic(epic);
                }
            }
        }
    }

    public void deleteTaskById(int id){
        if(tasks.get(id) != null) {
            tasks.remove(id);
        }
        else if(subtasks.get(id) != null) {
            subtask = subtasks.get(id);
            epic = epics.get(subtask.getIdEpicTask());
            epic.setSubtasks(subtask,2);//Не работает удаление сабтасков
            subtasks.remove(id);
        }
        else if(epics.get(id) != null) {
            epic = epics.get(id);
            List<Subtask> epicSubtask = epic.getSubtasks();
            for (Subtask subtask : epicSubtask){
                subtasks.remove(subtask);//Не работает удаление сабтасков
            }
            epics.remove(id);
        }
        else {
            System.out.println("Task not found");
        }
        System.out.println(id + " deleted");
    }

    public void getAllTaskByEpic(int id){
        if(epics.get(id) != null){
            epic = epics.get(id);
            for(Subtask subtask : epic.getSubtasks()) {
                System.out.println(subtask);
            }
        }
        else{
            System.out.println("Epic not found");
        }
    }

    public void getTaskById(int id){
        if(id <= this.getId())
            for(HashMap hashMapIterator : listHashMaps) {
                if (hashMapIterator.get(id) != null)
                    System.out.println(hashMapIterator.get(id));
            }
        else
            System.out.println("Task not found");
    }

    public void printMenu(){
        System.out.println("Menu, enter comand:");
        System.out.println("1 - List all tasks");//Done
        System.out.println("2 - Delete all task");//Done
        System.out.println("3 - Show task by ID");//Done
        System.out.println("4 - Create task");//Done
        System.out.println("5 - Update task");//Done, кроме обновления статусов у Эпиков и передача подзадач при обновлении Эпика
        System.out.println("6 - Delete task by ID");//Done, контроль у эпика ИД подзадачи, удаление эпика и подзадач
        System.out.println("7 - Show all task of Epic");//Done
        System.out.println("0 - Exit");
    }
}
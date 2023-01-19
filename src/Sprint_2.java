import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Classes.*;

public class Sprint_2 {
    public static HashMap<Integer, Task> tasks = new HashMap<>();
    public static HashMap<Integer, Subtask> subtasks = new HashMap<>();
    public static HashMap<Integer, Epic> epics = new HashMap<>();
    public static List<Task> listTask = new ArrayList<>();
    public static Manager taskManager = new Manager();

    public static void main(String[] args) {
        //Create test tasks
        String name = "Task";
        String description = "Test task";
        String status = "NEW";
        Task task = new Task(name, description, status, taskManager.addId());
        updateTask(task);
        name = "Epic task";
        description = "Test epic task 1";
        Epic epic = new Epic(name, description, status, taskManager.addId());
        updateEpic(epic);
        name = "Subtask";
        description = "Subtask 1";
        int idToEpic = 2;
        Subtask subtask = new Subtask(name, description, status, taskManager.addId(), idToEpic);
        updateSubtask(subtask);
        subtask = new Subtask(name, description, status, taskManager.addId(), idToEpic);
        updateSubtask(subtask);
        subtask = new Subtask(name, description, status, taskManager.addId(), idToEpic);
        updateSubtask(subtask);

        Scanner scanner = new Scanner(System.in);

        printMenu();
        int userInput = scanner.nextInt();
        while (userInput != 0) {
            if (userInput == 1) {
                showAllTask();
            }else if (userInput == 2) {
                deleteAllTask();
            }else if (userInput == 3) {
                getTaskById();
            }else if (userInput == 4) {
                createTask();
            }else if (userInput == 5) {
                updateTaskFromMenu();
            }else if (userInput == 6) {
                deleteTaskById();
            }else if (userInput == 7) {
                getAllTaskByEpic();
            }else{
                System.out.println("Command " + userInput + " not supported");
            }
            printMenu();
            userInput = scanner.nextInt();
        }
        System.out.println("Program completed");
    }

    private static void printMenu(){
        System.out.println("Menu, enter comand:");
        System.out.println("1 - List all tasks");//Done
        System.out.println("2 - Delete all task");//Done
        System.out.println("3 - Show task by ID");//Done - create input task
        System.out.println("4 - Create task");//Done
        System.out.println("5 - Update task");//Done, кроме обновления статусов у Эпиков и опредения типа задачи
        System.out.println("6 - Delete task by ID");//Done, контроль у эпика ИД подзадачи, удаление эпика и подзадач
        System.out.println("7 - Show all task of Epic");//Done - create input task
        System.out.println("0 - Exit");
    }

    public static void showAllTask(){
        if(!tasks.isEmpty() || !subtasks.isEmpty() || !epics.isEmpty()) {
            if(!tasks.isEmpty())
                System.out.println(taskManager.outTasks(tasks));
            if(!subtasks.isEmpty())
                System.out.println(taskManager.outSubtasks(subtasks));
            if(!epics.isEmpty())
                System.out.println(taskManager.outEpics(epics));
        }
        else{
            System.out.println("Task list is empty");
        }
    }

    public static void deleteAllTask(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Are you sure you want to delete all tasks??(Y/N)");
        String answerDelete = scanner.next();
        if (answerDelete.equals("Y")) {
            tasks.clear();
            subtasks.clear();
            epics.clear();
            listTask.clear();
            taskManager.setId(0);
            System.out.println("Deletion completed");
        } else if (answerDelete.equals("N")) {
            System.out.println("Deletion canceled");
        } else {
            System.out.println("Command not supported");
        }
    }

    public static void getTaskById(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input task ID: ");
        int id = scanner.nextInt();
        if(id <= listTask.size())
            System.out.println(listTask.get(id-1));
        else
            System.out.println("Task not found");
    }

    public static void createTask(){
        Scanner scanner = new Scanner(System.in);
        int taskType;
        int statusId;
        String name;
        String description;
        String status;
        Task task;
        Subtask subtask;
        Epic epic;
        int idToEpic;

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
                task = new Task(name, description, status, taskManager.addId());
                updateTask(task);
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
                    subtask = new Subtask(name, description, status, taskManager.addId(), idToEpic);
                    updateSubtask(subtask);
                }
                else {
                    System.out.println("Epic not found");
                }
                break;
            }
            case 3:{
                status = "NEW";
                epic = new Epic(name, description, status, taskManager.addId()); //Создание эпика без статуса?
                updateEpic(epic);
                break;
            }
            default:{
                System.out.println("Command not support");
            }
        }
    }

    public static void updateTaskFromMenu(){
        Scanner scanner = new Scanner(System.in);
        String name;
        String description;
        String status;
        Task task;

        System.out.println("Input task ID: "); //Добавить ввывод текущих значений полей объекта
        int id = scanner.nextInt();
        System.out.println("Input new task name: ");
        name = scanner.next();
        System.out.println("Input new description task: ");
        description = scanner.next();
        System.out.println("Select new status task(0 - new, 1 - working, 2 - done): ");
        int statusId = scanner.nextInt();
        if(statusId == 0) {
            status = "NEW";
        } else if(statusId == 1) {
            status = "WORKING";
        } else if(statusId == 2) {
            status = "DONE";
        } else {
            status = "ERROR";
        }
        //Добавить определение типа задачи
        task = new Task(name, description, status, id);
        updateTask(task);
    }

    public static void deleteTaskById(){
        Scanner scanner = new Scanner(System.in);
        Subtask subtask;
        Epic epic;

        System.out.println("Input task ID: ");
        int id = scanner.nextInt();
        if(tasks.get(id) != null) {
            tasks.remove(id);
        }
        else if(subtasks.get(id) != null) {
            subtask = subtasks.get(id);
            epic = epics.get(subtask.getIdEpicTask());
            epic.setIdSubtasks(subtask.getId(),2);
            subtasks.remove(id);
        }
        else if(epics.get(id) != null) {
            epics.remove(id);
        }
        else {
            System.out.println("Task not found");
        }
        System.out.println(id + " deleted");
    }

    public static void getAllTaskByEpic(){
        Scanner scanner = new Scanner(System.in);
        Epic epic;

        System.out.println("Input epic ID: ");
        int id = scanner.nextInt();
        if(epics.get(id) != null){
            epic = epics.get(id);
            for (int key : epic.getIdSubtasks()) {
                System.out.println("\n" + listTask.get(key-1) + "\n");
            }
        }
        else{
            System.out.println("Epic not found");
        }
    }

    public static void updateTask(Task task) {
        tasks.put(task.getId(), task);
        listTask.add(task);
    }
    public static void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        listTask.add(subtask);
        Epic epic = epics.get(subtask.getIdEpicTask());
        epic.setIdSubtasks(subtask.getId(),1);
    }
    public static void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        listTask.add(epic);
    }
}
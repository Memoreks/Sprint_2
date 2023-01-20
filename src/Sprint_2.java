import java.util.Scanner;

import Classes.*;

public class Sprint_2 {
    public static Manager taskManager = new Manager();

    public static void main(String[] args) {
        //Create test tasks
        String name = "Task 1";
        String description = "Test task 1";
        String status = "NEW";
        Task task = new Task(name, description, status, taskManager.addId());
        taskManager.updateTask(task);
        name = "Epic task 1";
        description = "Test epic task 1";
        Epic epic = new Epic(name, description, status, taskManager.addId());
        taskManager.updateEpic(epic);
        name = "Subtask 1";
        description = "Subtask 1";
        int idToEpic = 2;
        Subtask subtask = new Subtask(name, description, status, taskManager.addId(), idToEpic);
        taskManager.updateSubtask(subtask);
        name = "Subtask 2";
        description = "Subtask 2";
        subtask = new Subtask(name, description, status, taskManager.addId(), idToEpic);
        taskManager.updateSubtask(subtask);
        name = "Subtask 3";
        description = "Subtask 3";
        subtask = new Subtask(name, description, status, taskManager.addId(), idToEpic);
        taskManager.updateSubtask(subtask);

       Scanner scanner = new Scanner(System.in);
        int id;
        taskManager.printMenu();
        int userInput = scanner.nextInt();
        while (userInput != 0) {
            if (userInput == 1) {
                taskManager.showAllTask();
            }else if (userInput == 2) {
                taskManager.deleteAllTask();
            }else if (userInput == 3) {
                System.out.println("Input task ID: ");
                id = scanner.nextInt();
                taskManager.getTaskById(id);
            }else if (userInput == 4) {
                taskManager.createTask();
            }else if (userInput == 5) {
                taskManager.updateTaskFromMenu();
            }else if (userInput == 6) {
                System.out.println("Input task ID: ");
                id = scanner.nextInt();
                taskManager.deleteTaskById(id);
            }else if (userInput == 7) {
                System.out.println("Input epic ID: ");
                id = scanner.nextInt();
                taskManager.getAllTaskByEpic(id);
            }else{
                System.out.println("Command " + userInput + " not supported");
            }
            taskManager.printMenu();
            userInput = scanner.nextInt();
        }
        System.out.println("Program completed");
    }
}
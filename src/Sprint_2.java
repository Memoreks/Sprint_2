import java.util.Scanner;

import Managers.History.HistoryManager;
import Managers.Managers;
import Managers.Task.TaskManager;
import Tasks.*;

public class Sprint_2 {
    private static TaskManager taskManager;
    private static HistoryManager historyManager;

    public static void main(String[] args) {
        taskManager = Managers.getDefault();
        historyManager = Managers.getDefaultHistory();

        initTasks();

        Scanner scanner = new Scanner(System.in);
        int id;
        taskManager.printMenu();
        int userInput = scanner.nextInt();
        while (userInput != 0) {
            if (userInput == 1) {
                taskManager.showAllTasks();
            }else if (userInput == 2) {
                taskManager.deleteAllTasks();
            }else if (userInput == 3) {
                System.out.println("Input task ID: ");
                id = scanner.nextInt();
                taskManager.getTaskById(id, historyManager);
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
            }else if (userInput == 8) {
                historyManager.getHistory();
            }else{
                System.out.println("Command " + userInput + " not supported");
            }
            taskManager.printMenu();
            userInput = scanner.nextInt();
        }
        System.out.println("Program completed");
    }

    private static void initTasks() {
        //Create test tasks
        taskManager.createTask("Task 1", "Test task 1", TaskStatuses.NEW);

        Epic epic2 = taskManager.createEpic("Epic task 1", "Test epic task 1");

        taskManager.createSubtask("Subtask 1", "Subtask 1", TaskStatuses.NEW, epic2);
        taskManager.createSubtask("Subtask 2", "Subtask 2", TaskStatuses.NEW, epic2);
        taskManager.createSubtask("Subtask 3", "Subtask 3", TaskStatuses.NEW, epic2);
    }
}
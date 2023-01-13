import java.util.HashMap;
import java.util.Scanner;

import Classes.*;

public class Sprint_2 {
    public static Manager taskManager = new Manager();
    public static HashMap<Integer, Task> tasks = new HashMap<>();
    public static HashMap<Integer, Subtask> subtasks = new HashMap<>();
    public static HashMap<Integer, Epic> epics = new HashMap<>();
    public static void main(String[] args) {
        //Создание тестовых задач
        String name = "Задача";
        String description = "Пробная задача";
        String status = "NEW";
        Task task = new Task(name, description, status, taskManager.addId());
        tasks.put(task.getId(), task);
        name = "Подзадача";
        description = "Пробная подзадача 1";
        Subtask subtask = new Subtask(name, description, status, taskManager.addId(), 1);
        subtasks.put(task.getId(), subtask);
        name = "Большая задача";
        description = "Пробная большая задача 1";
        Epic epic = new Epic(name, description, status, taskManager.addId());
        epics.put(task.getId(), epic);

        Scanner scanner = new Scanner(System.in);
        printmenu();
        int userInput = scanner.nextInt();
        while (userInput != 0) {
            if (userInput == 1) {
                System.out.println(taskManager.out_tasks(tasks));
                System.out.println(taskManager.out_subtasks(subtasks));
                System.out.println(taskManager.out_epics(epics));
                //Добавить проверку ввывода
            }else if (userInput == 2) {
                System.out.println("Вы уверены, что хотите удалить все задачи?(Введите Y/N)");
                String answer_delete = scanner.next();
                if (answer_delete.equals("Y")) {
                    tasks = null;
                    subtasks = null;
                    epics = null;
                    System.out.println("Удаление произведено");
                } else if (answer_delete.equals("N")) {
                    System.out.println("Удаление отменено");
                } else {
                    System.out.println("Невозможно обработать Ваш ответ");
                }
            }else if (userInput == 5) {
                System.out.println("Введите ID задачи: ");
                int id = scanner.nextInt();
                System.out.println("Введите новое название задачи: ");
                name = scanner.next();
                System.out.println("Введите новое описание задачи: ");
                description = scanner.next();
                System.out.println("Выберите новой статус задачи(0 - new, 1 - working, 2 - done): ");
                int status_id = scanner.nextInt();
                if(status_id == 0) {
                    status = "NEW";
                } else if(status_id == 1) {
                    status = "WORKING";
                } else if(status_id == 2) {
                    status = "DONE";
                } else {
                    status = "ERROR";
                }
                //Добавить определение типа задачи
                task = new Task(name, description, status, id);
                updateTask(task);
            }else{
                System.out.println("Введенная команда " + userInput + " не поддерживается.");
            }
            printmenu();
            userInput = scanner.nextInt();
        }
        System.out.println("Программа завершена");
    }

    private static void printmenu(){ //Метод печати меню в консоли
        System.out.println("Меню программы, введите команду:");
        System.out.println("1 - Вывести списки всех задач");
        System.out.println("2 - Удалить все задачи");
        System.out.println("3 - Получить задачу по ИД");
        System.out.println("4 - Создать задачу");//сам объект должен передаваться в качестве параметра
        System.out.println("5 - Обновить задачу");//новая версия объекта и ИД передаются ввиде параметра
        System.out.println("6 - Удалить задачу по ИД");
        System.out.println("7 - Вывести все подзадачи эпика по ИД");
        System.out.println("0 - выход");
    }

    //Объединить в один метод?
    public static void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }
    public static void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
    }
    public static void updateTask(Epic epic) {
        epics.put(epic.getId(), epic);
    }
}

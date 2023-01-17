import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Classes.*;

public class Sprint_2 {
    public static Manager taskManager = new Manager();
    public static HashMap<Integer, Task> tasks = new HashMap<>();
    public static HashMap<Integer, Subtask> subtasks = new HashMap<>();
    public static HashMap<Integer, Epic> epics = new HashMap<>();
    public static List<HashMap> listHashMaps = new ArrayList<HashMap>();

    public static void main(String[] args) {
        listHashMaps.add(tasks);
        listHashMaps.add(subtasks);
        listHashMaps.add(epics);
        //Создание тестовых задач
        String name = "Задача";
        String description = "Пробная задача";
        String status = "NEW";
        Task task = new Task(name, description, status, taskManager.addId());
        tasks.put(task.getId(), task);
        name = "Подзадача";
        description = "Пробная подзадача 1";
        int idToEpic = 3;
        Subtask subtask = new Subtask(name, description, status, taskManager.addId(), idToEpic);
        subtasks.put(subtask.getId(), subtask);
        name = "Большая задача";
        description = "Пробная большая задача 1";
        Epic epic = new Epic(name, description, status, taskManager.addId());
        epics.put(epic.getId(), epic);

        Scanner scanner = new Scanner(System.in);
        printMenu();
        int userInput = scanner.nextInt();
        while (userInput != 0) {
            if (userInput == 1) {
                System.out.println(taskManager.outTasks(tasks));
                System.out.println(taskManager.outSubtasks(subtasks));
                System.out.println(taskManager.outEpics(epics));
                //Добавить проверку ввывода
            }else if (userInput == 2) {
                System.out.println("Вы уверены, что хотите удалить все задачи?(Введите Y/N)");
                String answerDelete = scanner.next();
                if (answerDelete.equals("Y")) {
                    tasks = null;
                    subtasks = null;
                    epics = null;
                    System.out.println("Удаление произведено");
                } else if (answerDelete.equals("N")) {
                    System.out.println("Удаление отменено");
                } else {
                    System.out.println("Невозможно обработать Ваш ответ");
                }
            }else if (userInput == 3) {
                System.out.println("Введите ID задачи: ");
                int id = scanner.nextInt();
                System.out.println(listHashMaps.get(id-1));
            }else if (userInput == 4) {
                int taskType;
                int statusId;
                System.out.println("Выберите тип задачи(1 - обычная задача, 2 - подзадача, 3 - эпик): ");
                taskType = scanner.nextInt();
                System.out.println("Введите новое название задачи: ");
                name = scanner.next();
                System.out.println("Введите новое описание задачи: ");
                description = scanner.next();
                switch (taskType){
                    case 1: {
                        System.out.println("Выберите новой статус задачи(0 - new, 1 - working, 2 - done): ");
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
                        task = new Task(name, description, status, taskManager.addId());
                        updateTask(task);
                        break;
                    }
                    case 2: {
                        System.out.println("Выберите новой статус задачи(0 - new, 1 - working, 2 - done): ");
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
                        System.out.println("Введите ID эпика: ");
                        idToEpic = scanner.nextInt();
                        subtask = new Subtask(name, description, status, taskManager.addId(), idToEpic);
                        updateSubtask(subtask);
                        subtasks.put(subtask.getId(), subtask);
                        break;
                    }
                    case 3:{
                        epic = new Epic(name, description, status, taskManager.addId());
                        updateEpic(epic);
                        epics.put(epic.getId(), epic);
                        break;
                    }
                    default:{
                        System.out.println("Введен не правильный тип задачи");
                    }
                }
                //Добавить определение типа задачи

            }else if (userInput == 5) {
                System.out.println("Введите ID задачи: "); //Добавить ввывод текущих значений полей объекта
                int id = scanner.nextInt();
                System.out.println("Введите новое название задачи: ");
                name = scanner.next();
                System.out.println("Введите новое описание задачи: ");
                description = scanner.next();
                System.out.println("Выберите новой статус задачи(0 - new, 1 - working, 2 - done): ");
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
            }else if (userInput == 6) {
                System.out.println("Введите ID задачи: ");
                int id = scanner.nextInt();
            }else if (userInput == 7) {

            }else{
                System.out.println("Введенная команда " + userInput + " не поддерживается.");
            }
            printMenu();
            userInput = scanner.nextInt();
        }
        System.out.println("Программа завершена");
    }

    private static void printMenu(){ //Метод печати меню в консоли
        System.out.println("Меню программы, введите команду:");
        System.out.println("1 - Вывести списки всех задач");//Готово, добавить проверку вывода
        System.out.println("2 - Удалить все задачи");//Готово
        System.out.println("3 - Получить задачу по ИД");//Готово, кроме новых задач
        System.out.println("4 - Создать задачу");//Готово
        System.out.println("5 - Обновить задачу");//Готово, кроме обновления статусов и опредения типа задачи
        System.out.println("6 - Удалить задачу по ИД");//Не готово
        System.out.println("7 - Вывести все подзадачи эпика по ИД");//Не готово
        System.out.println("0 - выход");
    }

    public static void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }
    public static void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
    }
    public static void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }
}
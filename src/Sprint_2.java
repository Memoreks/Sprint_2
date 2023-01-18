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
    public static List<HashMap> listHashMaps = new ArrayList<>();

    public static void main(String[] args) {
        //Создание тестовых задач
        String name = "Задача";
        String description = "Пробная задача";
        String status = "NEW";
        Task task = new Task(name, description, status, taskManager.addId());
        updateTask(task);
        name = "Большая задача";
        description = "Пробная большая задача 1";
        Epic epic = new Epic(name, description, status, taskManager.addId());
        updateEpic(epic);
        name = "Подзадача";
        description = "Пробная подзадача 1";
        int idToEpic = 2;
        Subtask subtask = new Subtask(name, description, status, taskManager.addId(), idToEpic);
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
                System.out.println("Введенная команда " + userInput + " не поддерживается.");
            }
            printMenu();
            userInput = scanner.nextInt();
        }
        System.out.println("Программа завершена");
    }

    private static void printMenu(){ //Метод печати меню в консоли
        System.out.println("Меню программы, введите команду:");
        System.out.println("1 - Вывести списки всех задач");//Готово
        System.out.println("2 - Удалить все задачи");//Готово
        System.out.println("3 - Вывести задачу по ИД");//Готово - сделать красивый вывод задачи
        System.out.println("4 - Создать задачу");//Готово
        System.out.println("5 - Обновить задачу");//Готово, кроме обновления статусов у Эпиков и опредения типа задачи
        System.out.println("6 - Удалить задачу по ИД");//Не готово, контроль у эпика ИД подзадачи
        System.out.println("7 - Вывести все подзадачи эпика по ИД");//Готово - сделать красивый вывод задачи
        System.out.println("0 - Выход");
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
            System.out.println("Список задач пуст");
        }
    }

    public static void deleteAllTask(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Вы уверены, что хотите удалить все задачи?(Введите Y/N)");
        String answerDelete = scanner.next();
        if (answerDelete.equals("Y")) {
            tasks.clear();
            subtasks.clear();
            epics.clear();
            listHashMaps.clear();
            taskManager.setId(0);
            System.out.println("Удаление произведено");
        } else if (answerDelete.equals("N")) {
            System.out.println("Удаление отменено");
        } else {
            System.out.println("Невозможно обработать Ваш ответ");
        }
    }

    public static void getTaskById(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите ID задачи: ");
        int id = scanner.nextInt();
        if(id <= listHashMaps.size())
            System.out.println(listHashMaps.get(id-1));
        else
            System.out.println("Задача с введенным ID не найдена");
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
                    System.out.println("Ошибка указан несуществующий статус задачи");
                    break;
                }
                System.out.println("Введите ID эпика: ");
                idToEpic = scanner.nextInt();
                if(epics.get(idToEpic) != null) {
                    subtask = new Subtask(name, description, status, taskManager.addId(), idToEpic);
                    updateSubtask(subtask);
                }
                else {
                    System.out.println("Ошибка эпик с указанным ID не найден");
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
                System.out.println("Введен не правильный тип задачи");
            }
        }
    }

    public static void updateTaskFromMenu(){
        Scanner scanner = new Scanner(System.in);
        String name;
        String description;
        String status;
        Task task;

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
    }

    public static void deleteTaskById(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите ID задачи: ");
        int id = scanner.nextInt();
        System.out.println(listHashMaps.get(id));
    }

    public static void getAllTaskByEpic(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> idSubtasks;
        Epic epic;

        System.out.println("Введите ID эпика: ");
        int id = scanner.nextInt();
        if(epics.get(id) != null){
            epic = epics.get(id);
            idSubtasks = epic.getIdSubtasks();
            for(int key: idSubtasks)
                System.out.println(listHashMaps.get(key-1));
            //Вывод субтасков
        }
        else{
            System.out.println("Ошибка эпика с указанным ID не существует");
        }
    }

    public static void updateTask(Task task) {
        tasks.put(task.getId(), task);
        listHashMaps.add(tasks);
    }
    public static void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        listHashMaps.add(subtasks);
        Epic epic = epics.get(subtask.getIdEpicTask());
        epic.setIdSubtasks(subtask.getId());
    }
    public static void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        listHashMaps.add(epics);
    }
}
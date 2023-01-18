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
        //�������� �������� �����
        String name = "������";
        String description = "������� ������";
        String status = "NEW";
        Task task = new Task(name, description, status, taskManager.addId());
        updateTask(task);
        name = "������� ������";
        description = "������� ������� ������ 1";
        Epic epic = new Epic(name, description, status, taskManager.addId());
        updateEpic(epic);
        name = "���������";
        description = "������� ��������� 1";
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
                System.out.println("��������� ������� " + userInput + " �� ��������������.");
            }
            printMenu();
            userInput = scanner.nextInt();
        }
        System.out.println("��������� ���������");
    }

    private static void printMenu(){ //����� ������ ���� � �������
        System.out.println("���� ���������, ������� �������:");
        System.out.println("1 - ������� ������ ���� �����");//������
        System.out.println("2 - ������� ��� ������");//������
        System.out.println("3 - ������� ������ �� ��");//������ - ������� �������� ����� ������
        System.out.println("4 - ������� ������");//������
        System.out.println("5 - �������� ������");//������, ����� ���������� �������� � ������ � ��������� ���� ������
        System.out.println("6 - ������� ������ �� ��");//�� ������, �������� � ����� �� ���������
        System.out.println("7 - ������� ��� ��������� ����� �� ��");//������ - ������� �������� ����� ������
        System.out.println("0 - �����");
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
            System.out.println("������ ����� ����");
        }
    }

    public static void deleteAllTask(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("�� �������, ��� ������ ������� ��� ������?(������� Y/N)");
        String answerDelete = scanner.next();
        if (answerDelete.equals("Y")) {
            tasks.clear();
            subtasks.clear();
            epics.clear();
            listHashMaps.clear();
            taskManager.setId(0);
            System.out.println("�������� �����������");
        } else if (answerDelete.equals("N")) {
            System.out.println("�������� ��������");
        } else {
            System.out.println("���������� ���������� ��� �����");
        }
    }

    public static void getTaskById(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("������� ID ������: ");
        int id = scanner.nextInt();
        if(id <= listHashMaps.size())
            System.out.println(listHashMaps.get(id-1));
        else
            System.out.println("������ � ��������� ID �� �������");
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

        System.out.println("�������� ��� ������(1 - ������� ������, 2 - ���������, 3 - ����): ");
        taskType = scanner.nextInt();
        System.out.println("������� ����� �������� ������: ");
        name = scanner.next();
        System.out.println("������� ����� �������� ������: ");
        description = scanner.next();
        switch (taskType){
            case 1: {
                System.out.println("�������� ����� ������ ������(0 - new, 1 - working, 2 - done): ");
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
                System.out.println("�������� ����� ������ ������(0 - new, 1 - working, 2 - done): ");
                statusId = scanner.nextInt();
                if(statusId == 0) {
                    status = "NEW";
                } else if(statusId == 1) {
                    status = "WORKING";
                } else if(statusId == 2) {
                    status = "DONE";
                } else {
                    System.out.println("������ ������ �������������� ������ ������");
                    break;
                }
                System.out.println("������� ID �����: ");
                idToEpic = scanner.nextInt();
                if(epics.get(idToEpic) != null) {
                    subtask = new Subtask(name, description, status, taskManager.addId(), idToEpic);
                    updateSubtask(subtask);
                }
                else {
                    System.out.println("������ ���� � ��������� ID �� ������");
                }
                break;
            }
            case 3:{
                status = "NEW";
                epic = new Epic(name, description, status, taskManager.addId()); //�������� ����� ��� �������?
                updateEpic(epic);
                break;
            }
            default:{
                System.out.println("������ �� ���������� ��� ������");
            }
        }
    }

    public static void updateTaskFromMenu(){
        Scanner scanner = new Scanner(System.in);
        String name;
        String description;
        String status;
        Task task;

        System.out.println("������� ID ������: "); //�������� ������ ������� �������� ����� �������
        int id = scanner.nextInt();
        System.out.println("������� ����� �������� ������: ");
        name = scanner.next();
        System.out.println("������� ����� �������� ������: ");
        description = scanner.next();
        System.out.println("�������� ����� ������ ������(0 - new, 1 - working, 2 - done): ");
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
        //�������� ����������� ���� ������
        task = new Task(name, description, status, id);
        updateTask(task);
    }

    public static void deleteTaskById(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("������� ID ������: ");
        int id = scanner.nextInt();
        System.out.println(listHashMaps.get(id));
    }

    public static void getAllTaskByEpic(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> idSubtasks;
        Epic epic;

        System.out.println("������� ID �����: ");
        int id = scanner.nextInt();
        if(epics.get(id) != null){
            epic = epics.get(id);
            idSubtasks = epic.getIdSubtasks();
            for(int key: idSubtasks)
                System.out.println(listHashMaps.get(key-1));
            //����� ���������
        }
        else{
            System.out.println("������ ����� � ��������� ID �� ����������");
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
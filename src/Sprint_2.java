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
        //�������� �������� �����
        String name = "������";
        String description = "������� ������";
        String status = "NEW";
        Task task = new Task(name, description, status, taskManager.addId());
        tasks.put(task.getId(), task);
        name = "���������";
        description = "������� ��������� 1";
        int idToEpic = 3;
        Subtask subtask = new Subtask(name, description, status, taskManager.addId(), idToEpic);
        subtasks.put(subtask.getId(), subtask);
        name = "������� ������";
        description = "������� ������� ������ 1";
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
                //�������� �������� �������
            }else if (userInput == 2) {
                System.out.println("�� �������, ��� ������ ������� ��� ������?(������� Y/N)");
                String answerDelete = scanner.next();
                if (answerDelete.equals("Y")) {
                    tasks = null;
                    subtasks = null;
                    epics = null;
                    System.out.println("�������� �����������");
                } else if (answerDelete.equals("N")) {
                    System.out.println("�������� ��������");
                } else {
                    System.out.println("���������� ���������� ��� �����");
                }
            }else if (userInput == 3) {
                System.out.println("������� ID ������: ");
                int id = scanner.nextInt();
                System.out.println(listHashMaps.get(id-1));
            }else if (userInput == 4) {
                int taskType;
                int statusId;
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
                            status = "ERROR";
                        }
                        System.out.println("������� ID �����: ");
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
                        System.out.println("������ �� ���������� ��� ������");
                    }
                }
                //�������� ����������� ���� ������

            }else if (userInput == 5) {
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
            }else if (userInput == 6) {
                System.out.println("������� ID ������: ");
                int id = scanner.nextInt();
            }else if (userInput == 7) {

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
        System.out.println("1 - ������� ������ ���� �����");//������, �������� �������� ������
        System.out.println("2 - ������� ��� ������");//������
        System.out.println("3 - �������� ������ �� ��");//������, ����� ����� �����
        System.out.println("4 - ������� ������");//������
        System.out.println("5 - �������� ������");//������, ����� ���������� �������� � ��������� ���� ������
        System.out.println("6 - ������� ������ �� ��");//�� ������
        System.out.println("7 - ������� ��� ��������� ����� �� ��");//�� ������
        System.out.println("0 - �����");
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
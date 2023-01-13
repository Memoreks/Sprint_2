import java.util.HashMap;
import java.util.Scanner;

import Classes.*;

public class Sprint_2 {
    public static Manager taskManager = new Manager();
    public static HashMap<Integer, Task> tasks = new HashMap<>();
    public static HashMap<Integer, Subtask> subtasks = new HashMap<>();
    public static HashMap<Integer, Epic> epics = new HashMap<>();
    public static void main(String[] args) {
        //�������� �������� �����
        String name = "������";
        String description = "������� ������";
        String status = "NEW";
        Task task = new Task(name, description, status, taskManager.addId());
        tasks.put(task.getId(), task);
        name = "���������";
        description = "������� ��������� 1";
        Subtask subtask = new Subtask(name, description, status, taskManager.addId(), 1);
        subtasks.put(task.getId(), subtask);
        name = "������� ������";
        description = "������� ������� ������ 1";
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
                //�������� �������� �������
            }else if (userInput == 2) {
                System.out.println("�� �������, ��� ������ ������� ��� ������?(������� Y/N)");
                String answer_delete = scanner.next();
                if (answer_delete.equals("Y")) {
                    tasks = null;
                    subtasks = null;
                    epics = null;
                    System.out.println("�������� �����������");
                } else if (answer_delete.equals("N")) {
                    System.out.println("�������� ��������");
                } else {
                    System.out.println("���������� ���������� ��� �����");
                }
            }else if (userInput == 5) {
                System.out.println("������� ID ������: ");
                int id = scanner.nextInt();
                System.out.println("������� ����� �������� ������: ");
                name = scanner.next();
                System.out.println("������� ����� �������� ������: ");
                description = scanner.next();
                System.out.println("�������� ����� ������ ������(0 - new, 1 - working, 2 - done): ");
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
                //�������� ����������� ���� ������
                task = new Task(name, description, status, id);
                updateTask(task);
            }else{
                System.out.println("��������� ������� " + userInput + " �� ��������������.");
            }
            printmenu();
            userInput = scanner.nextInt();
        }
        System.out.println("��������� ���������");
    }

    private static void printmenu(){ //����� ������ ���� � �������
        System.out.println("���� ���������, ������� �������:");
        System.out.println("1 - ������� ������ ���� �����");
        System.out.println("2 - ������� ��� ������");
        System.out.println("3 - �������� ������ �� ��");
        System.out.println("4 - ������� ������");//��� ������ ������ ������������ � �������� ���������
        System.out.println("5 - �������� ������");//����� ������ ������� � �� ���������� ����� ���������
        System.out.println("6 - ������� ������ �� ��");
        System.out.println("7 - ������� ��� ��������� ����� �� ��");
        System.out.println("0 - �����");
    }

    //���������� � ���� �����?
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

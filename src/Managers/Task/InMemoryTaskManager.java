package Managers.Task;

import Managers.History.HistoryManager;
import Tasks.*;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private int id;
    private static final HashMap<Integer, Task> tasks = new HashMap<>();
    private static final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private static final HashMap<Integer, Epic> epics = new HashMap<>();
    private static final List<HashMap> listHashMaps = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    Task task;
    Subtask subtask;
    Epic epic;

    public InMemoryTaskManager() {
        id = 0;
        listHashMaps.add(tasks);
        listHashMaps.add(subtasks);
        listHashMaps.add(epics);
    }

    public int generateId() {
        id++;
        return this.id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String printTasks(Map tasks) {
        String tasksString = "\nTasks list: ";
        for (Object key : tasks.keySet()) {
            Task task_out = (Task) tasks.get((Integer) key);
            tasksString += (task_out.toString() + "\n------");
        }
        return tasksString;
    }

    public Task createTask(String name, String description, TaskStatuses status) {
        Task task = new Task(name, description, status, generateId());

        tasks.put(task.getId(), task);
        return task;
    }

    public void updateTask(Task task, String name, String description, TaskStatuses status) {
        task.name = name;
        task.description = description;
        task.status = status;
    }

    public Subtask createSubtask(String name, String description, TaskStatuses status, Epic taskEpic) {
        Subtask subtask = new Subtask(name, description, status, generateId(), taskEpic);
        subtasks.put(subtask.getId(), subtask);
        taskEpic.addSubtask(subtask);
        return subtask;
    }

    public void updateSubtask(Subtask subtask, String name, String description, TaskStatuses status) {
        subtask.name = name;
        subtask.description = description;
        subtask.status = status;
        subtask.taskEpic.status = subtask.getTaskEpic().getEpicStatus();
    }

    public Epic createEpic(String name, String description) {
        Epic epic = new Epic(name, description, generateId());

        epics.put(epic.getId(), epic);
        return epic;
    }

    public void updateEpic(Epic epic, String name, String description) {
        epic.name = name;
        epic.description = description;
        epic.status = epic.getEpicStatus();
    }

    public void showAllTasks() {
        for (Map map : listHashMaps) {
            if (!map.isEmpty()) {
                System.out.println(this.printTasks(map));
            } else {
                System.out.println("Task list is empty");
            }
        }
    }

    public void deleteAllTasks() {
        System.out.println("Are you sure you want to delete all tasks? (Y/N)");
        String answerDelete = scanner.next();
        if (answerDelete.equals("Y")) {
            for (Map map : listHashMaps) {
                map.clear();
            }
            this.setId(0);
            System.out.println("Deletion completed");
        } else if (answerDelete.equals("N")) {
            System.out.println("Deletion canceled");
        } else {
            System.out.println("Command not supported");
        }
    }

    public void createTask() {
        int taskType;
        int statusId;
        int idToEpic;
        String name;
        String description;
        TaskStatuses status;

        System.out.println("Select task type(1 - task, 2 - subtask, 3 - epic): ");
        taskType = scanner.nextInt();
        System.out.println("Input name task: ");
        name = scanner.next();
        System.out.println("Input description task: ");
        description = scanner.next();
        switch (taskType) {
            case 1: {
                System.out.println("Select status task(0 - new, 1 - in_progress, 2 - done): ");
                statusId = scanner.nextInt();
                if (statusId == 0) {
                    status = TaskStatuses.NEW;
                } else if (statusId == 1) {
                    status = TaskStatuses.IN_PROGRESS;
                } else if (statusId == 2) {
                    status = TaskStatuses.DONE;
                } else {
                    System.out.println("Command not support");
                    break;
                }
                this.createTask(name, description, status);
                break;
            }
            case 2: {
                System.out.println("Select status task(0 - new, 1 - in_progress, 2 - done): ");
                statusId = scanner.nextInt();
                if (statusId == 0) {
                    status = TaskStatuses.NEW;
                } else if (statusId == 1) {
                    status = TaskStatuses.IN_PROGRESS;
                } else if (statusId == 2) {
                    status = TaskStatuses.DONE;
                } else {
                    System.out.println("Command not support");
                    break;
                }
                System.out.println("Input epic ID: ");
                idToEpic = scanner.nextInt();
                if (epics.get(idToEpic) != null) {
                    Epic getEpic = epics.get(idToEpic);
                    createSubtask(name, description, status, getEpic);
                } else {
                    System.out.println("Epic not found");
                }
                break;
            }
            case 3: {
                createEpic(name, description);
                break;
            }
            default: {
                System.out.println("Command not support");
            }
        }
    }

    public void updateTaskFromMenu() {
        String name;
        String description;
        TaskStatuses status;
        int statusId;

        System.out.println("Input task ID: ");
        int id = scanner.nextInt();
        for (HashMap hashMapIterator : listHashMaps) {
            if (hashMapIterator.get(id) != null) {
                if (hashMapIterator.get(id) instanceof Task) {
                    task = (Task) hashMapIterator.get(id);
                    System.out.println("Old name: " + task.name + "\nInput new task name: ");
                    name = scanner.next();
                    System.out.println("Old description: " + task.description + "\nInput new description task: ");
                    description = scanner.next();
                    System.out.println("Old status: " + task.status + "\nSelect new status task(0 - new, 1 - in_progress, 2 - done): ");
                    statusId = scanner.nextInt();
                    if (statusId == 0) {
                        status = TaskStatuses.NEW;
                    } else if (statusId == 1) {
                        status = TaskStatuses.IN_PROGRESS;
                    } else if (statusId == 2) {
                        status = TaskStatuses.DONE;
                    } else {
                        System.out.println("Command not support");
                        break;
                    }
                    updateTask(task, name, description, status);
                } else if (hashMapIterator.get(id) instanceof Subtask) {
                    subtask = (Subtask) hashMapIterator.get(id);
                    System.out.println("Old name: " + subtask.name + "\nInput new task name: ");
                    name = scanner.next();
                    System.out.println("Old description: " + subtask.description + "\nInput new description task: ");
                    description = scanner.next();
                    System.out.println("Old status: " + subtask.status
                            + "\nSelect new status task(0 - new, 1 - in_progress, 2 - done): ");
                    statusId = scanner.nextInt();
                    if (statusId == 0) {
                        status = TaskStatuses.NEW;
                    } else if (statusId == 1) {
                        status = TaskStatuses.IN_PROGRESS;
                    } else if (statusId == 2) {
                        status = TaskStatuses.DONE;
                    } else {
                        System.out.println("Command not support");
                        break;
                    }
                    updateSubtask(subtask, name, description, status);
                } else if (hashMapIterator.get(id).getClass() == Epic.class) {
                    epic = (Epic) hashMapIterator.get(id);
                    System.out.println("Old name: " + epic.name + "\nInput new task name: ");
                    name = scanner.next();
                    System.out.println("Old description: " + epic.description + "\nInput new description task: ");
                    description = scanner.next();
                    updateEpic(epic, name, description);
                }
            }
        }
    }

    public void deleteTaskById(int id) {
        if (tasks.get(id) != null) {
            tasks.remove(id);
        } else if (subtasks.get(id) != null) {
            subtask = subtasks.get(id);
            epic = epics.get(subtask.getTaskEpic());
            epic.deleteSubtask(subtask);
            subtasks.remove(id);
        } else if (epics.get(id) != null) {
            epic = epics.get(id);
            List<Subtask> epicSubtask = epic.getSubtasks();
            for (Subtask subtask : epicSubtask) {
                subtasks.remove(subtask.getId());
            }
            epics.remove(id);
        } else {
            System.out.println("Task not found");
        }
        System.out.println(id + " deleted");
    }

    public void getAllTaskByEpic(int id) {
        if (epics.get(id) != null) {
            epic = epics.get(id);
            for (Subtask subtask : epic.getSubtasks()) {
                System.out.println(subtask);
            }
        } else {
            System.out.println("Epic not found");
        }
    }

    public void getTaskById(int id, HistoryManager historyManager) {
        if (id <= this.getId()) {
            for (HashMap hashMapIterator : listHashMaps) {
                if (hashMapIterator.get(id) != null) {
                    System.out.println(hashMapIterator.get(id));
                    historyManager.add((Task) hashMapIterator.get(id));
                }
            }
        } else {
            System.out.println("Task not found");
        }
    }

    public void printMenu() {
        System.out.println("Menu, enter command:");
        System.out.println("1 - List all tasks");
        System.out.println("2 - Delete all task");
        System.out.println("3 - Show task by ID");
        System.out.println("4 - Create task");
        System.out.println("5 - Update task");
        System.out.println("6 - Delete task by ID");
        System.out.println("7 - Show all task of Epic");
        System.out.println("8 - Show history");
        System.out.println("0 - Exit");
    }
}
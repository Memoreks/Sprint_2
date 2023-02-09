package Managers.Task;

import Managers.History.HistoryManager;
import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import Tasks.TaskStatuses;

import java.util.Map;

public interface TaskManager {
    int generateId();
    int getId();
    void setId(int id);
    String printTasks(Map tasks);

    Task createTask(String name, String description, TaskStatuses status);
    void updateTask(Task task, String name, String description, TaskStatuses status);
    Subtask createSubtask(String name, String description, TaskStatuses status, Epic taskEpic);
    void updateSubtask(Subtask subtask, String name, String description, TaskStatuses status);
    Epic createEpic(String name, String description);
    void updateEpic(Epic epic, String name, String description);

    void createTask();
    void showAllTasks();
    void deleteAllTasks();
    void updateTaskFromMenu();
    void deleteTaskById(int id);
    void getAllTaskByEpic(int id);
    void getTaskById(int id, HistoryManager historyManager);

     void printMenu();
}

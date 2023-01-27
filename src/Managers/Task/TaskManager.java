package Managers.Task;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.Map;

public interface TaskManager {
    public int generateId();
    public int getId();
    public void setId(int id);
    public String printTasks(Map tasks);
    public Task createTask(String name, String description, String status);
    public void updateTask(Task task, String name, String description, String status);
    public Subtask createSubtask(String name, String description, String status, Epic taskEpic);
    public void updateSubtask(Subtask subtask, String name, String description, String status);
    public Epic createEpic(String name, String description);
    public void updateEpic(Epic epic, String name, String description);
    public void createTask();
    public void showAllTasks();
    public void deleteAllTasks();
    public void updateTaskFromMenu();
    public void deleteTaskById(int id);
    public void getAllTaskByEpic(int id);
    public void getTaskById(int id);
}

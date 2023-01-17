package Classes;

import java.util.HashMap;

public class Manager {
    private int id;

    public Manager() {
        int id = 0;
    }

    public int addId() {
        id++;
        return this.id;
    }

    public String outTasks(HashMap<Integer, Task> tasks){
        String out_string = "Список задач:\n";
        for(Integer key: tasks.keySet()) {
            Task task_out = tasks.get(key);
            out_string = out_string + ("Key: " + key + "\nValue: " + task_out.toString() + "\n------\n");
        }
        return out_string;
    }

    public String outSubtasks(HashMap<Integer, Subtask> subtasks) {
        String out_string = "Список подзадач:\n";
        for (Integer key : subtasks.keySet()) {
            Subtask subtask_out = subtasks.get(key);
            out_string = out_string + ("Key: " + key + "\nValue: " + subtask_out.toString() + "\n------\n");
        }
        return out_string;
    }

    public String outEpics(HashMap<Integer, Epic> epics) {
        String out_string = "Список епиков:\n";
        for (Integer key : epics.keySet()) {
            Epic epic_out = epics.get(key);
            out_string = out_string + ("Key: " + key + "\nValue: " + epic_out.toString() + "\n------\n");
        }
        return out_string;
    }

    public String getTypeTask(int id){
        return "task";
    }
        /*
        Хранение всех типов задач - выбрать коллекцию

        Статус приходит вместе с информацией о задаче, у эпиков статус пользователь поменять не может

        public void updateEpic(Epic epic)
        epics.put(epic.getId(), epic)*/
}

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

    public int setId(int id) {
        this.id = id;
        return this.id;
    }

    public String outTasks(HashMap<Integer, Task> tasks){
        String out_string = "\nTasks list:";
        for(Integer key: tasks.keySet()) {
            Task task_out = tasks.get(key);
            out_string += (task_out.toString() + "\n------");
        }
        return out_string;
    }

    public String outSubtasks(HashMap<Integer, Subtask> subtasks) {
        String out_string = "\nSubtasks list:";
        for (Integer key : subtasks.keySet()) {
            Subtask subtask_out = subtasks.get(key);
            out_string += (subtask_out.toString() + "\n------");
        }
        return out_string;
    }

    public String outEpics(HashMap<Integer, Epic> epics) {
        String out_string = "\nEpics list:";
        for (Integer key : epics.keySet()) {
            Epic epic_out = epics.get(key);
            out_string += (epic_out.toString() + "\n------");
        }
        return out_string;
    }
}
/*   public String getTypeTask(int id){
        if(tasks.get(id) != null)
            return "task";
        if(subtasks.get(id) != null)
            return "subtask";
        if(epics.get(id) != null)
            return "epic";
    }*/

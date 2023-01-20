package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {
    List<Subtask> subtasks = new ArrayList<>();

    public Epic(String name, String description, String status, int id) {
        super(name, description, status, id);
    }

    public void setSubtasks(Subtask subtask, int type) {
        if(type == 1)
            this.subtasks.add(subtask);
        if(type == 2) {
            for(Subtask anySubtask : subtasks)
            {
                if(anySubtask.equals(subtask))
                    subtasks.remove(subtask);
            }
        }
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "\nID: "+ this.id + "\nName: " + this.name + "\nDescription: " + this.description + "\nStatus: "
                + this.status + "\nSubtasks: " + this.subtasks.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Epic otherEpic = (Epic) obj;
        return Objects.equals(name, otherEpic.name) && Objects.equals(description, otherEpic.description)
                && Objects.equals(status, otherEpic.status);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        if(name != null) {
            hash = hash + name.hashCode();
        }
        hash = hash * 31;
        if(description != null) {
            hash = hash + description.hashCode();
        }
        hash = hash * 31;
        if(status != null) {
            hash = hash + status.hashCode();
        }
        hash = hash * 31;
        return hash;
    }
}

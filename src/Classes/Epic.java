package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {
    List<Subtask> subtasks = new ArrayList<>();

    public Epic(String name, String description, String status, int id) {
        super(name, description, status, id);
        if(subtasks.size() == 0) {
            status = "NEW";
        } else {
            status = null;
            for (Subtask anySubtask : subtasks) {
                if ((status == null || status.equals("NEW")) && anySubtask.status.equals("NEW")) {
                    status = "NEW";
                } else if ((status == null || status.equals("DONE")) && anySubtask.status.equals("DONE")) {
                    status = "DONE";
                } else {
                    status = "IN_PROGRESS";
                }
            }
        }
    }

    public void setSubtasks(Subtask subtask, int type) {
        if(type == 1) {
            this.subtasks.add(subtask);
            status = null;
            for (Subtask anySubtask : subtasks) {
                if ((status == null || status.equals("NEW")) && anySubtask.status.equals("NEW")) {
                    status = "NEW";
                } else if ((status == null || status.equals("DONE")) && anySubtask.status.equals("DONE")) {
                    status = "DONE";
                } else {
                    status = "IN_PROGRESS";
                }
            }
        } if(type == 2) {
            for(Subtask anySubtask : this.subtasks)
            {
                if(anySubtask.equals(subtask)) {
                    this.subtasks.remove(subtask);
                    break;
                }
            }
            if(subtasks.size() == 0) {
                status = "NEW";
            }
            else{
                status = null;
                for (Subtask anySubtask : subtasks) {
                    if ((status == null || status.equals("NEW")) && anySubtask.status.equals("NEW")) {
                        status = "NEW";
                    } else if ((status == null || status.equals("DONE")) && anySubtask.status.equals("DONE")) {
                        status = "DONE";
                    } else {
                        status = "IN_PROGRESS";
                    }
                }
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
                && Objects.equals(status, otherEpic.status) && (id == otherEpic.id);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        if((name != null) && (description != null)) {
            hash = hash + Objects.hash(name, description);
        }
        hash = hash * 31;
        if(status != null) {
            hash = hash + status.hashCode() + id;
        }
        return hash;
    }
}

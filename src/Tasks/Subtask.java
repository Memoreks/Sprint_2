package Tasks;

import java.util.Objects;

public class Subtask extends Task {
    public Epic taskEpic;

    public Subtask(String name, String description, TaskStatuses status, int id, Epic taskEpic) {
        super(name, description, status, id);
        this.taskEpic = taskEpic;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "\nID: "+ this.id + "\nName: " + this.name + "\nDescription: " + this.description + "\nStatus: "
                + this.status + "\nTo epic: " + this.taskEpic.getId();
    }

    public Epic getTaskEpic(){
        return this.taskEpic;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Subtask otherSubtask = (Subtask) obj;
        return Objects.equals(name, otherSubtask.name) && Objects.equals(description, otherSubtask.description)
                && Objects.equals(status, otherSubtask.status) && (id == otherSubtask.id);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        if(name != null && description != null) {
            hash = hash + Objects.hash(name, description);
        }
        if(status != null) {
            hash = hash * 31 + status.hashCode() + id;
        }
        return hash;
    }
}
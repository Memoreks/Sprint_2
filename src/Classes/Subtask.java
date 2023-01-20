package Classes;

import java.util.Objects;

public class Subtask extends Task {
    int idEpicTask;

    public Subtask(String name, String description, String status, int id, int idEpicTask) {
        super(name, description, status, id);
        this.idEpicTask = idEpicTask;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "\nID: "+ this.id + "\nName: " + this.name + "\nDescription: " + this.description + "\nStatus: "
                + this.status + "\nTo epic: " + this.idEpicTask;
    }

    public int getIdEpicTask(){
        return this.idEpicTask;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Subtask otherSubtask = (Subtask) obj;
        return Objects.equals(name, otherSubtask.name) && Objects.equals(description, otherSubtask.description)
                && Objects.equals(status, otherSubtask.status);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        if(name != null && description != null) {
            hash = hash + Objects.hash(name, description);
        }
        if(status != null) {
            hash = hash * 31 + status.hashCode();
        }
        return hash;
    }
}
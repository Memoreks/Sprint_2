package Tasks;

import java.util.Objects;

public class Task {
    public String name;
    public String description;
    public String status;
    int id;

    public Task(String name, String description, String status, int id) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "\nID: "+ this.id + "\nName: " + this.name + "\nDescription: " + this.description + "\nStatus: "
                + this.status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Task otherTask = (Task) obj;
        return Objects.equals(name, otherTask.name) && Objects.equals(description, otherTask.description)
                && Objects.equals(status, otherTask.status) && (id == otherTask.id);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        if((name != null) && (description != null)) {
            hash = hash + Objects.hash(name, description);
        }
        if(status != null) {
            hash = hash * 31 + status.hashCode() + id;
        }
        return hash;
    }
}
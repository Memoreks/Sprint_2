package Classes;

import java.util.Objects;

public class Task {
    String name;
    String description;
    String status;
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
                && Objects.equals(status, otherTask.status);
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
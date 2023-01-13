package Classes;

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
        return "\nname: " + this.name + "\ndescription: " + this.description + "\nstatus: " + this.status;
    }
}
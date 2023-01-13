package Classes;

public class Subtask extends Task {
    int id_epictask;

    public Subtask(String name, String description, String status, int id, int id_epictask) {
        super(name, description, status, id);
        this.id_epictask = id_epictask;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "\nname: " + this.name + "\ndescription: " + this.description + "\nstatus: " + this.status;
    }
}

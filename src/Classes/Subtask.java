package Classes;

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
}
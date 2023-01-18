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
        return "\nИД: "+ this.id + "\nИмя: " + this.name + "\nОписание: " + this.description + "\nСтатус: "
                + this.status + "\nК какому эпику относится: " + this.idEpicTask;
    }

    public int getIdEpicTask(){
        return this.idEpicTask;
    }
}
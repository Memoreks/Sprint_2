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
        return "\n��: "+ this.id + "\n���: " + this.name + "\n��������: " + this.description + "\n������: "
                + this.status + "\n� ������ ����� ���������: " + this.idEpicTask;
    }

    public int getIdEpicTask(){
        return this.idEpicTask;
    }
}
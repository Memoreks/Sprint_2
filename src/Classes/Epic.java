package Classes;

import java.util.ArrayList;

public class Epic extends Task {
    ArrayList<Integer> idSubtasks = new ArrayList<>();

    public Epic(String name, String description, String status, int id) {
        super(name, description, status, id);
    }

    public void setIdSubtasks(int idSubtasks) {
        this.idSubtasks.add(idSubtasks);
    }

    public ArrayList<Integer> getIdSubtasks() {
        return idSubtasks;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "\n��: "+ this.id + "\n���: " + this.name + "\n��������: " + this.description + "\n������: "
                + this.status + "\n����� ��������� ��������: " + this.idSubtasks;
    }
}

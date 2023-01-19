package Classes;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    List<Integer> idSubtasks = new ArrayList<>();

    public Epic(String name, String description, String status, int id) {
        super(name, description, status, id);
    }

    public void setIdSubtasks(int idSubtasks, int type) {
        if(type == 1)
            this.idSubtasks.add(idSubtasks);
        if(type == 2) {
            for(int key : this.idSubtasks)
            {
                if(this.idSubtasks.get(key) == idSubtasks)
                    this.idSubtasks.remove(key);
            }
        }
    }

    public List<Integer> getIdSubtasks() {
        return idSubtasks;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "\nID: "+ this.id + "\nName: " + this.name + "\nDescription: " + this.description + "\nStatus: "
                + this.status + "\nSubtasks: " + this.idSubtasks.toString();
    }
}

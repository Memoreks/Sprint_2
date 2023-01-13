package Classes;

//����������� � ������������ �������
public class Epic extends Task {
    int[] id_subtask = {0};//�������������� ����� �������� - ����������

    public Epic(String name, String description, String status, int id) {
        super(name, description, status, id);
        this.id_subtask[0] = 1;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "\nname: " + this.name + "\ndescription: " + this.description + "\nstatus: " + this.status;
    }
}

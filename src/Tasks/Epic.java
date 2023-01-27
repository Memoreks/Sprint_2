package Tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {
    public List<Subtask> subtasks;

    public Epic(String name, String description, int id) {
        super(name, description, "NEW", id);
        subtasks = new ArrayList<>();
    }

    public void addSubtask(Subtask subtask) {
        this.subtasks.add(subtask);
        this.status = getEpicStatus();
    }

    public String getEpicStatus() {
        int newTaskCount = 0;
        int doneTaskCount = 0;
        for (Subtask anySubtask : subtasks) {
            if (anySubtask.status.equals("NEW")) {
                newTaskCount++;
            } else if (anySubtask.status.equals("DONE")) {
                doneTaskCount++;
            }
        }
        if (subtasks.size() == 0 || subtasks.size() == newTaskCount) {
            return "NEW";
        } else if (subtasks.size() == doneTaskCount) {
            return "DONE";
        } else {
            return "IN_PROGRESS";
        }
    }

    public void deleteSubtask(Subtask subtask) {
        for (Subtask anySubtask : this.subtasks) {
            if (anySubtask.getId() == subtask.getId()) {
                this.subtasks.remove(subtask);
                break;
            }
        }
        this.status = getEpicStatus();
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "\nID: " + this.id + "\nName: " + this.name + "\nDescription: " + this.description + "\nStatus: "
                + this.status + "\nSubtasks: " + this.subtasks.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Epic otherEpic = (Epic) obj;
        return Objects.equals(name, otherEpic.name) && Objects.equals(description, otherEpic.description)
                && Objects.equals(status, otherEpic.status) && (id == otherEpic.id);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        if ((name != null) && (description != null)) {
            hash = hash + Objects.hash(name, description);
        }
        hash = hash * 31;
        if (status != null) {
            hash = hash + status.hashCode() + id;
        }
        return hash;
    }
}
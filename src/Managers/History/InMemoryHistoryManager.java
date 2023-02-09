package Managers.History;

import Tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private static List<Task> listHistory;

    public InMemoryHistoryManager() {
        listHistory = new ArrayList<>(10);//10 длина при переполнении удалять самый старый
    }

    @Override
    public void add(Task task) {
        listHistory.add(task);
    }

    @Override
    public HistoryManager getHistory() {
        if (listHistory.isEmpty()) {
            System.out.println("History list is empty");
        } else {
            for (Task task : listHistory) {
                System.out.println(task);
            }
        }
        return null;
    }
}

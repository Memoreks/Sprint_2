package Managers.History;

import Tasks.Task;

public interface HistoryManager {
    void add(Task task);
    HistoryManager getHistory();
}

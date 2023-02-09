package Managers;

import Managers.History.HistoryManager;
import Managers.History.InMemoryHistoryManager;
import Managers.Task.InMemoryTaskManager;
import Managers.Task.TaskManager;

public class Managers {
    public static TaskManager getDefault(){
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        return taskManager;
    }

    public static HistoryManager getDefaultHistory(){
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        historyManager.getHistory();
        return historyManager;
    }
}

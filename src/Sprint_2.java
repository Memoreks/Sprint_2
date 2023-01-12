import java.util.HashMap;
import java.util.Map;

public class Sprint_2 {
    public static void main(String[] args) {

        HashMap tasks = new HashMap();
        HashMap subtasks = new HashMap();
        HashMap epics = new HashMap();

        String name = "Задача";
        String description = "Пробная задача";
        Task task = new Task(name, description, tasks.size());
        tasks.put(tasks.size(), task);

        name = "Подзадача";
        Subtask subtask = new Subtask(name, description, subtasks.size(), 1);
        subtasks.put(subtasks.size(), subtask);

        name = "Большая задача";
        Epic epic = new Epic(name, description, epics.size());
        epics.put(epics.size(), epic);

        System.out.println("Список задач:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("Key :"+i + "   Value :"+tasks.get(i));
        }
        System.out.println("Список подзадач:");
        for (int i = 0; i < subtasks.size(); i++) {
            System.out.println("Key :"+i + "   Value :"+subtasks.get(i));
        }
        System.out.println("Список епиков:");
        for (int i = 0; i < epics.size(); i++) {
            System.out.println("Key :"+i + "   Value :"+epics.get(i));
        }
    }

    /*public static int manager(){
        int id;
        id = tasks.size();
        return id;
        Класс для объекта менеджера
        Объект менеджер
        Хранение всех типов задач - выбрать коллекцию
        Методы - Получение списка всех задач, Удаление всех задач, Получение задачи по ИД, создание задачи, сам объект должен передаваться в качестве параметра,
        обновление задачи, новая версия объекта и ИД передаются ввиде параметра, удаление по ИД
        Дополнительно - получение всех подзадач эпика

        ИД уникальное значение для 3-х хешмапов

        Статус приходит вместе с информацией о задаче, у эпиков статус пользователь поменять не может

        В хешмап надо записывать объект

        public void updateEpic(Epic epic)
        epics.put(epic.getId(), epic)
    }*/
}

class Task {
    String name;
    String description;
    int id;
    int status;

    public Task(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.id = id; // назначение через менеджера
        this.status = 0;
        /*
        new - 0
        in_progress - 1
        done - 2
        Сделать хешмапы Ид - название задачи
        */
    }
}

class Subtask extends Task{
       int id_epictask;

       public Subtask(String name, String description, int id, int id_epictask){
           super(name, description, id);
           this.id_epictask = id_epictask;
       }
}
//Разобраться с определением массива
class Epic extends Task{
    int[] id_subtask = {0};//Неограниченное число подзадач - переделать

    public Epic(String name, String description, int id){
        super(name, description, id);
        this.id_subtask[0] = 1;
    }
}
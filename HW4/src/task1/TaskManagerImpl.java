package task1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TaskManagerImpl implements TaskManager {

    public Map<LocalDateTime, Task> taskStore = new TreeMap<>();

    @Override
    public void add(LocalDateTime date, Task task) {
        taskStore.put(date, task);
    }

    @Override
    public void remove(LocalDateTime date) {
        Iterator<LocalDateTime> iterator = taskStore.keySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(date)) {
                iterator.remove();
            }
        }
    }

    @Override
    public Set<String> getCategories() {
        Set<String> result = new TreeSet<>();
        for (Map.Entry<LocalDateTime, Task> entry : taskStore.entrySet()) {
            result.add(entry.getValue().getCategory());
        }
        return result;
    }

    @Override
    public Map<String, List<Task>> getTasksByCategories(String... categories) {
        Map<String, List<Task>> result = new TreeMap<>();
        List<Task> temp;
        for (String category : categories) {
            temp = new ArrayList<>();
            for (Task task : taskStore.values()) {
                if (task.getCategory().equals(category)) {
                    temp.add(task);
                }
            }
            result.put(category, temp);
        }
        return result;
    }

    @Override
    public List<Task> getTasksByCategory(String category) {
        List<Task> result = new ArrayList<>();
        for (Map.Entry<LocalDateTime, Task> entry : taskStore.entrySet()) {
            Task task = entry.getValue();
            if (entry.getValue().getCategory().equals(category)) {
                result.add(task);
            }
        }
        return result;
    }

    @Override
    public List<Task> getTasksForToday() {
        List<Task> result = new ArrayList<>();
        LocalDate currentDay = LocalDate.now();
        for (Map.Entry<LocalDateTime, Task> entry : taskStore.entrySet()) {
            LocalDateTime localDateTime = entry.getKey();
            if (currentDay.isEqual(entry.getKey().toLocalDate())) {
                result.add(taskStore.get(localDateTime));
            }
        }
        return result;
    }
}
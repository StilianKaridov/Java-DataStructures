package fundamentals.exams.April_05th.main.java.core;

import fundamentals.exams.April_05th.main.java.model.Task;
import fundamentals.exams.April_05th.main.java.shared.Scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessScheduler implements Scheduler {

    private final List<Task> data;

    public ProcessScheduler() {
        this.data = new ArrayList<>();
    }

    @Override
    public void add(Task task) {
        this.data.add(task);
    }

    @Override
    public Task process() {
        if (checkIfEmpty()) {
            return null;
        }

        return this.data.remove(0);
    }

    @Override
    public Task peek() {
        if (checkIfEmpty()) {
            return null;
        }

        return this.data.get(0);
    }

    @Override
    public Boolean contains(Task task) {
        for (Task t : this.data) {
            if (t.equals(task)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public Boolean remove(Task task) {
        if (checkIfEmpty()) {
            return false;
        }

        boolean isRemoved = this.data.remove(task);

        if (!isRemoved) {
            throw new IllegalArgumentException();
        }

        return true;
    }

    @Override
    public Boolean remove(int id) {
        if (checkIfEmpty()) {
            return false;
        }

        boolean isRemoved = this.data.removeIf(t -> t.getId() == id);

        if (!isRemoved) {
            throw new IllegalArgumentException();
        }

        return true;
    }

    @Override
    public void insertBefore(int id, Task task) {
        Task taskById = find(id);
        int index = this.data.indexOf(taskById);

        this.data.add(index, task);
    }

    @Override
    public void insertAfter(int id, Task task) {
        Task taskById = find(id);
        int index = this.data.indexOf(taskById);

        this.data.add(index + 1, task);
    }

    @Override
    public void clear() {
        this.data.clear();
    }

    @Override
    public Task[] toArray() {
        Task[] arr = new Task[this.data.size()];

        return this.data.toArray(arr);
    }

    @Override
    public void reschedule(Task first, Task second) {
        int indexOfFirst = this.data.indexOf(first);

        if (indexOfFirst < 0) {
            throw new IllegalArgumentException();
        }

        int indexOfSecond = this.data.indexOf(second);

        if (indexOfSecond < 0) {
            throw new IllegalArgumentException();
        }

        Collections.swap(this.data, indexOfFirst, indexOfSecond);
    }

    @Override
    public List<Task> toList() {
        return new ArrayList<>(this.data);
    }

    @Override
    public void reverse() {
        Collections.reverse(this.data);
    }

    @Override
    public Task find(int id) {
        return this.data
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Task find(Task task) {
        return this.data
                .stream()
                .filter(t -> t.equals(task))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private boolean checkIfEmpty() {
        return this.size() == 0;
    }
}

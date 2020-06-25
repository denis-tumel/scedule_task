package com.tumel;

import java.util.*;

public class ISchedulerImpl implements IScheduler {

    private final List<Task> sortTasks = new ArrayList<>();

    @Override
    public List<Task> schedule(List<Task> tasks) {
        tasks.forEach(t -> sorting(t, tasks));
        return sortTasks;
    }

    public void sorting(Task task, List<Task> tasks) {
        if (sortTasks.stream().anyMatch(t -> t.getName().equals(task.getName()))) {
            return;
        }
        if (task.getPredecessors().isEmpty()) {
            sortTasks.add(task);
            return;
        }
        task.getPredecessors().forEach( predecessor -> {
            final Task predecessorTask = tasks.stream()
                    .filter(t -> t.getName().equals(predecessor))
                    .findFirst()
                    .orElse(null);
            sorting(predecessorTask, tasks);
        });
        sortTasks.add(task);
    }
}

package com.todo.serviceImpl;

import com.todo.entity.Task;
import com.todo.model.CurrentUser;
import com.todo.repository.TaskRepository;
import com.todo.service.TaskSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component("taskSecurityService")
public class TaskSecurityServiceImpl implements TaskSecurityService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskSecurityServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public boolean isAuthor(CurrentUser user, Long author) {
        return Objects.equals(user.getId(), author);
    }

    @Override
    public boolean isOwnerOf(CurrentUser user, Task task) {
        return Objects.equals(user.getId(), task.getAuthor());
    }

    @Override
    public boolean isOwnerOf(CurrentUser user, List<Task> tasks) {
        return tasks.stream().noneMatch((task -> (!isOwnerOf(user, task))));
    }

    @Override
    public boolean isOwnerOf(CurrentUser user, Long id) {
        Task task = this.taskRepository.findOne(id);

        return task.getAuthor().equals(user.getId());
    }
}

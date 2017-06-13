package com.todo.serviceImpl;

import com.todo.entity.Task;
import com.todo.forms.TaskForm;
import com.todo.model.CurrentUser;
import com.todo.repository.TaskRepository;
import com.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import  org.joda.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTask(TaskForm taskForm) {
        CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Task task = new Task();
        task.setAuthor(user.getId());
        task.setDate(taskForm.getDate());
        task.setStatus(taskForm.isStatus());
        task.setTodo(taskForm.getTodo());

        this.taskRepository.save(task);
    }

    @Override
    public Task findTaskById(Long id) {
        Task task = this.taskRepository.findOne(id);
        return task;
    }

    @Override
    public List<Task> findTaskByAuthorAndDate(Long author, LocalDate date) {
        List<Task> tasks = this.taskRepository.findByAuthorAndDate(author, date);
        return tasks;
    }

    @Override
    public void setTaskStatus(Long id, boolean status) {
        this.taskRepository.setTaskStatus(status, id);
    }

    @Override
    public void pushTask(Long id) {
        Task task = this.taskRepository.findOne(id);

        Task newTask = new Task();
        newTask.setAuthor(task.getAuthor());
        newTask.setStatus(false);
        newTask.setTodo(task.getTodo());
        newTask.setDate(task.getDate().plusDays(1));

        this.taskRepository.save(newTask);
    }

    @Override
    public void removeTask(Long id) {
        this.taskRepository.delete(id);
    }

    @Override
    public int calculateProgress(List<Task> tasks) {
        int completed = 0;
        for (Task task : tasks) {
            if(task.isStatus()) {
                ++completed;
            }
        }

        int n = tasks.size();
        int percentage = (int) (100.0 * completed / (n > 0 ? n : 1));

        return percentage;
    }
}

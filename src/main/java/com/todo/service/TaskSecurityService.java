package com.todo.service;


import com.todo.entity.Task;
import com.todo.model.CurrentUser;

import java.util.List;

public interface TaskSecurityService {

    boolean isAuthor(CurrentUser user, Long author);

    boolean isOwnerOf(CurrentUser user, Task task);

    boolean isOwnerOf(CurrentUser user, List<Task> tasks);

    boolean isOwnerOf(CurrentUser user, Long id);
}

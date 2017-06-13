package com.todo.repository;

import com.todo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import org.joda.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAuthorAndDate(Long author, LocalDate date);

    @Transactional
    @Modifying
    @Query("UPDATE Task SET status = ? WHERE id = ?")
    int setTaskStatus(boolean status, Long id);
}

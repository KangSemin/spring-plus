package org.example.expert.domain.todo.repository;

import java.time.LocalDate;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> , TodoRepositoryCustom{

    @Query("""
        SELECT t FROM Todo t LEFT JOIN FETCH t.user u
                where (:weather is null or t.weather = :wether)
                and ( :startDate is null or t.modifiedAt >= :startDate)
                and (:endDate is null or t.modifiedAt <= :endDate)
                ORDER BY t.modifiedAt DESC
        """)
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable,
    @Param("weather") String weather, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}

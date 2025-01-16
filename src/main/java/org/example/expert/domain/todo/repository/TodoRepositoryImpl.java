package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;


@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(Long todoId) {

        QTodo todo = QTodo.todo;

        return Optional.ofNullable(
            queryFactory.selectFrom(todo)
                .leftJoin(todo.user)
                .where(todo.user.id.eq(todoId))
                .fetchOne()
        );

    }
}

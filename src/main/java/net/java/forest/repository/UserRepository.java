package net.java.forest.repository;

import net.java.forest.model.users_table;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<users_table, Long> {
    @Query("select * from users_table where email = :email")
    Mono<users_table> findByEmail(String email);
    @Query("select * from users_table")
    Flux<users_table> getAllUser();
}

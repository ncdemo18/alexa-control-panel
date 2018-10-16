package com.netcracker.alexa.controlpanel.repository;

        import com.netcracker.alexa.controlpanel.model.db.entity.userpage.User;
        import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);

    boolean existsUserByLogin(String login);
}

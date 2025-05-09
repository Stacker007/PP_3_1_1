package by.petrushenko.PP_3_1_1.repository;

import by.petrushenko.PP_3_1_1.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    
    List<User> findUserByEmail(String email);


}

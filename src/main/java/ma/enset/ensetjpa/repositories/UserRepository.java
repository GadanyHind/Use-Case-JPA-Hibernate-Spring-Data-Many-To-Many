package ma.enset.ensetjpa.repositories;

import ma.enset.ensetjpa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByUserName(String userName);

}

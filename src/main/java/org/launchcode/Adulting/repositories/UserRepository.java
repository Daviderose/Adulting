package org.launchcode.Adulting.repositories;

import org.launchcode.Adulting.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
    User findByPassword(String password);
}

package org.launchcode.Adulting.models.data;

import org.launchcode.Adulting.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

// Interfaces with User object
@Repository
@Transactional
public interface UserDao extends CrudRepository< User, Integer > {

    User findByUsername(String username);
}


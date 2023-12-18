package com.copoglu.copoglublog.repository;

import com.copoglu.copoglublog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User , Long> {


    User findByUsernameAndPassword(String username, String password);



    User findByUsername(String username);

}

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User save(User hoidanit);

    List<User> findByEmailAndAddress(String email, String address);

    List<User> findByEmail(String email);

    List<User> findAll();

    User findById(long id);

    void deleteById(long id);
}

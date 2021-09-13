package com.auth.Signing.Project_Login.Repo;

import com.auth.Signing.Project_Login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     Optional<User> findByEmailid(String s);

     @Query(value = "Select * from users1 where provider ='local' and emailid=:n", nativeQuery = true)
     Optional<User> findByLocalEmailid(@Param("n") String s);

     Optional<User> findByVerificationCode(String s);

     @Query(value="Update users1 set enabled = true where emailid=:n", nativeQuery = true)
     void setEnablerByEmailid(@Param("n") String s);

}

package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read a user with an email?

    Optional<User> findByEmail(String email);

    //Write a derived query to read a user with an username?
    Optional<User> findByUsername(String userName);

    //Write a derived query to list all users that contain a specific name?
    List<User> findAllByUsernameContains(String pattern);

    //Write a derived query to list all users that contain a specific name in the ignore case mode?
    List<User> findAllByUsernameContainsAndUsernameIgnoreCase(String pattern);

    //Write a derived query to list all users with an age greater than a specified age?
    List<User> findAllByAccountAgeGreaterThan(Integer age);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns a user read by email?
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> retrieveByEmail(@Param("email")String email);

    //Write a JPQL query that returns a user read by username?
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    Optional<User> retrieveByUserName(@Param("userName")String userName);

    //Write a JPQL query that returns all users?
    @Query("SELECT u FROM User u")
    List<User> returnAllUsersJPQL();

    // ------------------- Native QUERIES ------------------- //

    //Write a native query that returns all users that contain a specific name?
    @Query(value = "SELECT * FROM user_account WHERE username CONTAINS ?1", nativeQuery = true)
    List<User> returnAllUsersContainSpecificName(@Param("name") String name);

    //Write a native query that returns all users?
    @Query(value = "SELECT * FROM user_account",nativeQuery = true)
    List<User> returnAllUsersNative();

    //Write a native query that returns all users in the range of ages?
    @Query(value = "SELECT * FROM account_details WHERE age BETWEEN ?1 AND ?2",nativeQuery = true)
    List<User> returnAllUserInAgeRange(@Param("age1")Integer age1, @Param("age2")Integer age2);

    //Write a native query to read a user by email?
    @Query(value = "SELECT * FROM user_account WHERE email = ?1", nativeQuery = true)
    Optional<User> readByEmail(@Param("email")String email);

}

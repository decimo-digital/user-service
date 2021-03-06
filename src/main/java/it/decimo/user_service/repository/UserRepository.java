package it.decimo.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.decimo.user_service.model.AuthUser;

@Repository
public interface UserRepository extends JpaRepository<AuthUser, Integer> {

}

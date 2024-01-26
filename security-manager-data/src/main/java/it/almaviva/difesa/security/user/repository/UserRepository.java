package it.almaviva.difesa.security.user.repository;

import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.almaviva.difesa.security.user.entity.User;
import it.almaviva.difesa.shared.data.repository.GenericRepository;
import it.almaviva.difesa.shared.data.repository.GenericSearchRepository;


@Repository
public interface UserRepository extends GenericRepository<User, Long>, GenericSearchRepository<User> {

//    Optional<User> findByEmployeeId(Long employeeId);
    Optional<User> findByUserId(@Param("userId") Long employeeId);
}

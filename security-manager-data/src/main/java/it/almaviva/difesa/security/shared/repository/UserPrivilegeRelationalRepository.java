package it.almaviva.difesa.security.shared.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.almaviva.difesa.security.shared.composite.UserPrivilegeCompositeKey;
import it.almaviva.difesa.security.shared.relational.UserPrivilegeRelational;
import it.almaviva.difesa.shared.data.repository.GenericRepository;

@Repository
public interface UserPrivilegeRelationalRepository extends GenericRepository<UserPrivilegeRelational, UserPrivilegeCompositeKey> {

    @Query("select u.id.privilegeId from UserPrivilegeRelational u " +
            "where u.id.userId = :userId")
    List<Long> findByUserId(Long userId);
}

package it.almaviva.difesa.security.shared.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.almaviva.difesa.security.shared.composite.UserRoleCompositeKey;
import it.almaviva.difesa.security.shared.relational.UserRoleRelational;
import it.almaviva.difesa.shared.data.repository.GenericRepository;

@Repository
public interface UserRoleRelationalRepository extends GenericRepository<UserRoleRelational, UserRoleCompositeKey> {

    @Query("select u.id.roleId from UserRoleRelational u " +
            "where u.id.userId = :userId")
    List<Long> findByUserId(@Param("userId")Long userId);

    @Query("from UserRoleRelational u " +
            "where u.id.roleId = :roleId")
    List<UserRoleRelational> findByRoleId(@Param("roleId") Long roleId);
}

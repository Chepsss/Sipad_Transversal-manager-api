package it.almaviva.difesa.security.shared.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.almaviva.difesa.security.shared.composite.RolePrivilegeCompositeKey;
import it.almaviva.difesa.security.shared.relational.RolePrivilegeRelational;
import it.almaviva.difesa.shared.data.repository.GenericRepository;

@Repository
public interface RolePrivilegeRelationalRepository extends GenericRepository<RolePrivilegeRelational, RolePrivilegeCompositeKey> {

    @Query("select r.id.privilegeId from RolePrivilegeRelational r " +
            "where r.id.roleId = :roleId")
    List<Long> findByRoleIdIn(Long roleId);
}

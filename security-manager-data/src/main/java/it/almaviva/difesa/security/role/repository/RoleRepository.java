package it.almaviva.difesa.security.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.almaviva.difesa.security.role.entity.Role;
import it.almaviva.difesa.shared.data.repository.GenericRepository;
import it.almaviva.difesa.shared.data.repository.GenericSearchRepository;

@Repository
public interface RoleRepository extends GenericRepository<Role, Long>, GenericSearchRepository<Role> {

    @Query("select r.id from Role r")
    List<Long> getAllIds();
}

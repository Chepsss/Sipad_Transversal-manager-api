package it.almaviva.difesa.security.privilege.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.almaviva.difesa.security.privilege.entity.Privilege;
import it.almaviva.difesa.shared.data.repository.GenericRepository;

@Repository
public interface PrivilegeRepository extends GenericRepository<Privilege, Long> {

    @Query("select p.id from Privilege p")
    List<Long> getAllIds();
    
    @Query(value = "select p " +
            "from Privilege p " +
            "inner join UserPrivilegeRelational up " +
            "on p.id = up.id.privilegeId " +
            "where up.id.userId = :userId")
    List<Privilege> findPrivilegesByUserId(@Param("userId") Long userId);

}

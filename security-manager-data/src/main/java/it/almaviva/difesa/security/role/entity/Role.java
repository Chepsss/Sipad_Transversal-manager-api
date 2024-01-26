package it.almaviva.difesa.security.role.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.Hibernate;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.domain.Sort;

import it.almaviva.difesa.shared.data.entity.GenericEntity;
import it.almaviva.difesa.shared.data.util.SortConstant;
import it.almaviva.difesa.shared.data.util.Sortable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Table(name = "TP_TRRUO_RUOLO")
@Getter
@ToString
@NoArgsConstructor
@Setter(AccessLevel.NONE)
//@EntityListeners(PreventAnyDataManipulation.class)	
public class Role implements GenericEntity, Sortable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleGenerator")
    @SequenceGenerator(name = "roleGenerator", sequenceName = "role_sequence", allocationSize = 1)
    @Column(name = "TRRUO_RUOLO_PK")
    private Long id;

    @NaturalId
    @Column(name = "TRRUO_NOME", unique = true, nullable = false)
    private String name;

    @Column(name = "TRRUO_DESCRIZIONE", nullable = false)
    private String description;

    @Column(name = "TRRUO_COD_RUOLO", nullable = false, unique = true)
    private String roleCode;

    @Override
    public Sort getSort() {
        return SortConstant.SORT_BY_NAME;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return id != null && Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
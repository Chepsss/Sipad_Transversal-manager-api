package it.almaviva.difesa.security.privilege.entity;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TP_TRPRI_PRIVILEGIO")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.NONE)
//@EntityListeners(PreventAnyDataManipulation.class)
public class Privilege implements GenericEntity, Sortable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privilegeGenerator")
    @SequenceGenerator(name = "privilegeGenerator", sequenceName = "privilege_sequence", allocationSize = 1)
    @Column(name = "TRPRI_PRIVILEGIO_PK")
    private Long id;

    @NaturalId
    @Column(name = "TRPRI_NOME")
    private String name;

    @Column(name = "TRPRI_DESCRIZIONE")
    private String description;

    @Override
    public Sort getSort() {
    	return SortConstant.SORT_BY_NAME;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Privilege privilege = (Privilege) o;
        return id != null && Objects.equals(id, privilege.id)
                && name != null && Objects.equals(name, privilege.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

package it.almaviva.difesa.security.shared.relational;

import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import it.almaviva.difesa.security.shared.composite.RolePrivilegeCompositeKey;
import it.almaviva.difesa.shared.data.entity.GenericEntity;
import it.almaviva.difesa.shared.data.util.PreventAnyDataManipulation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "TP_TRRUP_RUOLO_PRIVILEGIO")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(PreventAnyDataManipulation.class)
public class RolePrivilegeRelational implements GenericEntity {

    @EmbeddedId
    @AttributeOverride(name = "roleId", column = @Column(name = "TRRUP_TRRUO_RUOLO_PK"))
    @AttributeOverride(name = "privilegeId", column = @Column(name = "TRRUP_TRPRI_PRIVILEGIO_PK"))
    private RolePrivilegeCompositeKey id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RolePrivilegeRelational that = (RolePrivilegeRelational) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
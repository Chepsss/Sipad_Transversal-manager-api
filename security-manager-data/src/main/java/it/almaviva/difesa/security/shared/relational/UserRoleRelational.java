package it.almaviva.difesa.security.shared.relational;

import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import it.almaviva.difesa.security.shared.composite.UserRoleCompositeKey;
import it.almaviva.difesa.shared.data.entity.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "TR_TRUTR_UTENTE_RUOLO")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleRelational implements GenericEntity {

    @EmbeddedId
    @AttributeOverride(name = "userId", column = @Column(name = "TRUTR_TRUTE_UTENTE_PK"))
    @AttributeOverride(name = "roleId", column = @Column(name = "TRUTR_TRRUO_RUOLO_PK"))
    private UserRoleCompositeKey id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserRoleRelational relation = (UserRoleRelational) o;
        return id != null && Objects.equals(id, relation.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

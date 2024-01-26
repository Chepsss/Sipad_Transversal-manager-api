package it.almaviva.difesa.security.shared.relational;

import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import it.almaviva.difesa.security.shared.composite.UserPrivilegeCompositeKey;
import it.almaviva.difesa.shared.data.entity.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TR_TRUTP_UTENTE_PRIVILEGIO")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserPrivilegeRelational implements GenericEntity {

    @EmbeddedId
    @AttributeOverride(name = "userId", column = @Column(name = "TRUTP_TRUTE_UTENTE_PK"))
    @AttributeOverride(name = "privilegeId", column = @Column(name = "TRUTP_TRPRI_PRIVILEGIO_PK"))
    private UserPrivilegeCompositeKey id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserPrivilegeRelational that = (UserPrivilegeRelational) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

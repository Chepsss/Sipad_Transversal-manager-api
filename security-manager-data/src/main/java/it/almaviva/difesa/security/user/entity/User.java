package it.almaviva.difesa.security.user.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import it.almaviva.difesa.shared.data.entity.GenericEntity;
import it.almaviva.difesa.shared.data.util.SortConstant;
import it.almaviva.difesa.shared.data.util.Sortable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "TB_TRUTE_UTENTE")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User implements GenericEntity, Sortable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userGenerator")
    @SequenceGenerator(name = "userGenerator", sequenceName = "user_sequence", allocationSize = 1)
    @Column(name = "TRUTE_UTENTE_PK")
    private Long id;

    @Column(name = "TRUTE_ANDIP_ID_PK", unique = true, nullable = false)
    private Long userId;

    @Column(name = "TRUTE_COD_FFAA", unique = true, nullable = false)
    private String armedForceCode;

    @CreatedBy
    @Column(name = "TRUTE_COD_INS", nullable = false, updatable = false)
    private String insertCode;

    @CreatedDate
    @Column(name = "TRUTE_DATA_INS", nullable = false, updatable = false)
    private LocalDateTime insertDate;

    @LastModifiedDate
    @Column(name = "TRUTE_DATA_ULT_AGG", nullable = false)
    private LocalDateTime lastUpdatedDate;

    @LastModifiedBy
    @Column(name = "TRUTE_COD_ULT_AGG", nullable = false)
    private String lastUpdatedCode;

    @Override
    public Sort getSort() {
        return SortConstant.SORT_BY_LAST_UPDATED_DATE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
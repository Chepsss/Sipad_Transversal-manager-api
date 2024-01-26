package it.almaviva.difesa.security.shared.composite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RolePrivilegeCompositeKey implements Serializable {

    private Long roleId;
    private Long privilegeId;
}
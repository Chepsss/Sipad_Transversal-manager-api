package it.almaviva.difesa.security.shared.composite;

import lombok.*;
import javax.persistence.Embeddable;
import java.io.Serializable;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserRoleCompositeKey implements Serializable {

    private Long userId;
    private Long roleId;
}

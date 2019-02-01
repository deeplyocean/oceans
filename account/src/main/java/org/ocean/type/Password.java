package org.ocean.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter @Builder @EqualsAndHashCode(of = "value")
@NoArgsConstructor @AllArgsConstructor
public class Password implements Serializable {

    private static final long serialVersionUID = 787853158934909899L;

    @Column(name = "password", nullable = false)
    private String value;

}

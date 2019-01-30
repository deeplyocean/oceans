package org.ocean.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter @Builder @EqualsAndHashCode(of = "value")
@NoArgsConstructor @AllArgsConstructor
public class Password {

    @Column(name = "password", nullable = false)
    private String value;
}

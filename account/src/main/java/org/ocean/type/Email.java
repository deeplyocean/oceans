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
public class Email {

    @javax.validation.constraints.Email
    @Column(name = "email", nullable = false, unique = true)
    private String value;

    public String getId(){
        return value.substring(0,value.indexOf('@'));
    }

    public String getHost(){
        return value.substring(value.indexOf('@')+1);
    }
}

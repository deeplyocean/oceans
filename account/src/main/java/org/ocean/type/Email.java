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
public class Email implements Serializable {

    private static final long serialVersionUID = -6319732535486161195L;

    @javax.validation.constraints.Email
    @Column(name = "email", nullable = false, unique = true)
    private String value;

    public String getId(){
        if(value.indexOf('@') == -1){
            return value;
        }
        return value.substring(0,value.indexOf('@'));
    }

    public String getHost(){
        if(value.indexOf('@') == -1){
            return "";
        }
        return value.substring(value.indexOf('@')+1);
    }
}

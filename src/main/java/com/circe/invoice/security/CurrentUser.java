package com.circe.invoice.security;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Builder
public class CurrentUser extends User {

    private Long id;
    private String companyName;

    public CurrentUser(String codeClient, String password, List<GrantedAuthority> authorities, Long id, String companyName) {

        super(codeClient, password, authorities);
        this.id = id;
        this.companyName = companyName;
    }
}

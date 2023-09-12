package com.deyaco.taixingauth2.support;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class RegisteredUser extends User {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String mail;
    private String mobile;
    private String displayName;
    private boolean administrator;
    private String gender;
    private String employeeNumber;
    private String avatarFid;

    public RegisteredUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Long id, String mail, String mobile, String displayName, boolean administrator, String gender, String employeeNumber, String avatarFid) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.mail = mail;
        this.mobile = mobile;
        this.displayName = displayName;
        this.administrator = administrator;
        this.gender = gender;
        this.employeeNumber = employeeNumber;
        this.avatarFid = avatarFid;
    }
}

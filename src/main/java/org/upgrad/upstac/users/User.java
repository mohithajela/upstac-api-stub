package org.upgrad.upstac.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.upgrad.upstac.users.models.Gender;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.upgrad.upstac.users.models.AccountStatus;
import org.upgrad.upstac.users.roles.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;

    @Column
    @JsonIgnore
    @ToString.Exclude
    private String password;

    private LocalDateTime created;

    private LocalDate dateOfBirth;


    private LocalDateTime updated;

    private String firstName;

    private AccountStatus status;

    @Column(unique = true)
    private String email;


    private String lastName;


    private Gender gender;

    @Column(unique = true)
    private String phoneNumber;
    private String address;

    private Integer pinCode;

    //CascadeType.PERSIST has issues with many to many which makes us not use CascadeType.ALL
    //So Using  other Cascades other than CascadeType.PERSIST
//    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
//    @JoinTable(name = "USER_ROLES", joinColumns = {
//            @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
//            @JoinColumn(name = "ROLE_ID") })
//    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;


    public boolean doesRoleIsDoctor() {

        return doesUserHasRole("DOCTOR");


    }

    public boolean doesUserHasRole(String s) {
        return roles.stream()
                .filter(role -> {
                    return role.getName().equalsIgnoreCase(s);
                })
                .findFirst()
                .isPresent();
    }

    public boolean doesRoleIsUser() {
        return doesUserHasRole("USER");
    }

    public boolean doesRoleIsAuthority() {
        return doesUserHasRole("GOVERNMENT_AUTHORITY");
    }

    public boolean doesRoleIsTester() {
        return doesUserHasRole("TESTER");
    }

    public Integer getAge(){

        if(null != dateOfBirth)
            return LocalDate.now().getYear() - dateOfBirth.getYear();
        else
            return 0;
    }

	public AccountStatus getStatus() {
		return null;
	}

	public void setUserName(String userName2) {
	}

	public void setPassword(String encrypted) {
	}

	public void setRoles(Set<Role> rolesForUser) {
	}

	public void setUpdated(LocalDateTime now) {
	}

	public void setCreated(LocalDateTime now) {
	}

	public void setAddress(String address2) {
	}

	public void setFirstName(String firstName2) {
	}

	public void setEmail(String email2) {
	}

	public void setLastName(String lastName2) {
	}

	public void setPinCode(Integer pinCode2) {
	}

	public void setPhoneNumber(String phoneNumber2) {
	}

	public void setGender(Gender gender2) {
	}

	public void setDateOfBirth(LocalDate dateFromString) {
	}

	public void setStatus(AccountStatus status2) {
	}

	public String getUserName() {
		return null;
	}

	public String getPassword() {
		return null;
	}

	public Collection<SimpleGrantedAuthority> getRoles() {
		return null;
	}

}

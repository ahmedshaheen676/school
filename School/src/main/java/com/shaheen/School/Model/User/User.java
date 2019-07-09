/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Model.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shaheen.School.Security.Autority;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lts
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "parentActivity"})
@Transactional
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false, updatable = true, unique = true)
    @NotNull(message = "Must be a value")
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Column(name = "user_email", nullable = false, updatable = true, unique = true)
    private String email;

    @Transient
    private MultipartFile userImage;
    private String phone;
    private boolean enabel = true;

    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "users_roles",
//            joinColumns =
//                    @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = 
//                    @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public User() {
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the enabel
     */
    public boolean isEnabel() {
        return enabel;
    }

    /**
     * @param enabel the enabel to set
     */
    public void setEnabel(boolean enabel) {
        this.enabel = enabel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authoritys = new HashSet<>();
        getRoles().forEach(ur -> authoritys.add(new Autority(ur.getName())));
//        authoritys.add(new Autority(getRole().getName()));
        return authoritys;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;

    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabel();
    }

    /**
     * @return the userImage
     */
    public MultipartFile getUserImage() {
        return userImage;
    }

    /**
     * @param userImage the userImage to set
     */
    public void setUserImage(MultipartFile userImage) {
        this.userImage = userImage;
    }

    /**
     * @return the roles
     */
    public Set<Role> getRoles() {
        return roles;
    }


    /**
     * @param roles the roles to set
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
        @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + getUsername() + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", userImage=" + userImage + ", phone=" + phone + ", enabel=" + enabel + ", roles=" + roles + '}';
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }


}

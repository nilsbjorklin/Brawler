package com.academy.brawler.authorization.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

public class CustomUser implements UserDetails {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final BCryptPasswordEncoder B_CRYPT_PASSWORD_ENCODER = new BCryptPasswordEncoder();
    private String email;
    private String hashedPassword;
    private boolean enabled = true;
    private boolean locked = false;
    private Collection<GrantedAuthority> roles;


    public CustomUser() {
        roles = new ArrayList<>();
    }

    public static CustomUser createUserJson(final String jsonString) throws JsonProcessingException, Exceptions.JsonToUserException {
        JsonNode node = mapper.readTree(jsonString);
        CustomUser user = new CustomUser();
        String email = node.get("email")
                .textValue();
        user.setEmail(email);
        String hashedPassword = node.get("hash")
                .textValue();
        user.setHashedPassword(hashedPassword);

        JsonNode jsonRoles = node.get("roles");
        if (jsonRoles.isArray()) {
            int arrSize = jsonRoles.size();
            for (int i = 0; i < arrSize; i++) {
                user.addRole(Roles.getRoleFromAuthority(jsonRoles.get(i)
                        .textValue()));
            }
        } else {
            throw new Exceptions.JsonToUserException("No role found when parsing file.");
        }

        if (node.get("enabled").booleanValue()) {
            user.enable();
        } else {
            user.disable();
        }
        if (node.get("account_non_locked").booleanValue()) {
            user.unlock();
        } else {
            user.lock();
        }
        return user;
    }

    public boolean hasRole(final GrantedAuthority grantedAuthority) {
        for (final GrantedAuthority role : roles) {
            if (role.getAuthority().equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }
        return false;
    }

    public void addRole(final GrantedAuthority grantedAuthority) {
        if (grantedAuthority == null) {
            throw new NullPointerException("Cannot add role, role is null.");
        }
        roles.add(grantedAuthority);
    }

    public void removeRole(final GrantedAuthority grantedAuthority) {
        roles.remove(grantedAuthority);
    }

    public void setHashedPassword(final String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }


    public void setClearTextPassword(final String clearTextPassword) {
        this.hashedPassword = B_CRYPT_PASSWORD_ENCODER.encode(clearTextPassword);
    }


    public boolean login(final String clearTextPassword) {
        return B_CRYPT_PASSWORD_ENCODER.matches(clearTextPassword, getPassword());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void disable() {
        enabled = false;
    }

    public void enable() {
        enabled = true;
    }

    public void lock() {
        locked = true;
    }

    public void unlock() {
        locked = false;
    }

    public JsonNode asJson() throws Exceptions.UserToJsonException {
        ArrayNode rolesArray = mapper.createArrayNode();
        if (getAuthorities() == null || getAuthorities().size() == 0) {
            throw new NullPointerException("RoleList is null or empty");
        }

        for (final GrantedAuthority role : getAuthorities()) {
            if (role != null) {
                rolesArray.add(role.getAuthority());
            } else {
                throw new Exceptions.UserToJsonException("Role is null, user is invalid.");
            }
        }

        if (email == null || email.length() == 0) {
            throw new Exceptions.UserToJsonException("Email is null or empty");
        }
        if (getPassword() == null || getPassword().length() == 0) {
            throw new Exceptions.UserToJsonException("Password is null or empty");
        }
        return mapper.createObjectNode()
                .put("email", getEmail())
                .put("hash", getPassword())
                .put("enabled", isEnabled())
                .put("account_non_locked", isAccountNonLocked())
                .set("roles", rolesArray);
    }

    public String formatForFile() throws Exceptions.UserToJsonException {
        return asJson().toString() + "\n";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
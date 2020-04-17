package com.academy.brawler.authorization.users;

import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

public class Roles {

    public static CustomRole getRoleFromAuthority(final String authority) {
        for (final CustomRole value : CustomRole.values()) {
            if (value.getAuthority().equals(authority)){
                return value;
            }
        }
        return null;
    }

    public static CustomRole getRoleByName(final String roleName) {
        for (final CustomRole value : CustomRole.values()) {
            if (value.getName().equals(roleName)){
                return value;
            }
        }
        return null;
    }

    public static enum CustomRole implements GrantedAuthority {
        ADMINISTRATOR(0, "administrator"), MEDIUM_DUDE(1, "medium_dude"), USER(2, "user");
        private String name;
        private UUID uuid;
        private int id;

        CustomRole(final int roleId, final String nameValue) {
            this.id = roleId;
            this.name = nameValue;
            this.uuid = UUID.nameUUIDFromBytes(nameValue.getBytes());
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String getAuthority() {
            return uuid.toString();
        }
    }
}

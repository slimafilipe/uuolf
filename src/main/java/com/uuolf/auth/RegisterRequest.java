package com.uuolf.auth;

import com.uuolf.entity.Role;

public record RegisterRequest(String name, String email, String phone_number, String password, Role role) {
}

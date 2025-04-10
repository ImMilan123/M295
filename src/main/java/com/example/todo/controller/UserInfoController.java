package com.example.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "User profile info and token details")
public class UserInfoController {

    @GetMapping("/me")
    @Operation(summary = "Get the current user's info")
    @ApiResponse(responseCode = "200", description = "Returns username and roles")
    public Map<String, Object> getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
                "username", jwt.getClaimAsString("preferred_username"),
                "roles", jwt.getClaimAsMap("realm_access").get("roles")
        );
    }
}

package com.example.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin", description = "Endpoints restricted to admin users")
public class AdminController {

    @GetMapping("/secret")
    @Operation(summary = "Get a secret message only for admins")
    @ApiResponse(responseCode = "200", description = "Returns a secret message")
    public String adminSecret() {
        return "Only admins can see this!";
    }
}

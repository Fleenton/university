package com.example.university.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class ApiDocumentationController {

    @ApiOperation("Get documentation of application")
    @GetMapping("")
    public ResponseEntity<String> getDocumentationPage() {
        return ResponseEntity.ok(
                "This is University backend.<br/><a href=\"swagger-ui/#/\">REST API documentation</a>");
    }
}
package com.javaprojects.quizapp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.security.PrivateKey;

@Data
@RequiredArgsConstructor
public class Response {
    private int id;
    private String response;
}

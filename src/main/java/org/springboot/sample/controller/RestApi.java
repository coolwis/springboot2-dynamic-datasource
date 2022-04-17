package org.springboot.sample.controller;

import org.springboot.sample.datasource.DynamicDataSourceContextHolder;
import org.springboot.sample.entity.Student;
import org.springboot.sample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestApi {

    @Autowired
    private StudentService service;

    @GetMapping(value = "s_mapper")
    public ResponseEntity<List<Student>> getMapper() {
        DynamicDataSourceContextHolder.setDataSourceType("ds1");
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(service.getListOfMapper());
    }

    @GetMapping(value = "student")
    public ResponseEntity<List<Student>> getEmployees() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(service.getList());
    }
}

package org.springboot.sample.controller;

import org.springboot.sample.datasource.DynamicDataSourceContextHolder;
import org.springboot.sample.entity.Student;
import org.springboot.sample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestApi {

    @Autowired
    private StudentService service;

    @GetMapping(value = "s1")
    public ResponseEntity<List<Map<String,Object>>> getSessionTemplate() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(service.getListOfSessionTemplate());
    }

    @PostMapping(value = "ins")
    public int insertTest(@RequestBody HashMap params) {
           int i=  service.insertTest(params);
           return i;
    }

    @GetMapping(value = "s_mapper")
    public ResponseEntity<List<Student>> getMapper() {
        //DynamicDataSourceContextHolder.setDataSourceType("ds1");
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(service.getListOfMapper());
    }

}

package org.springboot.sample.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class PostConstrutorExample {

    private static final Logger log= LoggerFactory.getLogger(PostConstrutorExample.class);

    @Autowired
    private Environment environment;

    @PostConstruct //의존성 빈들이 모두 생성된 이후 호출
    public void init() {
        log.info("#######################  environment.toString()") ;
        log.info( environment.toString());
    }
}
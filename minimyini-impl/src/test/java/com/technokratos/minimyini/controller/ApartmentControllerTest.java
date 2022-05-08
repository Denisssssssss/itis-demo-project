package com.technokratos.minimyini.controller;

import com.technokratos.minimyini.security.TokenProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApartmentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TokenProvider tokenProvider;

    @Test
    public void throws_exception_on_illegal_modification() throws Exception {
        modify(HttpStatus.FORBIDDEN, "2");
    }

    @Test
    @Sql(scripts = "classpath:/db/sql/data.sql")
    public void allows_to_modify_to_owner() {
        modify(HttpStatus.OK, "1");
    }

    private void modify(HttpStatus httpStatus, String apartmentId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", tokenProvider.generate(1L));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        int status = restTemplate.exchange("/apartments/" + apartmentId, HttpMethod.PUT, entity, void.class)
                .getStatusCodeValue();
        Assertions.assertEquals(httpStatus.value(), status);
    }
}

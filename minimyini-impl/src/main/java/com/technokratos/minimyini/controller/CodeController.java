package com.technokratos.minimyini.controller;

import com.technokratos.minimyini.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    // псевдо использование кода для замка
    @GetMapping(value = "/codes/{apartment-id}/{code}")
    public void useCode(@PathVariable("apartment-id") Long id,
                        @PathVariable("code") String code) {
        codeService.useCode(code, id);
    }
}

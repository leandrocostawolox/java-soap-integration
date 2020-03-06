package com.leandrocosta.soapintegration.controllers;

import com.leandrocosta.soapintegration.services.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/calculator")
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    @GetMapping("/addition")
    public ResponseEntity getAddition(@RequestParam final Integer firstOperand,
                                      @RequestParam  final Integer secondOperand) {
        return ResponseEntity.ok(calculatorService.getAddResult(firstOperand, secondOperand));
    }

}

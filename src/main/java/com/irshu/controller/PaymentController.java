package com.irshu.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping(path = "/pay")
    @CircuitBreaker(name = "doDummyPayment", fallbackMethod = "doDummyPayment")
    public String doPayment(){
        System.out.println("FROM ACTUAL SERVICE");
        if(new Random().nextInt(15) < 10){
            throw new RuntimeException("DUMMY EXCEPTION");
        }
        return "DONE!";
    }

    public String doDummyPayment(){
        return "PLEASE TRY LATER!!!";
    }
}

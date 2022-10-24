package org.study.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.study.kafka.producer.BaseKafkaProducer;

@RestController
@RequestMapping("/kafka")
public class DemoController {
    @Autowired
    private BaseKafkaProducer baseKafkaProducer;

    @GetMapping("/msg/send")
    public String send(@RequestParam(value = "id") Integer id) {
        baseKafkaProducer.send("demo", "123", id.toString());
        return "success";
    }
}

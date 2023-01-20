package com.example.courseworkthree.controller;

import com.example.courseworkthree.dto.SockRequest;
import com.example.courseworkthree.model.Color;
import com.example.courseworkthree.model.Size;
import com.example.courseworkthree.service.SockService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sock")
public class SockController {
    private final SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }


    @PostMapping
    public void addSock(@RequestBody SockRequest sockRequest) {
        sockService.addSock(sockRequest);
    }

    @PutMapping
    public void issueSocks(@RequestBody SockRequest sockRequest) {
        sockService.issueSock(sockRequest);
    }

    @GetMapping
    public int getSockCount(@RequestParam(required = false, name = "color") Color color,
                            @RequestParam(required = false, name = "size") Size size,
                            @RequestParam(required = false, name = "cottonMin") Integer cottonMin,
                            @RequestParam(required = false, name = "cottonMax") Integer cottonMax)
    {
        return sockService.getSockQuantity(color, size, cottonMin, cottonMax);
    }

    @DeleteMapping
    public void deleteSocks(@RequestBody SockRequest sockRequest) {
        sockService.removeSock(sockRequest);
    }
}

package com.example.courseworkthree.service;

import com.example.courseworkthree.dto.SockRequest;
import com.example.courseworkthree.exception.InsufficientSockQuantityException;
import com.example.courseworkthree.exception.InvalidSockRequestException;
import com.example.courseworkthree.model.Color;
import com.example.courseworkthree.model.Size;
import com.example.courseworkthree.model.Sock;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class SockService {
    private final Map<Sock, Integer> socks = new HashMap<>();

    public void addSock(SockRequest sockRequest) {
        validateRequest(sockRequest);
        Sock sock = mapToSock(sockRequest);
        if (socks.containsKey(sock)) {
            socks.put(sock, socks.get(sock) + sockRequest.getQuantity());
        } else {
            socks.put(sock, sockRequest.getQuantity());
        }
    }

    public int getSockQuantity(Color color, Size size, Integer cottonMin, Integer cottonMax) {
        int total = 0;
        for (Map.Entry<Sock, Integer> entry : socks.entrySet()) {
            if (color != null && !entry.getKey().getColor().equals(color)) {
                continue;
            }
            if (size != null && !entry.getKey().getSize().equals(size)) {
                continue;
            }
            if (cottonMin != null && entry.getKey().getCottonPercent() < cottonMin) {
                continue;
            }
            if (cottonMax != null && entry.getKey().getCottonPercent() > cottonMax) {
                continue;
            }
            total += entry.getValue();
        }
        return total;
    }

    public void decreaseSockQuantity(SockRequest sockRequest) {
        validateRequest(sockRequest);
        Sock sock = mapToSock(sockRequest);
        int sockQuantity = socks.getOrDefault(sock, 0);
        if (sockQuantity >= sockRequest.getQuantity()) {
            socks.put(sock, sockQuantity - sockRequest.getQuantity());
        } else throw new InsufficientSockQuantityException("Нет носков");
    }
    public void issueSock(SockRequest sockRequest) {

        decreaseSockQuantity(sockRequest);
    }
    public void removeSock(SockRequest sockRequest) {
        decreaseSockQuantity(sockRequest);
    }

    private void validateRequest(SockRequest sockRequest) {
        if (sockRequest.getColor() == null || sockRequest.getSize() == null) {
            throw new InvalidSockRequestException(" Все поля должны быть заполнены");
        }
        if (sockRequest.getQuantity() < 0) {
            throw new InvalidSockRequestException("Количество должно быть больше 0 ");
        }
        if (sockRequest.getCottonPercent() <= 0 || sockRequest.getCottonPercent() > 100) {
            throw new InvalidSockRequestException("Процент хлопка должно быть в пределах 0...100");
        }
    }

    private Sock mapToSock(SockRequest sockRequest) {
        return new Sock(sockRequest.getColor(), sockRequest.getSize(), sockRequest.getCottonPercent());
    }
}



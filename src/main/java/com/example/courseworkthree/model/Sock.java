package com.example.courseworkthree.model;

import java.util.Objects;

public class Sock {
    private final Color color;
    private final Size size;
    private final int cottonPercent;

    public Sock(Color color, Size size, int cottonPercent) {
        this.color = color;
        this.size = size;
        this.cottonPercent = cottonPercent;
    }

    public Color getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }

    public int getCottonPercent() {
        return cottonPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sock sock = (Sock) o;
        return cottonPercent == sock.cottonPercent && color == sock.color && size == sock.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size, cottonPercent);
    }
}

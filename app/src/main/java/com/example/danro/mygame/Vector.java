package com.example.danro.mygame;


public class Vector {

    protected int x, y;

    public Vector(Vector v) {
        this.x = v.x;
        this.y = v.y;
    }

    Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Vector() {
        this.x = 0;
        this.y = 0;
    }

    public static void main(String argS[]) {
        // System.out.println(v);

    }

    public void print() {
        System.out.println("(" + x + ", " + y + ")");
    }

    public String toString() {
        return "(" + x + "; " + y + ")";
    }

    public void sum(Vector v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void sub(Vector v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void mul(float k) {
        x *= k;
        y *= k;
    }

    void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


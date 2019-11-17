package com.groupeonepoint;

public class Main {
    public static void main (String... args){
        Operation operation = new Operation();
        System.out.println(operation.compute(args[0]));
    }
}

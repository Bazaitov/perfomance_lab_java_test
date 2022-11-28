package org.example;
public class Main {
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        int i = 1;
        System.out.println();
        for (; ; ) {
            System.out.print(i);
            i = 1 + (i + m - 2) % n;
            if (i == 1) {
                break;
            }
        }
        System.out.println();
    }
}
package com.utils;

public class RandomPassword {
    public String genRandomPassword() {

        StringBuilder s = new StringBuilder(12);
        s.append("Pw0");
        for (int i = 1; i <= 9; i++) {
            int num = (int) (Math.random() * 3);
            if (num == 0) {
                s.append((char) ('A' + (int) (Math.random() * 26)));
            } else if (num == 1) {
                s.append((char) ('a' + (int) (Math.random() * 26)));
            } else {
                s.append((0 + (int) (Math.random() * 10)));
            }

        }
        return s.toString();
    }

}

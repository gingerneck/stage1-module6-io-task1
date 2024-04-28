package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        var res = new Profile();
        try {
            try (var reader = new java.io.FileReader(file);
                 var bufferReader = new BufferedReader(reader)) {
                String line;
                while ((line = bufferReader.readLine()) != null) {
                    setData(line, res);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    private void setData(String data, Profile profile) {
        var key = data.substring(0, data.indexOf(":")).toLowerCase();
        var value = data.substring(data.indexOf(":") + 1).trim();
        switch (key) {
            case ("name"): {
                profile.setName(value);
                break;
            }
            case ("age"): {
                profile.setAge(getInteger(value));
            }
            case ("email"): {
                profile.setEmail(value);
            }
            case ("phone"): {
                profile.setPhone((long) getInteger(value));
            }
        }
    }

    private int getInteger(String value) {
        char[] chars = value.toCharArray();
        boolean isNegative = (chars[0] == '-');
        if (isNegative) {
            chars[0] = '0';
        }

        int multiplier = 1;
        int total = 0;

        for (int i = chars.length - 1; i >= 0; i--) {
            total = total + ((chars[i] - '0') * multiplier);
            multiplier = multiplier * 10;
        }

        if (isNegative) {
            total = total * -1;
        }
        return total;
    }
}

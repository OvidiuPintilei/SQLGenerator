package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File file = new File("src\\data.txt");
        FileWriter errorFile = null;
        try {
            errorFile = new FileWriter("errors.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter logs = null;
        try {
            logs = new FileWriter("logs.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String currentLine;
        String[] data;
        StringBuilder stringBuilder;

        while (sc.hasNextLine()){
            currentLine = sc.nextLine();
            data = currentLine.split("[,\\-:?;.+*]");
            if(data.length != 2){
                try {
                    errorFile.write("Ce e aia: " + currentLine + "?\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                continue;
            }

            stringBuilder = new StringBuilder("INSERT INTO items VALUES ('");
            stringBuilder.append(data[0]);
            stringBuilder.append("','");
            stringBuilder.append(data[1]);
            stringBuilder.append("');\n");

            try {
                logs.write(stringBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            errorFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            logs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

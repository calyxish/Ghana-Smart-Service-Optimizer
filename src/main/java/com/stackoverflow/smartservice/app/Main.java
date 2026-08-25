package com.stackoverflow.smartservice.app;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleMenu menu = new ConsoleMenu(scanner);
        menu.run();
        scanner.close();
    }
}

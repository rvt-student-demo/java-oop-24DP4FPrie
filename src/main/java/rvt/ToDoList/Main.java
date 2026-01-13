package rvt.ToDoList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Todo todo = new Todo();
        Scanner scanner = new Scanner(System.in);
        Todo.UserInterface ui = new Todo.UserInterface(todo, scanner);
        ui.start();
    }
}

package rvt.ToDoList;

import java.util.ArrayList;
import java.util.Scanner;

public class Todo {
    private ArrayList<String> tasks;

    public Todo() {
        this.tasks = new ArrayList<>();
    }

    public void add(String task) {
        tasks.add(task);
    }

    public void print() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i));
        }
    }

    public void remove(int number) {
        tasks.remove(number - 1);
    }

    public static class UserInterface {
        private Todo todo;
        private Scanner scanner;

        public UserInterface(Todo todo, Scanner scanner) {
            this.todo = todo;
            this.scanner = scanner;
        }

        public void start() {
            while (true) {
                System.out.print("Command: ");
                String command = scanner.nextLine();

                if (command.equals("stop")) {
                    break;
                }

                if (command.equals("add")) {
                    System.out.print("To add: ");
                    String task = scanner.nextLine();
                    todo.add(task);
                } else if (command.equals("list")) {
                    todo.print();
                } else if (command.equals("remove")) {
                    System.out.print("Which one is removed? ");
                    int number = Integer.valueOf(scanner.nextLine());
                    todo.remove(number);
                }
            }
        }
    }
}

package rvt.ToDoList;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Todo {

    private ArrayList<String> tasks;
    private final String filePath = "data/todo.csv";

    public Todo() {
        this.tasks = new ArrayList<>();
        loadFromFile();
    }

    // ===============================
    // LOAD CSV -> ARRAYLIST
    // ===============================
    private void loadFromFile() {
    try {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        br.readLine();

        while ((line = br.readLine()) != null) {

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");

            if (parts.length < 2) {
                continue;
            }

            tasks.add(parts[1]);
        }

        br.close();
    } catch (IOException e) {
    }
}


    // ===============================
    // GET LAST ID FROM ARRAYLIST
    // ===============================
    private int getLastId() {
        return tasks.size();
    }

    // ===============================
    // SAVE ARRAYLIST -> CSV
    // ===============================
    private boolean updateFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("id,task\n");

            for (int i = 0; i < tasks.size(); i++) {
                fw.write((i + 1) + "," + tasks.get(i) + "\n");
            }

            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // ===============================
    // ADD TASK
    // ===============================
    public void add(String task) {
        tasks.add(task);
        updateFile();
    }

    // ===============================
    // PRINT TASKS
    // ===============================
    public void print() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i));
        }
    }

    // ===============================
    // REMOVE TASK
    // ===============================
    public void remove(int id) {
        if (id > 0 && id <= tasks.size()) {
            tasks.remove(id - 1);
            updateFile();
        }
    }

    // ===============================
    // USER INTERFACE (UNCHANGED)
    // ===============================
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

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

    private int getLastId() {
        return tasks.size();
    }

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

    public void add(String task) {
        tasks.add(task);
        updateFile();
    }
    public void print() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i));
        }
    }

    public void remove(int id) {
        if (id > 0 && id <= tasks.size()) {
            tasks.remove(id - 1);
            updateFile();
        }
    }


    public java.util.List<String> getTasks() {
        return new java.util.ArrayList<>(tasks);
    }

    public java.util.List<String> findAll() {
        return getTasks();
    }
}

package rvt.ToDoList;

import javax.swing.*;
import java.awt.*;

public class ToDoSwingUI extends JFrame {

    private Todo todo;

    private DefaultListModel<String> listModel;
    private JList<String> taskList;

    private JTextField taskField;

    public ToDoSwingUI() {

        todo = new Todo();

        setTitle("ToDo List");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // TOP PANEL
        JPanel topPanel = new JPanel();

        taskField = new JTextField(20);

        JButton addButton = new JButton("Add");

        topPanel.add(taskField);
        topPanel.add(addButton);

        panel.add(topPanel, BorderLayout.NORTH);

        // CENTER LIST
        listModel = new DefaultListModel<>();

        for (String task : todo.getTasks()) {
            listModel.addElement(task);
        }

        taskList = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(taskList);

        panel.add(scrollPane, BorderLayout.CENTER);

        // BOTTOM PANEL
        JPanel bottomPanel = new JPanel();

        JButton removeButton = new JButton("Remove");

        bottomPanel.add(removeButton);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        // ADD BUTTON ACTION
        addButton.addActionListener(e -> {

            String task = taskField.getText().trim();

            if (!task.isEmpty()) {

                todo.add(task);

                listModel.addElement(task);

                taskField.setText("");
            }
        });

        // REMOVE BUTTON ACTION
        removeButton.addActionListener(e -> {

            int selectedIndex = taskList.getSelectedIndex();

            if (selectedIndex != -1) {

                todo.remove(selectedIndex + 1);

                listModel.remove(selectedIndex);
            }
        });

        add(panel);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new ToDoSwingUI();
        });
    }
}
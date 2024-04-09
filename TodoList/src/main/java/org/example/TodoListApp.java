package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TodoListApp {
    private JFrame frame;
    private JTextField taskField;
    private JTable table;
    private DefaultTableModel model;

    public TodoListApp() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Todo List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel();
        JLabel taskLabel = new JLabel("Task:");
        taskField = new JTextField(20);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        inputPanel.add(taskLabel);
        inputPanel.add(taskField);
        inputPanel.add(addButton);

        panel.add(inputPanel, BorderLayout.NORTH);

        model = new DefaultTableModel();
        model.addColumn("Task");
        model.addColumn("Status");

        table = new JTable(model);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Detect single click
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        toggleTaskStatus(selectedRow);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editTask();
            }
        });
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            model.addRow(new Object[]{task, "Pending"});
            taskField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a task", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editTask() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String task = (String) model.getValueAt(selectedRow, 0);
            String newTask = JOptionPane.showInputDialog(frame, "Edit Task:", task);
            if (newTask != null && !newTask.trim().isEmpty()) {
                model.setValueAt(newTask, selectedRow, 0);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a task to edit", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteTask() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this task?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                model.removeRow(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a task to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void toggleTaskStatus(int row) {
        String currentStatus = (String) model.getValueAt(row, 1);
        if (currentStatus.equals("Pending")) {
            model.setValueAt("Completed", row, 1);
        } else {
            model.setValueAt("Pending", row, 1);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TodoListApp();
            }
        });
    }
}

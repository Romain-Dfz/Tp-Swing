package org.example;

import javax.swing.*;
import org.jdesktop.swingx.JXDatePicker;
import java.awt.*;

public class SwingExample extends JFrame {
    private JPanel imageDisplayPanel;

    public SwingExample() {
        setTitle("Formulaire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Augmentation de la hauteur pour ajouter une box "center"

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel northPanel = new JPanel(new GridLayout(1, 3));

        JPanel employeePanel = new JPanel(new GridLayout(7, 2));
        employeePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Ajout de marges
        employeePanel.add(new JLabel("Employé"));
        employeePanel.add(new JTextField());
        employeePanel.add(new JLabel("Nom"));
        employeePanel.add(new JTextField());
        employeePanel.add(new JLabel("Genre"));
        JPanel genderPanel = new JPanel(new GridLayout(1, 2));
        genderPanel.add(new JRadioButton("Homme"));
        genderPanel.add(new JRadioButton("Femme"));
        employeePanel.add(genderPanel);
        employeePanel.add(new JLabel("Âge"));
        employeePanel.add(new JTextField());
        employeePanel.add(new JLabel("Contact"));
        employeePanel.add(new JTextField());
        employeePanel.add(new JLabel("Qualification"));
        employeePanel.add(new JComboBox<>(new String[]{"Manager", "Employé"}));
        employeePanel.add(new JLabel("Date de début"));

        JXDatePicker datePicker = new JXDatePicker();
        employeePanel.add(datePicker);

        JPanel addressAndUploadPanel = new JPanel(new BorderLayout());
        addressAndUploadPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Ajout de marges
        JPanel addressPanel = new JPanel(new GridLayout(1, 1));
        addressPanel.setBorder(BorderFactory.createTitledBorder("Adresse"));
        addressPanel.add(new JTextField());
        addressAndUploadPanel.add(addressPanel, BorderLayout.CENTER);
        JButton uploadImageButton = new JButton("Upload Image");
        addressAndUploadPanel.add(uploadImageButton, BorderLayout.SOUTH);

        imageDisplayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            }
        };
        imageDisplayPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Ajout de marges

        northPanel.add(employeePanel);
        northPanel.add(addressAndUploadPanel);
        northPanel.add(imageDisplayPanel);

        mainPanel.add(northPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());

        JLabel searchLabel = new JLabel("Recherche:");
        JTextField searchField = new JTextField(20); // Agrandissement de la zone de texte
        JButton newButton = new JButton("New");
        JButton saveButton = new JButton("Save");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");
        JButton printButton = new JButton("Print");

        JPanel searchButtonPanel = new JPanel();
        searchButtonPanel.add(searchLabel);
        searchButtonPanel.add(searchField);
        searchButtonPanel.add(newButton);
        searchButtonPanel.add(saveButton);
        searchButtonPanel.add(updateButton);
        searchButtonPanel.add(deleteButton);
        searchButtonPanel.add(clearButton);
        searchButtonPanel.add(printButton);

        centerPanel.add(searchButtonPanel, BorderLayout.NORTH);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingExample::new);
    }
}

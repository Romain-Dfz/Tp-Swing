package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormulaireAjoutSwing {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);

            JPanel container = new JPanel(new BorderLayout());

            JPanel titrePanel = new JPanel();
            JLabel titreLabel = new JLabel("Formulaire d'ajout");
            titrePanel.add(titreLabel);
            titrePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JPanel formulairePanel = new JPanel(new GridLayout(4, 2, 10, 10));
            formulairePanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder("Formulaire"),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));

            JLabel nomLabel = new JLabel("Nom:");
            JTextField nomField = new JTextField();
            JLabel emailLabel = new JLabel("Email:");
            JTextField emailField = new JTextField();
            JLabel genreLabel = new JLabel("Genre:");
            JRadioButton maleButton = new JRadioButton("Homme");
            JRadioButton femaleButton = new JRadioButton("Femme");
            ButtonGroup genreGroup = new ButtonGroup();
            genreGroup.add(maleButton);
            genreGroup.add(femaleButton);
            JButton addButton = new JButton("Ajouter");

            JTextArea dataTextArea = new JTextArea(10, 10);
            dataTextArea.setEditable(false);

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nom = nomField.getText();
                    String email = emailField.getText();
                    String genre = maleButton.isSelected() ? "Homme" : "Femme";

                    String newData = "Nom: " + nom + ", Email: " + email + ", Genre: " + genre + "\n";
                    dataTextArea.append(newData);
                    JOptionPane.showMessageDialog(frame, "Données ajoutées avec succès", "Nouvelle Entrée", JOptionPane.INFORMATION_MESSAGE);

                    nomField.setText("");
                    emailField.setText("");
                    genreGroup.clearSelection();
                }
            });

            formulairePanel.add(nomLabel);
            formulairePanel.add(nomField);
            formulairePanel.add(emailLabel);
            formulairePanel.add(emailField);
            formulairePanel.add(genreLabel);
            JPanel genrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            genrePanel.add(maleButton);
            genrePanel.add(femaleButton);
            formulairePanel.add(genrePanel);
            formulairePanel.add(addButton);

            container.add(titrePanel, BorderLayout.NORTH);
            container.add(formulairePanel, BorderLayout.CENTER);

            JScrollPane scrollPane = new JScrollPane(dataTextArea);
            container.add(scrollPane, BorderLayout.SOUTH);

            frame.getContentPane().add(container);
            frame.setVisible(true);
        });
    }
}

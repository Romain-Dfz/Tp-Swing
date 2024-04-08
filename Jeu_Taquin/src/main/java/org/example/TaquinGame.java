package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaquinGame extends JFrame {

    private final int ROWS = 4;
    private final int COLS = 4;
    private final int NUM_TILES = ROWS * COLS;
    private final int TILE_SIZE = 100;

    private List<JButton> tiles;
    private int emptyIndex;

    public TaquinGame() {
        setTitle("Taquin Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(ROWS, COLS));

        tiles = new ArrayList<>();

        getContentPane().setPreferredSize(new Dimension(COLS * TILE_SIZE, ROWS * TILE_SIZE));

        for (int i = 1; i < NUM_TILES; i++) {
            JButton tile = new JButton(String.valueOf(i));
            tile.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
            tile.addActionListener(e -> {
                JButton clickedTile = (JButton) e.getSource();
                int clickedIndex = tiles.indexOf(clickedTile);
                if (isAdjacentToEmpty(clickedIndex)) {
                    swapTiles(emptyIndex, clickedIndex);
                    emptyIndex = clickedIndex;
                    updateTileLabels();
                    checkForWin();
                }
            });
            tiles.add(tile);
            getContentPane().add(tile);
        }

        JButton emptyTile = new JButton("");
        emptyTile.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        tiles.add(emptyTile);
        getContentPane().add(emptyTile);

        shuffleTiles();
        initializeEmptyIndex();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void shuffleTiles() {
        Collections.shuffle(tiles.subList(0, NUM_TILES - 1));
        updateEmptyIndex();
        updateTileLabels();
    }

    private void initializeEmptyIndex() {
        emptyIndex = NUM_TILES - 1;
    }

    private void updateEmptyIndex() {
        emptyIndex = tiles.indexOf(tiles.get(NUM_TILES - 1));
    }

    private void checkForWin() {
        boolean win = true;
        for (int i = 0; i < NUM_TILES - 1; i++) {
            JButton tile = tiles.get(i);
            if (!tile.getText().equals(String.valueOf(i + 1))) {
                win = false;
                break;
            }
        }
        if (win) {
            JOptionPane.showMessageDialog(this, "Congratulations! You won!");
        }
    }

    private boolean isAdjacentToEmpty(int index) {
        int rowDifference = Math.abs(emptyIndex / COLS - index / COLS);
        int colDifference = Math.abs(emptyIndex % COLS - index % COLS);
        return (rowDifference == 1 && colDifference == 0) || (rowDifference == 0 && colDifference == 1);
    }

    private void swapTiles(int index1, int index2) {
        JButton temp = tiles.get(index1);
        tiles.set(index1, tiles.get(index2));
        tiles.set(index2, temp);
    }

    private void updateTileLabels() {
        getContentPane().removeAll();
        for (JButton tile : tiles) {
            getContentPane().add(tile);
        }
        revalidate();
        repaint();
    }

}

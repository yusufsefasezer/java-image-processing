package com.yusufsezer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AppUI extends JFrame {

    JLabel jLabel;

    public AppUI() {
        setTitle("Java Image Processing");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.setBorder(BorderFactory.createDashedBorder(Color.GRAY, 2f, 5f, 2f, false));
        jLabel = new JLabel("Please select a photo!");
        jLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setForeground(Color.GRAY);
        jLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanel.add(jLabel, BorderLayout.CENTER);
        jLabel.addMouseListener(new CustomMouseAdapter());
        add(jPanel, BorderLayout.CENTER);

        JPanel jPanelFilter = new JPanel();
        jPanelFilter.add(new JLabel("Filters: "));
        add(jPanelFilter, BorderLayout.SOUTH);
    }

    private static class CustomMouseAdapter extends MouseAdapter {

        public CustomMouseAdapter() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e);
        }
    }

}

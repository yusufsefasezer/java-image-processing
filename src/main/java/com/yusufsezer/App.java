package com.yusufsezer;

import com.yusufsezer.contract.IFilter;
import com.yusufsezer.util.ClassUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class App extends JFrame {

    File selectedFile = null;
    JLabel imageLabel;
    List<JButton> buttons = new ArrayList<>();

    public App() {
        setTitle("Java Image Processing");
        //setSize(600, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.setBorder(BorderFactory.createDashedBorder(Color.GRAY, 2f, 5f, 2f, false));
        imageLabel = new JLabel("Please select a photo!");
        imageLabel.setPreferredSize(new Dimension(600, 800));
        imageLabel.addMouseListener(new ImageMouseAdapter());
        imageLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setForeground(Color.GRAY);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanel.add(imageLabel, BorderLayout.CENTER);
        add(jPanel, BorderLayout.CENTER);

        JPanel jPanelFilter = new JPanel();
        jPanelFilter.add(new JLabel("Filters: "));

        List<Class<? extends IFilter>> filterClasses = ClassUtils.findClassesImplementing("com.yusufsezer.filter", IFilter.class);
        for (Class<? extends IFilter> filterClass : filterClasses) {
            try {
                var button = createButton(filterClass);
                buttons.add(button);
                jPanelFilter.add(button);
            } catch (IllegalAccessException | InstantiationException e) {
                System.err.println(e);
            }
        }
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(this::resetActionListener);
        resetButton.setEnabled(false);
        buttons.add(resetButton);
        jPanelFilter.add(resetButton);

        add(jPanelFilter, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
    }

    private JButton createButton(Class<? extends IFilter> filterClass)
            throws InstantiationException, IllegalAccessException {
        JButton filterButton = new JButton(filterClass.getSimpleName());
        IFilter filter = filterClass.newInstance();
        filterButton.addActionListener(new FilterButtonListener(filter));
        filterButton.setEnabled(false);
        return filterButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }

    private static Image iconToImage(Icon icon) {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon) icon).getImage();
        } else {
            int width = icon.getIconWidth();
            int height = icon.getIconHeight();
            Image image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.getGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }

    private void resetActionListener(ActionEvent e) {
        setImageLabel(selectedFile);
    }

    private class FilterButtonListener implements ActionListener {

        private final IFilter filter;

        public FilterButtonListener(IFilter filter) {
            this.filter = filter;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Image image = iconToImage(imageLabel.getIcon());
            Image newImage = filter.filter(image);
            imageLabel.setIcon(new ImageIcon(newImage));
        }
    }

    private class ImageMouseAdapter extends MouseAdapter {

        public ImageMouseAdapter() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JFileChooser imageFileChooser = new JFileChooser();

            File userHome = new File(System.getProperty("user.home"));
            if (null != userHome) {
                imageFileChooser.setCurrentDirectory(userHome);
            }

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", ImageIO.getReaderFormatNames());
            imageFileChooser.setFileFilter(filter);

            int returnVal = imageFileChooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                selectedFile = imageFileChooser.getSelectedFile();
                setImageLabel(selectedFile);
                buttons.forEach(b -> b.setEnabled(true));
            }
        }
    }

    public void setImageLabel(File file) {
        String filePath = file.getAbsolutePath();
        ImageIcon loadedImage = new ImageIcon(filePath);
        Image scaledImage = loadedImage.getImage()
                .getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        imageLabel.setText("");
    }

}

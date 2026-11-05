package com.popsilog.popsilogorderingsystem;

import com.popsilog.popsilogorderingsystem.ui.PopsilogOrderingSystemFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class PopsilogOrderingSystem {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            PopsilogOrderingSystemFrame app = new PopsilogOrderingSystemFrame();
            app.showLoginDialog();
        });
    }
}
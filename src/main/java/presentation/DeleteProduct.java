package presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;

public class DeleteProduct {
    private JPanel mainPanel;
    private JLabel deleteProductLabel;
    private JLabel icon;
    private JComboBox productsComboBox;
    private JButton deleteButton;
    private JLabel selectProductLabel;
    private JButton backButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JLabel getDeleteProductLabel() {
        return deleteProductLabel;
    }

    public void setDeleteProductLabel(JLabel deleteProductLabel) {
        this.deleteProductLabel = deleteProductLabel;
    }

    public JLabel getIcon() {
        return icon;
    }

    public void setIcon(JLabel icon) {
        this.icon = icon;
    }

    public JComboBox getProductsComboBox() {
        return productsComboBox;
    }

    public void setProductsComboBox(JComboBox productsComboBox) {
        this.productsComboBox = productsComboBox;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JLabel getSelectProductLabel() {
        return selectProductLabel;
    }

    public void setSelectProductLabel(JLabel selectProductLabel) {
        this.selectProductLabel = selectProductLabel;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(4, 3, new Insets(20, 20, 20, 20), -1, -1));
        mainPanel.setBackground(new Color(-4489102));
        Font mainPanelFont = this.$$$getFont$$$(null, Font.BOLD, 14, mainPanel.getFont());
        if (mainPanelFont != null) mainPanel.setFont(mainPanelFont);
        mainPanel.setForeground(new Color(-16766021));
        deleteProductLabel = new JLabel();
        Font deleteProductLabelFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 36, deleteProductLabel.getFont());
        if (deleteProductLabelFont != null) deleteProductLabel.setFont(deleteProductLabelFont);
        deleteProductLabel.setForeground(new Color(-16766021));
        deleteProductLabel.setText("Delete product");
        mainPanel.add(deleteProductLabel, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        icon = new JLabel();
        icon.setIcon(new ImageIcon(getClass().getResource("/delete-product.png")));
        icon.setText("");
        mainPanel.add(icon, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        productsComboBox = new JComboBox();
        mainPanel.add(productsComboBox, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteButton = new JButton();
        deleteButton.setBackground(new Color(-16766021));
        Font deleteButtonFont = this.$$$getFont$$$(null, Font.BOLD, 14, deleteButton.getFont());
        if (deleteButtonFont != null) deleteButton.setFont(deleteButtonFont);
        deleteButton.setForeground(new Color(-1));
        deleteButton.setText("Delete");
        mainPanel.add(deleteButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        selectProductLabel = new JLabel();
        Font selectProductLabelFont = this.$$$getFont$$$(null, Font.BOLD, 14, selectProductLabel.getFont());
        if (selectProductLabelFont != null) selectProductLabel.setFont(selectProductLabelFont);
        selectProductLabel.setForeground(new Color(-16766021));
        selectProductLabel.setText("Select product");
        mainPanel.add(selectProductLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        backButton = new JButton();
        backButton.setBackground(new Color(-16766021));
        Font backButtonFont = this.$$$getFont$$$(null, Font.BOLD, 14, backButton.getFont());
        if (backButtonFont != null) backButton.setFont(backButtonFont);
        backButton.setForeground(new Color(-1));
        backButton.setText("Back");
        mainPanel.add(backButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}

package components;

import javax.swing.*;

public class JMyNumberField extends JTextField {

    public JMyNumberField() {
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (!Character.isDigit(evt.getKeyChar())) {
                    evt.consume();
                }
            }
        });
    }

    public int getInteger() {
        int num = 0;
        if (!this.getText().isEmpty()) {
            num = Integer.parseInt(this.getText());
        }
        return num;
    }

    public void setInteger(int num) {
        this.setText(num + "");
    }

}

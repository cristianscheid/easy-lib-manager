/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Color;
import javax.swing.JFormattedTextField;

/**
 *
 * @author mouriac
 */
public class JMyCpfField extends JFormattedTextField {

    private boolean right;

    public JMyCpfField() {
        right = false;
        try {
            setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (!isRight()) {
                    Color cor = new Color(255, 255, 196);
                    setBackground(cor);
                    setToolTipText("O CPF não está correto");
                } else {
                    setBackground(Color.WHITE);
                    setToolTipText("");
                }
            }

        });

    }

    public boolean isRight() {
        String aux = this.getText();
        if (!aux.isEmpty()) {
            aux = aux.replaceAll("[^0-9]", "");
            right = Validacao.validarCPF(aux);
        } else {
            right = false;
        }
        return right;
    }

}

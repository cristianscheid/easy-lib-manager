package apresentacao;

public class TelaMenuPrincipalAdmin extends javax.swing.JFrame {

    public TelaMenuPrincipalAdmin() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jButtonSair = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuMovimentacoes = new javax.swing.JMenu();
        jMenuItemEmprestimo = new javax.swing.JMenuItem();
        jMenuItemDevolucao = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemCadastrarCliente = new javax.swing.JMenuItem();
        jMenuItemCadastrarLivro = new javax.swing.JMenuItem();
        jMenuItemCadastraUsuario = new javax.swing.JMenuItem();
        jMenuConsultas = new javax.swing.JMenu();
        jMenuItemClientes = new javax.swing.JMenuItem();
        jMenuItemEmprestimos = new javax.swing.JMenuItem();
        jMenuItemLivros = new javax.swing.JMenuItem();
        jMenuItemLogs = new javax.swing.JMenuItem();
        jMenuEditar = new javax.swing.JMenu();
        jMenuItemDefinicoes = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonSair.setText("Sair");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        jMenuMovimentacoes.setText("Movimentações");

        jMenuItemEmprestimo.setText("Empréstimo");
        jMenuItemEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEmprestimoActionPerformed(evt);
            }
        });
        jMenuMovimentacoes.add(jMenuItemEmprestimo);

        jMenuItemDevolucao.setText("Devolução");
        jMenuItemDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDevolucaoActionPerformed(evt);
            }
        });
        jMenuMovimentacoes.add(jMenuItemDevolucao);

        jMenuBar1.add(jMenuMovimentacoes);

        jMenu3.setText("Cadastros");

        jMenuItemCadastrarCliente.setText("Cliente");
        jMenuItemCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastrarClienteActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemCadastrarCliente);

        jMenuItemCadastrarLivro.setText("Livro");
        jMenuItemCadastrarLivro.setToolTipText("");
        jMenuItemCadastrarLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastrarLivroActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemCadastrarLivro);

        jMenuItemCadastraUsuario.setText("Usuário");
        jMenuItemCadastraUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastraUsuarioActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemCadastraUsuario);

        jMenuBar1.add(jMenu3);

        jMenuConsultas.setText("Consultas");

        jMenuItemClientes.setText("Clientes");
        jMenuItemClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientesActionPerformed(evt);
            }
        });
        jMenuConsultas.add(jMenuItemClientes);

        jMenuItemEmprestimos.setText("Empréstimos");
        jMenuItemEmprestimos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEmprestimosActionPerformed(evt);
            }
        });
        jMenuConsultas.add(jMenuItemEmprestimos);

        jMenuItemLivros.setText("Livros");
        jMenuItemLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLivrosActionPerformed(evt);
            }
        });
        jMenuConsultas.add(jMenuItemLivros);

        jMenuItemLogs.setText("Logs");
        jMenuItemLogs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLogsActionPerformed(evt);
            }
        });
        jMenuConsultas.add(jMenuItemLogs);

        jMenuBar1.add(jMenuConsultas);

        jMenuEditar.setText("Editar");

        jMenuItemDefinicoes.setText("Definições");
        jMenuItemDefinicoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDefinicoesActionPerformed(evt);
            }
        });
        jMenuEditar.add(jMenuItemDefinicoes);

        jMenuBar1.add(jMenuEditar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(920, Short.MAX_VALUE)
                .addComponent(jButtonSair)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(546, Short.MAX_VALUE)
                .addComponent(jButtonSair)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemCadastraUsuarioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemCadastraUsuarioActionPerformed
    {//GEN-HEADEREND:event_jMenuItemCadastraUsuarioActionPerformed
        new TelaCadastroUsuario().setVisible(true);
    }//GEN-LAST:event_jMenuItemCadastraUsuarioActionPerformed

    private void jMenuItemCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemCadastrarClienteActionPerformed
    {//GEN-HEADEREND:event_jMenuItemCadastrarClienteActionPerformed
        new TelaCadastroCliente().setVisible(true);
    }//GEN-LAST:event_jMenuItemCadastrarClienteActionPerformed

    private void jMenuItemCadastrarLivroActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemCadastrarLivroActionPerformed
    {//GEN-HEADEREND:event_jMenuItemCadastrarLivroActionPerformed
        new TelaCadastroLivro().setVisible(true);
    }//GEN-LAST:event_jMenuItemCadastrarLivroActionPerformed

    private void jMenuItemEmprestimoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemEmprestimoActionPerformed
    {//GEN-HEADEREND:event_jMenuItemEmprestimoActionPerformed
        new TelaEmprestimo().setVisible(true);
    }//GEN-LAST:event_jMenuItemEmprestimoActionPerformed

    private void jMenuItemDevolucaoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemDevolucaoActionPerformed
    {//GEN-HEADEREND:event_jMenuItemDevolucaoActionPerformed
        new TelaDevolucao().setVisible(true);
    }//GEN-LAST:event_jMenuItemDevolucaoActionPerformed

    private void jMenuItemDefinicoesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemDefinicoesActionPerformed
    {//GEN-HEADEREND:event_jMenuItemDefinicoesActionPerformed
        new TelaDefinicoes().setVisible(true);
    }//GEN-LAST:event_jMenuItemDefinicoesActionPerformed

    private void jMenuItemLivrosActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemLivrosActionPerformed
    {//GEN-HEADEREND:event_jMenuItemLivrosActionPerformed
        new TelaConsultaLivro().setVisible(true);
    }//GEN-LAST:event_jMenuItemLivrosActionPerformed

    private void jMenuItemClientesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemClientesActionPerformed
    {//GEN-HEADEREND:event_jMenuItemClientesActionPerformed
        new TelaConsultaCliente().setVisible(true);
    }//GEN-LAST:event_jMenuItemClientesActionPerformed

    private void jMenuItemEmprestimosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEmprestimosActionPerformed
        new TelaConsultaEmprestimo().setVisible(true);
    }//GEN-LAST:event_jMenuItemEmprestimosActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jMenuItemLogsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLogsActionPerformed
        new TelaConsultaLogs().setVisible(true);
    }//GEN-LAST:event_jMenuItemLogsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipalAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipalAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipalAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipalAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMenuPrincipalAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuConsultas;
    private javax.swing.JMenu jMenuEditar;
    private javax.swing.JMenuItem jMenuItemCadastraUsuario;
    private javax.swing.JMenuItem jMenuItemCadastrarCliente;
    private javax.swing.JMenuItem jMenuItemCadastrarLivro;
    private javax.swing.JMenuItem jMenuItemClientes;
    private javax.swing.JMenuItem jMenuItemDefinicoes;
    private javax.swing.JMenuItem jMenuItemDevolucao;
    private javax.swing.JMenuItem jMenuItemEmprestimo;
    private javax.swing.JMenuItem jMenuItemEmprestimos;
    private javax.swing.JMenuItem jMenuItemLivros;
    private javax.swing.JMenuItem jMenuItemLogs;
    private javax.swing.JMenu jMenuMovimentacoes;
    // End of variables declaration//GEN-END:variables
}

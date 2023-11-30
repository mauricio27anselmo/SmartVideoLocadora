package br.com.locadora.main;

import br.com.locadora.domain.Usuario;
import br.com.locadora.service.UsuarioService;
import br.com.locadora.util.NegocioException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class UserApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        ResourceBundle messages = ResourceBundle.getBundle("i18n.messages");

        JFrame frame = new JFrame(messages.getString("br.com.locadora.cadastrousuario"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        JLabel nameLabel = new JLabel(messages.getString("br.com.locadora.nome"));
        JLabel passwordLabel = new JLabel(messages.getString("br.com.locadora.senha"));
        JLabel emailLabel = new JLabel(messages.getString("br.com.locadora.email"));

        JTextField nameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JTextField emailField = new JTextField(20);

        JButton saveButton = new JButton(messages.getString("br.com.locadora.salvar"));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText();
                try {
                    Usuario usuario = new Usuario();
                    usuario.setNome(name);
                    usuario.setSenha(password);
                    usuario.setEmail(email);
                    UsuarioService.getInstance().insert(usuario);
                    JOptionPane.showMessageDialog(frame, messages.getString("br.com.locadora.acao.salvarsucesso"));
                } catch (NegocioException ex) {
                    JOptionPane.showMessageDialog(frame, messages.getString(ex.getMessage()), messages.getString("br.com.locadora.erro"), JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameLabel)
                                        .addComponent(passwordLabel)
                                        .addComponent(emailLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameField)
                                        .addComponent(passwordField)
                                        .addComponent(emailField)))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(saveButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nameLabel)
                                .addComponent(nameField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(passwordLabel)
                                .addComponent(passwordField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(emailLabel)
                                .addComponent(emailField))
                        .addComponent(saveButton)
        );

        frame.setSize(300, 200);
        frame.setVisible(true);
    }

}

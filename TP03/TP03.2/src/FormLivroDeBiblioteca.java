import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormLivroDeBiblioteca extends JFrame {
    private JPanel panel1;
    private JTextField tituloTextField;
    private JTextField autorTextField;
    private JTextField editoraTextField;
    private JTextField anoEdTextField;
    private JTextField localTextField;
    private JTextField statusTextField;
    private JButton OKButton;
    private JButton limparButton;
    private JButton emprestarButton;
    private JButton devolverButton;
    private JButton mostrarButton;
    private JButton sairButton;
    private String message = "";

    private LivroDeBiblioteca livro = new LivroDeBiblioteca("Java", "Mauricio", "LTC", (short) 2005, "a1b1");

    public FormLivroDeBiblioteca(String titulo) {
        super(titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();

        tituloTextField.setText(livro.getTitulo());
        autorTextField.setText(livro.getAutor());
        editoraTextField.setText(livro.getEditora());
        anoEdTextField.setText(String.valueOf(livro.getAnoEdicao()));
        localTextField.setText(livro.getLocalizacao());
        statusTextField.setText(String.valueOf(livro.getEmprestado()));

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tituloTextField.setText("");
                autorTextField.setText("");
                editoraTextField.setText("");
                anoEdTextField.setText("");
                localTextField.setText("");
                statusTextField.setText("");
            }
        });

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                livro.setTitulo(tituloTextField.getText());
                livro.setAutor(autorTextField.getText());
                livro.setEditora(editoraTextField.getText());
                livro.setAnoEdicao(Short.parseShort(anoEdTextField.getText()));
                livro.setLocalizacao(localTextField.getText());
                livro.setEmprestado(Boolean.parseBoolean(statusTextField.getText()));


                limparButton.doClick();
            }
        });
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tituloTextField.setText(livro.getTitulo());
                autorTextField.setText(livro.getAutor());
                editoraTextField.setText(livro.getEditora());
                anoEdTextField.setText(String.valueOf(livro.getAnoEdicao()));
                localTextField.setText(livro.getLocalizacao());
                statusTextField.setText(String.valueOf(livro.getEmprestado()));
            }
        });
        emprestarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane optionPane = new JOptionPane();
                JDialog dialog = optionPane.createDialog(null, "Empréstimo");

                try {
                    if (Boolean.FALSE.equals(livro.getEmprestado())) {
                        message = "O livro foi emprestado com sucesso!";
                        livro.setEmprestado(!livro.getEmprestado());

                    } else {
                        message = "Indisponível para empréstimo.";
                    }
                } catch(NullPointerException exception) {
                    message = "Livro não encontrado.";
                } finally {
                    optionPane.setMessage(message);
                    dialog.setVisible(true);
                }
            }
        });
        devolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane optionPane = new JOptionPane();
                JDialog dialog = optionPane.createDialog(null, "Devolução");
                try {
                    if (Boolean.TRUE.equals(livro.getEmprestado())) {
                        message = "O livro foi devolvido!";
                        livro.setEmprestado(!livro.getEmprestado());

                    } else {
                        message = "O livro não encontra-se emprestado.";
                    }
                } catch(NullPointerException exception) {
                    message = "Livro não encontrado.";
                } finally {
                    optionPane.setMessage(message);
                    dialog.setVisible(true);
                }
            }
        });
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame f = new FormLivroDeBiblioteca("Semana 04 - Exercício 02");
        f.setVisible(true);
    }
}

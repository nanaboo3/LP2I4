import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Cadastro extends JFrame {
    private JPanel panel1;
    private JTextField nomeField;
    private JTextField idadeField;
    private JTextField enderecoField;
    private JButton okButton;
    private JButton limparButton;
    private JButton mostrarButton;
    private JButton sairButton;
    private ArrayList<Aluno> list;
    private String message = "";

    public Cadastro(String titulo) {
        super(titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.list = new ArrayList<Aluno>();
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                int idade = Integer.parseInt(idadeField.getText());
                String endereco = enderecoField.getText();
                Aluno aluno = new Aluno(nome, idade, endereco);
                list.add(aluno);
                limparButton.doClick();
                message = message+"Id: "+aluno.getUuid()+"    "+"Nome: "+aluno.getNome()+"\n";
            }
        });
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nomeField.setText(null);
                idadeField.setText(null);
                enderecoField.setText(null);
            }
        });
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane optionPane = new JOptionPane();
                optionPane.setMessage(message);
                optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = optionPane.createDialog(null, "Message");
                dialog.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame f = new Cadastro("TP02");
        f.setVisible(true);
    }
}

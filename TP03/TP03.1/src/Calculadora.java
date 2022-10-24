import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame {
    private JPanel panel1;
    private JTextField aTextField;
    private JButton limparButton;
    private JTextField bTextField;
    private JTextField somaTextField;
    private JTextField subtTextField;
    private JTextField multTextField;
    private JTextField diviTextField;
    private JButton calcularButton;
    private JButton sairButton;

    public Calculadora(String titulo) {
        super(titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();

        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double a = Double.parseDouble(aTextField.getText());
                double b = Double.parseDouble(bTextField.getText());
                somaTextField.setText(Double.toString(Calcular.somar(a,b)));
                subtTextField.setText(Double.toString(Calcular.subtrair(a,b)));
                multTextField.setText(Double.toString(Calcular.multiplicar(a,b)));
                diviTextField.setText(Double.toString(Calcular.dividir(a,b)));
            }
        });
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aTextField.setText("");
                bTextField.setText("");
                somaTextField.setText("");
                subtTextField.setText("");
                multTextField.setText("");
                diviTextField.setText("");
            }
        });
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}

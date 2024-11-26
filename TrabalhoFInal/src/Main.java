import javax.swing.SwingUtilities;
import view.LoginView;

public class Main {
    public static void main(String[] args) {
        // Garante que a interface gráfica será criada na thread de eventos do Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginView().setVisible(true); // Exibe a tela de login
            }
        });
    }
}

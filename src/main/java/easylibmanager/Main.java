package easylibmanager;

import apresentacao.TelaLogin;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import negocio.Usuario;
import persistencia.AutorDao;
import persistencia.CategoriaDao;
import persistencia.DefinicoesDao;
import persistencia.UsuarioDao;

public class Main {

    public static String ipAdress;
    public static Usuario usuario;

    public static void main(String[] args) throws SocketException {

        UsuarioDao usuarioDao = new UsuarioDao();
        DefinicoesDao definicoesDao = new DefinicoesDao();
        CategoriaDao categoriaDao = new CategoriaDao();

        if (usuarioDao.readAll().isEmpty()) {
            usuarioDao.createAdminEVisitante();
            definicoesDao.createDefinicoesIniciais();
            categoriaDao.createCategoriasIniciais();
        }

        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);

        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ipAdress = socket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException ex) {
            java.util.logging.Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

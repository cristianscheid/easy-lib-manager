package easylibmanager;

import apresentacao.TelaLogin;
import persistencia.CategoriaDao;
import persistencia.DefinicoesDao;
import persistencia.UsuarioDao;

public class Main {

    public static void main(String[] args) {

        UsuarioDao usuarioDao = new UsuarioDao();
        DefinicoesDao definicoesDao = new DefinicoesDao();
        CategoriaDao categoriaDao = new CategoriaDao();

        if (usuarioDao.readAll().isEmpty()) {
            usuarioDao.createAdmin();
            definicoesDao.createDefinicoesIniciais();
            categoriaDao.createCategoriasIniciais();
        }

        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
    }
}

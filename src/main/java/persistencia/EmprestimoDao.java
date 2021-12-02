package persistencia;

import easylibmanager.HibernateUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import negocio.Autor;
import negocio.Categoria;
import negocio.Cliente;
import negocio.Definicoes;
import negocio.Emprestimo;
import negocio.Livro;
import negocio.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmprestimoDao {

    private Log log;
    private LogDao logDao = new LogDao();

    public void create(Emprestimo emprestimo) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(emprestimo);
            transacao.commit();
            log = new Log("TRACE", "Emprestimo " + emprestimo.getId() + " created");
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
        } finally {
            logDao.create(log);
            sessao.close();
        }
    }

    public void update(Emprestimo emprestimo) {
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery("from Emprestimo where id = " + emprestimo.getId());
            List resultado = query.list();
            for (Object obj : resultado) {
                Emprestimo emprestimo_bd = (Emprestimo) obj;
                emprestimo_bd.setId(emprestimo.getId());
                emprestimo_bd.setDataEmprestimo(emprestimo.getDataEmprestimo());
                emprestimo_bd.setDataDevolucao(emprestimo.getDataDevolucao());
                emprestimo_bd.setCliente(emprestimo.getCliente());
                emprestimo_bd.setLivro(emprestimo.getLivro());
                sessao.update(emprestimo_bd);
                transacao.commit();
                log = new Log("TRACE", "Emprestimo " + emprestimo.getId() + " updated");
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
        }
    }

    public ArrayList<Emprestimo> readAll() {
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Emprestimo");
            List resultado = query.list();
            for (Object obj : resultado) {
                Emprestimo emprestimo = (Emprestimo) obj;
                emprestimos.add(emprestimo);
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
        }
        return emprestimos;
    }

    public ArrayList<Emprestimo> readEmprestimosAtivo(ArrayList<Emprestimo> emprestimos) {
        ArrayList<Emprestimo> emprestimosAtivo = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolucao() == null) {
                emprestimosAtivo.add(emprestimo);
            }
        }
        return emprestimosAtivo;
    }

    public ArrayList<Emprestimo> readEmprestimosNaoAtivo(ArrayList<Emprestimo> emprestimos) {
        ArrayList<Emprestimo> emprestimosNaoAtivo = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolucao() != null) {
                emprestimosNaoAtivo.add(emprestimo);
            }
        }
        return emprestimosNaoAtivo;
    }

    public ArrayList<Emprestimo> readEmprestimosPeriodo(ArrayList<Emprestimo> emprestimos, Date dataInicial, Date dataFinal) {
        ArrayList<Emprestimo> emprestimosPeriodo = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
//            if (emprestimo.getDataEmprestimo().after(dataInicial) || emprestimo.getDataEmprestimo().before(dataFinal)
//                    || emprestimo.getDataEmprestimo().equals(dataInicial) || emprestimo.getDataEmprestimo().equals(dataFinal)) {
            if (emprestimo.getDataEmprestimo().compareTo(dataInicial) >= 0 && emprestimo.getDataEmprestimo().compareTo(dataFinal) <= 0) {
                emprestimosPeriodo.add(emprestimo);
            }
        }
        return emprestimosPeriodo;
    }

    public ArrayList<Emprestimo> readEmprestimosMultaAberto(ArrayList<Emprestimo> emprestimos) {
        ArrayList<Emprestimo> emprestimosMultaAberto = new ArrayList<>();
        Date dataAtual = new Date();
        DefinicoesDao definicoesDao = new DefinicoesDao();
        Definicoes definicoes = definicoesDao.read();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolucao() == null) {
                Date dataEmprestimo = emprestimo.getDataEmprestimo();
                int diasEmprestado = (int) ((dataAtual.getTime() - dataEmprestimo.getTime()) / (1000 * 60 * 60 * 24));
                if (diasEmprestado > definicoes.getPrazoEmprestimo()) {
                    emprestimosMultaAberto.add(emprestimo);
                }
            }
        }
        return emprestimosMultaAberto;
    }

    public ArrayList<Emprestimo> readEmprestimosSemMultaAberto(ArrayList<Emprestimo> emprestimos) {
        ArrayList<Emprestimo> emprestimosSemMultaAberto = new ArrayList<>();
        Date dataAtual = new Date();
        DefinicoesDao definicoesDao = new DefinicoesDao();
        Definicoes definicoes = definicoesDao.read();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolucao() != null) {
                emprestimosSemMultaAberto.add(emprestimo);
            } else {
                Date dataEmprestimo = emprestimo.getDataEmprestimo();
                int diasEmprestado = (int) ((dataAtual.getTime() - dataEmprestimo.getTime()) / (1000 * 60 * 60 * 24));
                if (diasEmprestado <= definicoes.getPrazoEmprestimo()) {
                    emprestimosSemMultaAberto.add(emprestimo);
                }
            }
        }
        return emprestimosSemMultaAberto;
    }

    public ArrayList<Cliente> readClientesEmprestimoAtivo(ArrayList<Cliente> clientes) {
        ArrayList<Cliente> clientesEmprestimoAtivo = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            for (Cliente cliente : clientes) {
                org.hibernate.Query query = sessao.createQuery("from Emprestimo where cliente_id = " + cliente.getId() + " and data_devolucao is null");
                List resultado = query.list();
                if (!resultado.isEmpty()) {
                    clientesEmprestimoAtivo.add(cliente);
                }
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
        }
        return clientesEmprestimoAtivo;
    }

    public ArrayList<Cliente> readClientesSemEmprestimoAtivo(ArrayList<Cliente> clientes) {
        ArrayList<Cliente> clientesSemEmprestimoAtivo = new ArrayList<>();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            for (Cliente cliente : clientes) {
                org.hibernate.Query query = sessao.createQuery("from Emprestimo where cliente_id = " + cliente.getId() + " and data_devolucao is null");
                List resultado = query.list();
                if (resultado.isEmpty()) {
                    clientesSemEmprestimoAtivo.add(cliente);
                }
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
        }
        return clientesSemEmprestimoAtivo;
    }

    public ArrayList<Cliente> readClientesMultaAberto(ArrayList<Cliente> clientes) {
        ArrayList<Cliente> clientesMultaAberto = new ArrayList<>();
        Emprestimo emprestimo = null;
        Date dataAtual = new Date();
        DefinicoesDao definicoesDao = new DefinicoesDao();
        Definicoes definicoes = definicoesDao.read();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            for (Cliente cliente : clientes) {
                org.hibernate.Query query = sessao.createQuery("from Emprestimo where cliente_id = " + cliente.getId() + " and data_devolucao is null");
                List resultado = query.list();
                if (!resultado.isEmpty()) {
                    for (Object obj : resultado) {
                        emprestimo = (Emprestimo) obj;
                        Date dataEmprestimo = emprestimo.getDataEmprestimo();
                        int diasEmprestado = (int) ((dataAtual.getTime() - dataEmprestimo.getTime()) / (1000 * 60 * 60 * 24));
                        if (diasEmprestado > definicoes.getPrazoEmprestimo()) {
                            clientesMultaAberto.add(cliente);
                            break;
                        }
                    }
                }
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
        }
        return clientesMultaAberto;
    }

    public ArrayList<Cliente> readClientesSemMultaAberto(ArrayList<Cliente> clientes) {
        ArrayList<Cliente> clientesSemMultaAberto = new ArrayList<>();
        Emprestimo emprestimo = null;
        Date dataAtual = new Date();
        DefinicoesDao definicoesDao = new DefinicoesDao();
        Definicoes definicoes = definicoesDao.read();
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            for (Cliente cliente : clientes) {
                boolean multaAberto = false;
                org.hibernate.Query query = sessao.createQuery("from Emprestimo where cliente_id = " + cliente.getId());
                List resultado = query.list();
                if (!resultado.isEmpty()) {
                    for (Object obj : resultado) {
                        emprestimo = (Emprestimo) obj;
                        if (emprestimo.getDataDevolucao() == null) {
                            Date dataEmprestimo = emprestimo.getDataEmprestimo();
                            int diasEmprestado = (int) ((dataAtual.getTime() - dataEmprestimo.getTime()) / (1000 * 60 * 60 * 24));
                            if (diasEmprestado > definicoes.getPrazoEmprestimo()) {
                                multaAberto = true;
                            }
                        }
                        if (multaAberto) {
                            break;
                        }
                    }
                }
                if (!multaAberto) {
                    clientesSemMultaAberto.add(cliente);
                }
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
        }
        return clientesSemMultaAberto;
    }

    public Emprestimo readLivroEmprestado(Livro livro) {
        Emprestimo emprestimo = null;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Emprestimo where data_devolucao is null and livro_id = " + livro.getId());
            List resultado = query.list();
            for (Object obj : resultado) {
                emprestimo = (Emprestimo) obj;
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
        }
        return emprestimo;
    }

    public Boolean livroPossuiRegistoEmprestimo(Livro livro) {
        boolean possuiRegistroEmprestimo = true;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Emprestimo where livro_id = " + livro.getId());
            List resultado = query.list();
            if (resultado.isEmpty()) {
                possuiRegistroEmprestimo = false;
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
        }
        return possuiRegistroEmprestimo;
    }

    public Boolean clientePossuiRegistoEmprestimo(Cliente cliente) {
        boolean possuiRegistroEmprestimo = true;
        try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = sessao.createQuery("from Emprestimo where cliente_id = " + cliente.getId());
            List resultado = query.list();
            if (resultado.isEmpty()) {
                possuiRegistroEmprestimo = false;
            }
        } catch (HibernateException hibEx) {
            log = new Log("ERROR", hibEx.getMessage());
            logDao.create(log);
        }
        return possuiRegistroEmprestimo;
    }

    public ArrayList<Livro> readLivrosPopulares() {
        ArrayList<Emprestimo> emprestimos = readAll();
        ArrayList<Integer> livrosId = new ArrayList<>();
        //Gerar lista com IDs dos livros que constam nos registros de empréstimo
        for (Emprestimo emprestimo : emprestimos) {
            livrosId.add(emprestimo.getLivro().getId());
        }
        //Remember LinkedHashMap maintains insertion order of elements
        Map<Integer, Integer> elementCountMap = new LinkedHashMap<>();
        //Check presence of each element in elementCountMap 
        for (int i = 0; i < livrosId.size(); i++) {
            if (elementCountMap.containsKey(livrosId.get(i))) {
                //If element is present in elementCountMap, increment its value by 1
                elementCountMap.put(livrosId.get(i), elementCountMap.get(livrosId.get(i)) + 1);
            } else {
                //If element is not present, insert this element with 1 as its value
                elementCountMap.put(livrosId.get(i), 1);
            }
        }
        //Create an ArrayList to hold sorted elements
        ArrayList<Integer> sortedElements = new ArrayList<>();
        //Java 8 code to sort elementCountMap by values in reverse order
        //and put keys into sortedElements list
        elementCountMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(entry -> {
                    for (int i = 1; i <= entry.getValue(); i++) {
                        sortedElements.add(entry.getKey());
                    }
                });
        //Printing sorted array elements in descending order of their frequency
        System.out.println("Input Array :" + livrosId);
        System.out.println("Sorted Array Elements In Descending Order Of their Frequency :");
        System.out.println(sortedElements);
        //Remove duplicates
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(sortedElements);
        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>(hashSet);
        System.out.println(listWithoutDuplicates);
        //Buscas 10 livros mais populares pelo ID
        LivroDao livroDao = new LivroDao();
        ArrayList<Livro> livrosPopulares = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            livrosPopulares.add(livroDao.read(listWithoutDuplicates.get(i)));
        }
        System.out.println(livrosPopulares);
        return livrosPopulares;
    }

    public ArrayList<Autor> readAutoresPopulares() {
        ArrayList<Emprestimo> emprestimos = readAll();
        ArrayList<Integer> autoresId = new ArrayList<>();
        //Gerar lista com IDs dos livros que constam nos registros de empréstimo
        for (Emprestimo emprestimo : emprestimos) {
            autoresId.add(emprestimo.getLivro().getAutor().getId());
        }
        //Remember LinkedHashMap maintains insertion order of elements
        Map<Integer, Integer> elementCountMap = new LinkedHashMap<>();
        //Check presence of each element in elementCountMap 
        for (int i = 0; i < autoresId.size(); i++) {
            if (elementCountMap.containsKey(autoresId.get(i))) {
                //If element is present in elementCountMap, increment its value by 1
                elementCountMap.put(autoresId.get(i), elementCountMap.get(autoresId.get(i)) + 1);
            } else {
                //If element is not present, insert this element with 1 as its value
                elementCountMap.put(autoresId.get(i), 1);
            }
        }
        //Create an ArrayList to hold sorted elements
        ArrayList<Integer> sortedElements = new ArrayList<>();
        //Java 8 code to sort elementCountMap by values in reverse order
        //and put keys into sortedElements list
        elementCountMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(entry -> {
                    for (int i = 1; i <= entry.getValue(); i++) {
                        sortedElements.add(entry.getKey());
                    }
                });
        //Printing sorted array elements in descending order of their frequency
        System.out.println("Input Array :" + autoresId);
        System.out.println("Sorted Array Elements In Descending Order Of their Frequency :");
        System.out.println(sortedElements);
        //Remove duplicates
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(sortedElements);
        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>(hashSet);
        System.out.println(listWithoutDuplicates);
        //Buscas 10 autores mais populares pelo ID
        AutorDao autorDao = new AutorDao();
        ArrayList<Autor> autoresPopulares = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            autoresPopulares.add(autorDao.read(listWithoutDuplicates.get(i)));
        }
        System.out.println(autoresPopulares);
        return autoresPopulares;
    }

    public ArrayList<Categoria> readCategoriasPopulares() {
        ArrayList<Emprestimo> emprestimos = readAll();
        ArrayList<Integer> categoriasId = new ArrayList<>();
        //Gerar lista com IDs dos livros que constam nos registros de empréstimo
        for (Emprestimo emprestimo : emprestimos) {
            categoriasId.add(emprestimo.getLivro().getCategoria().getId());
        }
        //Remember LinkedHashMap maintains insertion order of elements
        Map<Integer, Integer> elementCountMap = new LinkedHashMap<>();
        //Check presence of each element in elementCountMap 
        for (int i = 0; i < categoriasId.size(); i++) {
            if (elementCountMap.containsKey(categoriasId.get(i))) {
                //If element is present in elementCountMap, increment its value by 1
                elementCountMap.put(categoriasId.get(i), elementCountMap.get(categoriasId.get(i)) + 1);
            } else {
                //If element is not present, insert this element with 1 as its value
                elementCountMap.put(categoriasId.get(i), 1);
            }
        }
        //Create an ArrayList to hold sorted elements
        ArrayList<Integer> sortedElements = new ArrayList<>();
        //Java 8 code to sort elementCountMap by values in reverse order
        //and put keys into sortedElements list
        elementCountMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(entry -> {
                    for (int i = 1; i <= entry.getValue(); i++) {
                        sortedElements.add(entry.getKey());
                    }
                });
        //Printing sorted array elements in descending order of their frequency
        System.out.println("Input Array :" + categoriasId);
        System.out.println("Sorted Array Elements In Descending Order Of their Frequency :");
        System.out.println(sortedElements);
        //Remove duplicates
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(sortedElements);
        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>(hashSet);
        System.out.println(listWithoutDuplicates);
        //Buscas 10 categorias mais populares pelo ID
        CategoriaDao categoriaDao = new CategoriaDao();
        ArrayList<Categoria> categoriasPopulares = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            categoriasPopulares.add(categoriaDao.read(listWithoutDuplicates.get(i)));
        }
        System.out.println(categoriasPopulares);
        return categoriasPopulares;
    }
}

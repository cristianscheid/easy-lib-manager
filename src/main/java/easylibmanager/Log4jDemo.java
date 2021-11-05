package easylibmanager;

import negocio.Cliente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistencia.ClienteDao;

public class Log4jDemo {

    private static Logger logger = LogManager.getLogger(Log4jDemo.class);

    public static void main(String[] args) {

        System.out.println("\nStarting...\n");

        logger.trace("This is a trace message");
        logger.info("This is a information message");
        logger.warn("This is a warning message");
        logger.error("This is a error message");
        logger.fatal("This is a fatal message");

        try {
            Cliente cliente = null;
            ClienteDao clienteDao = new ClienteDao();
            clienteDao.delete(cliente);
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }

        System.out.println("\nFinished!\n");

    }

}

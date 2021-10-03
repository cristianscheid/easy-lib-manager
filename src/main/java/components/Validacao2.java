package components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Validacao2 {

    public static boolean validarIsbn(String isbn) {
        return !(isbn == null || (isbn.length() != 10 && isbn.length() != 13));
    }

    public static boolean validarAno(int ano) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
        String anoAtualString = dateTimeFormatter.format(LocalDate.now());
        int anoAtualInt = Integer.parseInt(anoAtualString);
        return !(ano < 1450 || ano > anoAtualInt);
    }
}

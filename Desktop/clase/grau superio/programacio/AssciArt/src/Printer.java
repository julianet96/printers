/**
 * Created by julià on 23/02/2017.
 */
public class Printer {
    private String alphabet;
    private String Espai;
    private int LlarcLletre;
    private int CanviaLletra;
    private int SebrePos;
    private int AlturaLletra;

    // Constructor: accepta un String amb la representació de tot l'alphabet
    // Hi haurà 27 lletres en total, separades per un space en blanc.
    Printer(String alphabet) {
        this.alphabet = alphabet;
        //calculam la altura de cada lletre
        this.AlturaLletra = alphabet.length() / 27 - 14;
        //calculam el llarc de cada lletre
        this.LlarcLletre = (alphabet.length() / 27) / this.AlturaLletra;
        //calculam cuan em de canviar la lletra
        this.CanviaLletra = alphabet.length() / AlturaLletra + 1;
        //cram els espais que hi ha entre paraula i paraula
        if (this.AlturaLletra == 5) {
            this.Espai = "    ";
        } else {
            this.Espai = "      ";
        }
        //volem sebre onesta la posicio
        this.SebrePos = CanviaLletra - Espai.length();
    }

    // Mètode render: accepta un String amb el text a representar i torna
    // també un String amb el text en forma d'ASCII Art.
    // Només es consideren les lletres majúscules de la A a la Z. Les
    // minúscules es passen a majúscules, i els altres caràctes es tradueixen
    // a «?» (manco l'space en blanc).
    public String render(String text) {
        //guardam la lletra o lletres que ens pasen
        char[] Lletres = text.toUpperCase().toCharArray();
        String Result = "";
        //Feim un bucle per fer la altura de la lletra
        for (int i = 0; i < this.AlturaLletra; i++) {
            //Feim un bucle per construir cada linea
            for (int j = 0; j < Lletres.length; j++) {
                //comprovam si hi ha espais entre paraules
                if (Lletres[j] == ' ') {
                    Result += this.Espai;
                    continue;
                }
                //calculam la pasicio on comensam a escriure
                int Pos = i * this.CanviaLletra;
                //comprovam si esta entre la A i la Z si nos posam caracter ?
                if (Lletres[j] < 'A' || Lletres[j] > 'Z') {
                    Pos += this.SebrePos;
                } else {
                    Pos += this.LlarcLletre * (Lletres[j] - 'A') + (Lletres[j] - 'A');
                }
                //comensam a escriure la lletra
                Result += this.alphabet.substring(Pos, Pos + this.LlarcLletre);
                //posam l/espai entre lletra i lletra
                if (Lletres.length > 1 && j < Lletres.length - 1) {
                    Result += " ";
                }
            }
            //comprovam si no es la darrera linea i si no ho es li posam un \n
            if (i < this.AlturaLletra - 1) {
                Result += "\n";
            }
        }
        System.out.println(Result);
        return Result;
    }
}

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
        this.AlturaLletra = alphabet.length() / 27 - 14;
        this.LlarcLletre = (alphabet.length() / 27) / this.AlturaLletra;
        this.CanviaLletra = alphabet.length() / AlturaLletra + 1;

        if (this.AlturaLletra == 5) {
            this.Espai = "    ";
        } else {
            this.Espai = "      ";
        }
        this.SebrePos = CanviaLletra - Espai.length();
    }

    // Mètode render: accepta un String amb el text a representar i torna
    // també un String amb el text en forma d'ASCII Art.
    // Només es consideren les lletres majúscules de la A a la Z. Les
    // minúscules es passen a majúscules, i els altres caràctes es tradueixen
    // a «?» (manco l'space en blanc).
    public String render(String text) {
        char[] Lletres = text.toUpperCase().toCharArray();
        String Result = "";
        for (int i = 0; i < this.AlturaLletra; i++) {
            for (int j = 0; j < Lletres.length; j++) {
                if (Lletres[j] == ' ') {
                    Result += this.Espai;
                    continue;
                }
                int Pos = i * this.CanviaLletra;
                if (Lletres[j] < 'A' || Lletres[j] > 'Z') {
                    Pos += this.SebrePos;
                } else {
                    Pos += this.LlarcLletre * (Lletres[j] - 'A') + (Lletres[j] - 'A');
                }

                Result += this.alphabet.substring(Pos, Pos + this.LlarcLletre);
                if (Lletres.length > 1 && j < Lletres.length - 1) {
                    Result += " ";
                }
            }


            if (i < this.AlturaLletra - 1) {
                Result += "\n";
            }
        }
        return Result;
    }
}

import sun.plugin.javascript.navig.Array;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by julià on 10/05/2017.
 */
public class main {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.println("Si nesesites ajuda escriu sa paraula "+Text.ANSI_YELLOW+"help"+Text.ANSI_RESET);
        Tar tar = null;
        while (true){

            System.out.println(Text.ANSI_RED+"Introdueix la comanda: "+Text.ANSI_RESET);
            String comand = s.nextLine();
            comand = comand.toUpperCase();

            switch (comand){
                case "HELP":
                    System.out.println(Text.ANSI_YELLOW+"load :"+Text.ANSI_RESET+Text.ANSI_CYAN+" Carrega els fitxers"+Text.ANSI_RESET);
                    System.out.println(Text.ANSI_YELLOW+"list :"+Text.ANSI_RESET +Text.ANSI_CYAN+" Llista els fitxers que hi ha en el tar"+Text.ANSI_RESET);
                    System.out.println(Text.ANSI_YELLOW+"extract :"+Text.ANSI_RESET+Text.ANSI_CYAN+" Extreu els fitxers del tar"+Text.ANSI_RESET);
                    System.out.println(Text.ANSI_YELLOW+"exit :"+Text.ANSI_RESET+Text.ANSI_CYAN+" Sorti de la aplicacio"+Text.ANSI_RESET);
                    break;
                case "LIST":
                    // imprimim la llista de noms del tar que ens pasen
                    System.out.println(Arrays.toString(tar.list()));
                    break;
                case "LOAD":
                    //carragam el fitxer
                    System.out.println(Text.ANSI_PURPLE+"Donem el nom del fitxer"+Text.ANSI_RESET);
                    String file = s.nextLine();
                    System.out.println(file);
                    // per carragarlo el que em de fer es crear el tar i cridar a expand
                     tar = new Tar(file);
                    tar.expand();
                    break;
                case "EXTRACT":
                    // extreim el fitxer
                    System.out.println(Text.ANSI_RED+"Donem el nom del arxiu que vols"+Text.ANSI_RESET);
                    comand = s.nextLine();
                    // cream un outputStream per que ens vagi creat el arxiu
                    FileOutputStream fo = new FileOutputStream("c:\\tmp\\"+comand);
                    // sercam el nom del arxiu que volem extreura
                    for (int i = 0; i < tar.list().length ; i++) {
                        if(comand.equals(tar.list()[i])){
                            // una vegada trobat feim un write dels byts que te el nom
                            fo.write(tar.list()[i].getBytes());
                        }
                    }
                    break;
                case "EXIT":
                    // acaba el bucle
                    System.out.println(Text.ANSI_BLUE+"Bay Bay"+Text.ANSI_RESET);
                    return;

            }

        }
    }
}
class Text {
    // colors per decorar el "terminal"
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
}

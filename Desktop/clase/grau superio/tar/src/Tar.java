import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by julià on 10/05/2017.
 */
public class Tar {
    private final String filename;
    private List<arxiu> arxiulist = new ArrayList<>();
    // Constructor
    public Tar(String filename) {
        this.filename = filename;
        File f = new File(this.filename);
        //comprovam si el fitxer existeix
        if(f.exists()&&filename.contains(".tar")){
            System.out.println("Fitxer carragat");
            //si no existeix miram si conte el .tar
            //cream una excepcio
        }else if(this.filename.contains(".tar")){
            try {
                throw new exceptionNoRut();
            }catch (exceptionNoRut e){

            }
            //miram si el fitxer que ens pasen no conte el .tar
            //cream una excepcio
        }else if (!this.filename.contains(".tar")) {
            try {
                throw new exceptionNoTar();
            } catch (exceptionNoTar e) {

            }
        }
    }
    // Torna un array amb la llista de fitxers que hi ha dins el TAR
    public String[] list() {
        String[] arxius = new String[arxiulist.size()];
        for (int i = 0; i < arxius.length ; i++) {
            arxius[i]=arxiulist.get(i).getNom();
        }
        return arxius;
    }
    // Torna un array de bytes amb el contingut del fitxer que té per nom
// igual a l'String «name» que passem per paràmetre
    public byte[] getBytes(String name) {
        // sercam a la llista que tenim el nom del arxiu
        for (int i = 0; i < arxiulist.size() ; i++) {
            // cuan el nom es el mateix el que feim es li retornam els byts del contingut
            if(name.equals(arxiulist.get(i).getNom())){
                return arxiulist.get(i).getContin();
            }
        }
        return null; }
    // Expandeix el fitxer TAR dins la memòria
    public void expand(){
        try{
        InputStream f = new FileInputStream(this.filename);
        String Nom="";
        int pes ;
        String oct = "";
        int sice ;
        //mentres que hi hagui arxius dintre del tar
        while ( (pes = f.read()) != -1){
            //Amb aquest for el que miram es el nom del arxiu
            for (int i = 0; i <100 ; i++) {
                if (pes > 0){
                    Nom += (char)pes;
                }

                pes = f.read();
            }
            // Comprovam si no te nom feim un break
            if (Nom.equals("")) break;
            // feim un skip de 24 per visualitzar el pes
            f.skip(24);
            // Aquest for el que mira es el pes del arxiu en octal
            for (int i = 0; i <10 ; i++) {

                oct +=(char)f.read();

            }
            // pasam de string a decimal
            sice = Integer.parseInt(oct, 8);
            // pasam 377 posicions per arribar al arxiu per comensar a lletgir dades
            f.skip(377);
            ByteArrayOutputStream contenidor = new ByteArrayOutputStream();
            // lletgim les dades i les guardam a un contenidor
            for (int i = 0; i < sice ; i++) {
                contenidor.write(f.read());
            }
            // el pasam a un array de bits
            byte [] bytarrai = contenidor.toByteArray();
            // feim el calcul per sebre les vegades que ens falten per arribar al seguent header
            int rest = 512 -(sice % 512);
            f.skip(rest);
            // ficam dedins l'array el nou objecte amb tots els parametres
            arxiulist.add(new arxiu(Nom,sice,bytarrai));
            // resetejam les variables
            Nom = "";
            oct = "";
        }

        }catch (Exception e){

        }
    }

    public InputStream getInputStream(String s) {
        // el que feim es pasar a un inputstream els byts del valors que ens donen
        // per aixo cridam a getBytes i li pasam el nom
        InputStream ips = new ByteArrayInputStream(getBytes(s));
        return ips;
    }
}
class arxiu {
    // aquesta clase - objecte el que fa es amagatzamar dades
    private String Nom= "";
    private int Temañ = 0;
    private byte[] contin;

    public arxiu(String nom, int temañ,byte[] conting){
        this.contin=conting;
        this.Nom=nom;
        this.Temañ=temañ;
    }

    public String getNom() {
        return Nom;
    }

    public byte[] getContin() {
        return contin;
    }
}
// cream la excepcio per si no troba la ruta
class exceptionNoRut extends Exception{
    exceptionNoRut(){
        super();
        System.out.println("La ruta no es correcte");
    }
}
// Cream la excepcio per si no ha inserit un fitxer tar
class exceptionNoTar extends Exception{
    exceptionNoTar(){
        super();
        System.out.println("Mas introduit un fitxer que no es tar");
    }
}
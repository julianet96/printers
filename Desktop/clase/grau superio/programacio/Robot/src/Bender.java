import java.util.LinkedList;

/**
 * Created by julià on 15/03/2017.
 */
class Bender {
    char[][] mapa;
    int PosicioXx=0;
    int PosicioXy=0;
//    int Posicio$x=0;
//    int Posicio$y=0;
    int PosicioIx=0;
    int PosicioIy=0;
    int PosicioT1x=0;
    int PosicioT1y=0;
    int PosicioTy=0;
    int PosicioTx=0;
    int altura=1;
    int llarc=0;
    // Constructor: ens passen el mapa en forma d'String
    public Bender(String mapa) {
        this.mapa=map(mapa);
    }
    // Navegar fins a l'objectiu («$»).
// El valor retornat pel mètode consisteix en una cadena de
// caràcters on cada lletra pot tenir els valors «S», «N», «W» o «E»,
// segons la posició del robot a cada moment.
    public String run() {
        String result="";
        boolean llista = true;
        LinkedList<Character> Onanar=RenovaLlista(llista);
        PosicioCaracters(this.mapa);
        while (true){
            if(mapa[PosicioXx+1][PosicioXy]=='#'&&Onanar.getFirst()=='S'){
                Onanar.removeFirst();
            }
            if(mapa[PosicioXx][PosicioXy+1]=='#'&&Onanar.getFirst()=='E'){
                if(mapa[PosicioXx+1][PosicioXy]=='#'){Onanar.removeFirst();}
                else { Onanar=RenovaLlista(llista);}
            }
            if(mapa[PosicioXx-1][PosicioXy]=='#'&&Onanar.getFirst()=='N'){
                if(mapa[PosicioXx+1][PosicioXy]=='#'&&mapa[PosicioXx][PosicioXy+1]=='#'||mapa[PosicioXx-1][PosicioXy]=='#'){Onanar.removeFirst();}
                else {Onanar=RenovaLlista(llista);}
            }
            if(mapa[PosicioXx][PosicioXy-1]=='#'&&Onanar.getFirst()=='W'){
                if(llista==false){ Onanar.removeFirst();}
                else{
                    Onanar=RenovaLlista(llista);
                    continue;
                }

            }
            //caminam cap alla on toca
            result+= Unapasa(Onanar);
            //comprovam si es el calor T
            if(mapa[PosicioXx][PosicioXy]=='T'){
                if(PosicioXx==PosicioT1x&&PosicioXy==PosicioT1y){
                    PosicioXx=PosicioTx;
                    PosicioXy=PosicioTy;
                }else {
                    PosicioXx=PosicioT1x;
                    PosicioXy=PosicioT1y;
                }
            }
            //Comprovam si la posicio en que esteim es el velor I
            if(mapa[PosicioXx][PosicioXy]=='I'){
                llista=false;
                Onanar=RenovaLlista(llista);
            }

            //Comprovam si es el velor $
            if(mapa[PosicioXx][PosicioXy]=='$'){
                break;
            }

        }

        return result;
    }
    LinkedList<Character> RenovaLlista(boolean llista){
       LinkedList<Character> Onanar=new LinkedList<>();
        if(llista==true){
            Onanar.add('S');
            Onanar.add('E');
            Onanar.add('N');
            Onanar.add('W');
        }else {
            Onanar.add('N');
            Onanar.add('W');
            Onanar.add('S');
            Onanar.add('E');
        }
        return Onanar;
    }

    char Unapasa(LinkedList<Character> Onanar){
        char result=' ';
        if(mapa[PosicioXx][PosicioXy]!='#'&&Onanar.getFirst()=='S'){
            if(mapa[PosicioXx+1][PosicioXy]!='#'){
                PosicioXx++;
            }
            result ='S';
        }
        if(mapa[PosicioXx][PosicioXy]!='#'&&Onanar.getFirst()=='E'){
            if(mapa[PosicioXx][PosicioXy+1]!='#'){
                PosicioXy++;
            }
            result='E';
        }
        if(mapa[PosicioXx][PosicioXy]!='#'&&Onanar.getFirst()=='N'){
            if(mapa[PosicioXx-1][PosicioXy]!='#'){
                PosicioXx--;
            }
            result='N';
        }
        if(mapa[PosicioXx][PosicioXy]!='#'&&Onanar.getFirst()=='W'){
            if(mapa[PosicioXx][PosicioXy-1]!='#'){
                PosicioXy--;
            }
            result='W';
        }
        return result;
    }
    //Construim el mapa dedins de un array bidimensional
    char[][] map (String mapa){
        char[][] map;
        int cont=0;
        for (int i = 0; i <mapa.length() ; i++) {
            cont++;
            char c = mapa.charAt(i);
            if(llarc<=cont) {
                llarc = cont;
            }
            if (c =='\n'){altura++; cont=0;}
        }
        map= new char[altura][llarc];
        for (int i = 0 ,j=0,y=0; i <mapa.length() ; i++) {
            if (mapa.charAt(i) == '\n') {
                j++;
                y = 0;
                i++;
            }
            char  c = mapa.charAt(i);
            map[j][y]=c;
            y++;
        }
        return map;
    }

    //Volem sebre la posicio en que es trova la X
    void PosicioCaracters(char[][] mapa){
        for (int i = 0; i <altura ; i++) {
            for (int j = 0; j <llarc ; j++) {
                if(mapa[i][j]=='X'){
                    PosicioXx=i;
                    PosicioXy=j;
                }
//                if(mapa[i][j]=='$'){
//                    Posicio$x=i;
//                    Posicio$y=j;
//                }
                if(mapa[i][j]=='T'&&PosicioTx==0){
                    PosicioTx=i;
                    PosicioTy=j;
                }
                if(mapa[i][j]=='T'&&PosicioTx!=0){
                    PosicioT1x=i;
                    PosicioT1y=j;
                }
                if(mapa[i][j]=='I'){
                    PosicioIx=i;
                    PosicioIy=j;
                }
            }
        }
    }
}

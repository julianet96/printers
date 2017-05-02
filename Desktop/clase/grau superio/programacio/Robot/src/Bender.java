import java.util.LinkedList;
/**
 * Created by julià on 15/03/2017.
 */
class Bender {
    private char[][] mapa;
    private int[][] mapaint;
    private int PosicioXx=0;
    private int PosicioXy=0;
    private int PosicioT1x=0;
    private int PosicioT1y=0;
    private int PosicioTy=0;
    private int PosicioTx=0;
    private int altura=1;
    private int llarc=0;
    private int Posicio$x;
    private int Posicio$y;
    // Constructor: ens passen el mapa en forma d'String
    public Bender(String mapa) {
        this.mapa=map(mapa);
    }

    public String run() {
        String result="";
        boolean tipollista = true;
        LinkedList<Character> Onanar=RenovaLlista(tipollista);
        while (true){
            //miram amem si a la seguent posicio hi ha # i estem en S
            if(mapa[PosicioXx+1][PosicioXy]=='#'&&Onanar.getFirst()=='S'){
                Onanar.removeFirst();
            }
            //miram amem si a la seguent posicio hi ha # i estem en E
            if(mapa[PosicioXx][PosicioXy+1]=='#'&&Onanar.getFirst()=='E'){
                //em de sebre si em de tornar a renovar la llista o nem de eliminar un
                if(mapa[PosicioXx+1][PosicioXy]=='#'){Onanar.removeFirst();}
                else { Onanar=RenovaLlista(tipollista);}
            }
            //miram amem si a la seguent posicio hi ha # i estem en N
            if(mapa[PosicioXx-1][PosicioXy]=='#'&&Onanar.getFirst()=='N'){
                //em de sebre si em de tornar a renovar la llista o nem de eliminar un
                if(mapa[PosicioXx+1][PosicioXy]=='#'&&mapa[PosicioXx][PosicioXy+1]=='#'||mapa[PosicioXx-1][PosicioXy]=='#'){Onanar.removeFirst();}
                else {Onanar=RenovaLlista(tipollista);}
            }
            //miram amem si a la seguent posicio hi ha # i estem en W
            if(mapa[PosicioXx][PosicioXy-1]=='#'&&Onanar.getFirst()=='W'){
                if(!tipollista){ Onanar.removeFirst();}
                else{
                    Onanar=RenovaLlista(tipollista);
                    continue;
                }
            }
            //caminam cap alla on toca
            result+= Unapasa(Onanar);
            //comprovam si es el calor T
            if(mapa[PosicioXx][PosicioXy]=='T'){
                //Comprovam quina T es
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
                tipollista=false;
                Onanar=RenovaLlista(tipollista);
            }
            //Comprovam si es el velor $
            if(mapa[PosicioXx][PosicioXy]=='$'){
                break;
            }
        }
        return result;
    }
    public int bestRun(){
        //iniciam posant els valors 1 a dalt a baix dreta y esquerra de la X
        mapaint[PosicioXx][PosicioXy+1]=1;
        mapaint[PosicioXx][PosicioXy-1]=1;
        mapaint[PosicioXx+1][PosicioXy]=1;
        mapaint[PosicioXx-1][PosicioXy]=1;
        int result;
        int cont=0;
        while (mapaint[Posicio$x][Posicio$y]==0){
            cont++;
            for (int i = 0; i <altura ; i++) {
                for (int j = 0; j <mapa[i].length ; j++) {
                    //si ni es peret i tampoc el valor X i en el mapa de ints es un 0
                    if(mapa[i][j]!='#'&&mapa[i][j]!='X'&&mapaint[i][j]==0){
                        //cream el numero que em de posar
                        int num = cont +1;
                        //si el valor de la dreta es igual al valor antario
                        if(mapaint[i][j+1]==cont&&mapa[i][j+1]!='#'){
                            mapaint[i][j]=num;
                            //si es el valor T
                            if(mapa[i][j]=='T'&&mapaint[PosicioT1x][PosicioT1y]==0){
                                mapaint[PosicioT1x][PosicioT1y]=num;
                            }
                        }
                        //si el valor de la esquerra es igual al valor antario
                        if(mapaint[i][j-1]==cont&&mapa[i][j-1]!='#'){
                            mapaint[i][j]=num;
                            if(mapa[i][j]=='T'&&mapaint[PosicioT1x][PosicioT1y]==0){
                                mapaint[PosicioT1x][PosicioT1y]=num;
                            }
                        }
                        //si el valor de abaix es igual al valor antario
                        if(mapaint[i+1][j]==cont&&mapa[i+1][j]!='#'){
                            mapaint[i][j]=num;
                            if(mapa[i][j]=='T'&&mapaint[PosicioT1x][PosicioT1y]==0){
                                mapaint[PosicioT1x][PosicioT1y]=num;
                            }
                        }
                        //si el valor de adalt es igual al valor antario
                        if(mapaint[i-1][j]==cont&&mapa[i-1][j]!='#'){
                            mapaint[i][j]=num;
                            if(mapa[i][j]=='T'&&mapaint[PosicioT1x][PosicioT1y]==0){
                                mapaint[PosicioT1x][PosicioT1y]=num;
                            }
                        }
                    }
                }
            }
        }
        //el resultat sira el velor que contegui la posicio del dolar
        result = mapaint[Posicio$x][Posicio$y];
        return result;
    }
        //construim la llista tant per la primera vegada com si la em de tornar a renovar
    private LinkedList<Character> RenovaLlista(boolean llista){
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
// aquesta funcio va fent cada pasa que fa el robot
    private char Unapasa(LinkedList<Character> Onanar){
        char result=' ';
        //Anam cap al S si no trobam #
        if(mapa[PosicioXx][PosicioXy]!='#'&&Onanar.getFirst()=='S'){
            if(mapa[PosicioXx+1][PosicioXy]!='#'){
                PosicioXx++;
            }
            result ='S';

          //Anam cap a E si no trobam #
        }else if(mapa[PosicioXx][PosicioXy]!='#'&&Onanar.getFirst()=='E'){
            if(mapa[PosicioXx][PosicioXy+1]!='#'){
                PosicioXy++;
            }
            result='E';

          //si anam cap al N i no hii ha #
        }else if(mapa[PosicioXx][PosicioXy]!='#'&&Onanar.getFirst()=='N'){
            if(mapa[PosicioXx-1][PosicioXy]!='#'){
                PosicioXx--;
            }
            result='N';

          //anam cap a W si no trobam #
        }else if(mapa[PosicioXx][PosicioXy]!='#'&&Onanar.getFirst()=='W'){
            if(mapa[PosicioXx][PosicioXy-1]!='#'){
                PosicioXy--;
            }
            result='W';
        }
        return result;
    }
    //Construim el mapa dedins de un array bidimensional
    private char[][] map (String mapa){
        char[][] map;
        int cont=0;
        //Volem sebre quina es la altura i quina es la llergada de cada mapa
        for (int i = 0; i <mapa.length() ; i++) {
            cont++;
            char c = mapa.charAt(i);
            if(llarc<=cont) {
                llarc = cont;
            }
            if (c =='\n'){altura++; cont=0;}
        }
        //per fer els mapes justos es a dir llevant el \n li llevam un al llarc
        llarc--;
        map= new char[altura][llarc];
        mapaint= new int[altura][llarc];
        //Construim el mapa
        for (int i = 0 ,j=0,y=0; i <mapa.length() ; i++) {

            if (mapa.charAt(i) == '\n') {
                j++;
                y = 0;
                i++;
            }
            char  c = mapa.charAt(i);
            map[j][y]=c;
            //nam comprovant alla on esta la figura X, les figures T i la figura $
            if(c=='X'){
                PosicioXx=j;
                PosicioXy=y;
            }
            if(c=='T'&&PosicioTx==0){
                PosicioTx=j;
                PosicioTy=y;
            }
            if(c=='T'&&PosicioTx!=0){
                PosicioT1x=j;
                PosicioT1y=y;
            }if(c=='$'){
                Posicio$x=j;
                Posicio$y=y;
            }
            y++;
        }
        //aquest for el que fa es recorre l'array i als valors que hi ha un null li posa el velor #
        for (int i = 0; i < altura ; i++) {
            for (int j = 0; j <llarc ; j++) {
                if(map[i][j]==0){
                    map[i][j]='#';
                }
            }
        }
        return map;
    }
}



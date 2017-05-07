import java.util.*;

public class Token {
    enum Toktype {
        NUMBER, OP, PAREN
    }

    // Pensa a implementar els "getters" d'aquests atributs
    private Toktype ttype;
    private int value;
    private char tk;
    // cream els geters i seters
    public void setValue(int value){
        this.value=value;
    }
    private void setTk(char tk){
        this.tk=tk;
    }
    private void setTtype(Toktype t){
        this.ttype=t;
    }
    public int getValue(){
      return this.value;
    }
    public char getTk(){
        return this.tk;
    }
    public Toktype getTtype(){
        return this.ttype;
    }



    // Constructor privat. Evita que es puguin construir objectes Token externament
    private Token() {

    }

    // Torna un token de tipus "NUMBER"
    static Token tokNumber(int value) {
        Token number = new Token();
        number.setValue(value);
        number.setTtype(Toktype.NUMBER);
        return number ;
    }

    // Torna un token de tipus "OP"
    static Token tokOp(char c) {
        Token op = new Token();
        op.setTk(c);
        op.setTtype(Toktype.OP);
        return op;
    }

    // Torna un token de tipus "PAREN"
    static Token tokParen(char c) {
        Token parner = new Token();
        parner.setTk(c);
        parner.setTtype(Toktype.PAREN);
        return parner;
    }

    // Mostra un token (conversió a String)
    public String toString() {
        if(this.ttype == Toktype.NUMBER){
            return String.valueOf(this.value);
        }
        return String.valueOf(this.tk);
    }

    // Mètode equals. Comprova si dos objectes Token són iguals
    public boolean equals(Object o) {
        // miram si es un token
        if (o instanceof Token) {
            Token to = (Token) o;
            // si son numeros
            if(this.ttype == Toktype.NUMBER){
                return this.value==to.value&&this.ttype == to.ttype;
                // si son operants
            }else if(this.ttype == Toktype.OP){
                return this.tk == to.tk &&this.ttype == to.ttype;
                // si son parentesis
            }else if(this.ttype == Toktype.PAREN){
                return this.tk == to.tk &&this.ttype == to.ttype;
            }

        }
        return false;
    }

    // A partir d'un String, torna una llista de tokens
    public static Token[] getTokens(String expr) {
        Token[] result ;
//          Cream una llista on anirem guardant els resultats
            List<Token> resultat = new ArrayList<>();
//          mentres que quedin velos dedisn l'string
            for (int i = 0; i <expr.length() ; i++) {
                String r = "";
                // Comprovam si son numeros i els anam ficant dedisn un string anomenat resultat
                while (expr.charAt(i)>='0'&&expr.charAt(i)<='9'){
                    r += expr.charAt(i);
                    if(i < expr.length()-1){
                        i++;
                    }else {
                        break;
                    }

                }
                char c =expr.charAt(i);
//                Si r no esta buit pasam a int el string i el cream un token com a numero
                if (r != ""){
                    resultat.add(Token.tokNumber(Integer.parseInt(r)));
                }
                // si son operants cream el token com a op
                if (c == '+'||c == '-'||c=='*'||c=='/'||c=='^'){
                    resultat.add(Token.tokOp(c));
//                    Si es un parentesis el pasam com a token parner
                }else if (c=='('||c==')'){
                    resultat.add(Token.tokParen(c));
                }
            }
//            Pasam el resultat de la llista a Array de tokens i el retornam
            result= resultat.toArray(new Token[resultat.size()]) ;
        return result;
    }
}

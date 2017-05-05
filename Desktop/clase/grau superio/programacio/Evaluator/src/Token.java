import java.util.*;

public class Token {
    enum Toktype {
        NUMBER, OP, PAREN
    }

    // Pensa a implementar els "getters" d'aquests atributs
    private Toktype ttype;
    private int value;
    private char tk;

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
        if (o instanceof Token) {
            Token to = (Token) o;
            if(this.ttype == Toktype.NUMBER){
                return this.value==to.value&&this.ttype == to.ttype;
            }else if(this.ttype == Toktype.OP){
                return this.tk == to.tk &&this.ttype == to.ttype;
            }else if(this.ttype == Toktype.PAREN){
                return this.tk == to.tk &&this.ttype == to.ttype;
            }

        }
        return false;
    }

    // A partir d'un String, torna una llista de tokens
    public static Token[] getTokens(String expr) {
        Token[] result ;

            List<Token> resultat = new ArrayList<>();
            for (int i = 0; i <expr.length() ; i++) {
                String r = "";

                while (expr.charAt(i)>='0'&&expr.charAt(i)<='9'){
                    r += expr.charAt(i);
                    if(i < expr.length()-1){
                        i++;
                    }else {
                        break;
                    }

                }
                char c =expr.charAt(i);

                if (r != ""){
                    resultat.add(Token.tokNumber(Integer.parseInt(r)));
                }
                if (c == '+'||c == '-'||c=='*'||c=='/'||c=='^'){
                    resultat.add(Token.tokOp(c));
                }else if (c=='('||c==')'){
                    resultat.add(Token.tokParen(c));
                }
            }
            result= resultat.toArray(new Token[resultat.size()]) ;
        return result;
    }
}

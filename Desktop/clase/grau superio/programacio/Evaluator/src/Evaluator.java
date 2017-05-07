import java.util.LinkedList;

public class Evaluator {

    public static int calculate(String expr) {
        //listes on anirem amagatzamant els resultat i els operadors
        LinkedList<Token> operants = new LinkedList<>();
        LinkedList<Token> Result = new LinkedList<>();
        // Convertim l'string d'entrada en una llista de tokens
        Token[] tokens = Token.getTokens(expr);
        for (int i = 0; i < tokens.length ; i++) {
            //comprovam si es un numero el ficam directa a resultat
            if(tokens[i].getTtype()==Token.Toktype.NUMBER){
                Result.add(tokens[i]);
                // si es un numero feim varies comprovacions
            }else if(tokens[i].getTtype()==Token.Toktype.OP){
                // si dedins la llista de operants no hi ha cap valor introduim l'operador
                if(operants.size()==0){
                    operants.add(tokens[i]);
                    // si en la llista de operants hi ha una suma o resta buida i el que li pesam ho hes pasam   la llista sensera
                }else if(preferencia(tokens[i].getTk())==preferencia(operants.peek().getTk())&&preferencia(tokens[i].getTk())==1){

                    for (int j = operants.size(); j > 0 ; j--) {
                        if(operants.peek().getTk()!='('){
                            Result.add(operants.pop());
                        }
                    }
                    operants.addFirst(tokens[i]);
                    // si la praferencia es mes alta ho ficam dedins la llista de operants
                }else if(preferencia(operants.peek().getTk())< preferencia(tokens[i].getTk())){
                    operants.addFirst(tokens[i]);
                    // si la prafarencia es mes petita del derrer valor de la llista la buidam sensera
                }else if(preferencia(operants.peek().getTk())>preferencia(tokens[i].getTk())) {

                    for (int j = operants.size(); j > 0 ; j--) {
                        if(operants.peek().getTk()!='('){
                            Result.add(operants.pop());
                        }
                    }
                    operants.addFirst(tokens[i]);
                    // si la preferencia es mejor que 1 i el valor que volem incloura a la llista
                    //es igual al derrer que hi ha treim el primer valor
                }else if(preferencia(tokens[i].getTk())==preferencia(operants.peek().getTk())) {
                    Result.add(operants.pop());
                    operants.addFirst(tokens[i]);
                    // si no es cap de aquests el ficam dedis la llista de operants
                }else if(operants.peek().getTk()!='('||operants.peek().getTk()!=')') {
                    operants.addFirst(tokens[i]);
                }
                // si son parentesis
            }else if(tokens[i].getTtype()==Token.Toktype.PAREN){
                // Si es el parentesis de opertura el ficam dedins la llista
                if(tokens[i].getTk()=='('){
                    operants.addFirst(tokens[i]);
                }else {
                    // si es el parentesis de tencat treim tots els valors que hi ha fins el parentesis de opertura
                    for (int j = 0; j <operants.size() ; j++) {
                        if(operants.peek().getTk()!='('){
                            Result.add(operants.pop());
                        }
                        if(operants.peek().getTk()=='('){
                            operants.pop();
                            break;
                        }
                    }
                }
            }
            // si es el derrer valor que miram pasam tot lo que hi ha dedins la llista de operants el pasam a resultats
            if (i==tokens.length-1){
                Result.addAll(operants);
            }

        }

        // Efectua el procediment per convertir la llista de tokens en notació RPN
        Token[] list = Result.toArray(new Token[Result.size()]);

        // Finalment, crida a calcRPN amb la nova llista de tokens i torna el resultat
        return calcRPN(list);
    }

    public static int calcRPN(Token[] list) {
        // cream una llista on anirem amagazamant cada resultat
        LinkedList<Token> RPN = new LinkedList<>();

        for (int i = 0; i < list.length; i++) {
            // si es un numero el nam incloigent a la llista
            if(list[i].getTtype()== Token.Toktype.NUMBER){
                RPN.addFirst(list[i]);
                // si es un valor
            }else if (list[i].getTtype() == Token.Toktype.OP){
                // ficam els dos primers valors de la llista que em creat
                int y = RPN.removeFirst().getValue();
                int x = RPN.removeFirst().getValue();
                // cream un npu token de numero on per que funciona cridam a la funcio calcula
                Token[] resTok = new Token[]{Token.tokNumber(calcula(list[i].getTk(),x,y))};
                // ficam dedins la llista de resultat el token
                RPN.addFirst(resTok[0]);
            }
        }
        // cuant ja nomes queda un valor el treim i el retornam
        return RPN.removeFirst().getValue();
        
    }
    // la funcio calcula el que fa es li pasam l'operant i dos valors
    static int calcula(char tk, int x, int y ){
        int result = 0;
        switch (tk){

            case '+':
                result = x+y;
                break;
            case '-':
                result = x-y;
                break;
            case '*':
                result = x*y;
                break;
            case '/':
                result = x/y;
                break;
            case '^':
                result =(int) Math.pow(x,y);
        }
        return result;
    }
    // la funcio preferencia el que fa es mira quin operant es i depen del operant li asigna un numero
    static int preferencia(char c){
        if(c == '+'|| c=='-'){
            return 1;
        }else if(c=='*'||c=='/'){
            return 2;
        }else {
            return 3;
        }

    }
}

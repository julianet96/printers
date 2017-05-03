import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Evaluator {


    public static int calculate(String expr) {
        LinkedList<Token> operants = new LinkedList<>();
        LinkedList<Token> valors = new LinkedList<>();
        // Convertim l'string d'entrada en una llista de tokens
        Token[] tokens = Token.getTokens(expr);
        for (int i = 0; i < tokens.length ; i++) {
            if(tokens[i].getTtype()==Token.Toktype.NUMBER){
                valors.add(tokens[i]);
            }else if(tokens[i].getTtype()==Token.Toktype.OP){
                if(operants.size()==0){
                    operants.add(tokens[i]);
                }else if((operants.peek().getTk()=='-'||operants.peek().getTk()=='+')&&(tokens[i].getTk()=='-'||tokens[i].getTk()=='+')){

                    for (int j = operants.size(); j > 0 ; j--) {
                        if(operants.peek().getTk()!='('){
                            valors.add(operants.pop());
                        }
                    }
                    operants.addFirst(tokens[i]);
                }else if((operants.peek().getTk()=='-'||operants.peek().getTk()=='+')&&(tokens[i].getTk()=='*'||tokens[i].getTk()=='/')){
                    operants.addFirst(tokens[i]);
                }else if((operants.peek().getTk()=='*'||operants.peek().getTk()=='/')&&(tokens[i].getTk()=='+'||tokens[i].getTk()=='-')) {

                    for (int j = operants.size(); j > 0 ; j--) {
                        if(operants.peek().getTk()!='('){
                            valors.add(operants.pop());
                        }
                    }
                    operants.addFirst(tokens[i]);
                }else if((operants.peek().getTk()=='*'||operants.peek().getTk()=='/')&&(tokens[i].getTk()=='*'||tokens[i].getTk()=='/')) {
                    valors.add(operants.pop());
                    operants.addFirst(tokens[i]);
                }else if(operants.peek().getTk()!='('||operants.peek().getTk()!=')') {
                    operants.addFirst(tokens[i]);
                }

            }else if(tokens[i].getTtype()==Token.Toktype.PAREN){
                if(tokens[i].getTk()=='('){
                    operants.addFirst(tokens[i]);
                }else {
                    for (int j = 0; j <operants.size() ; j++) {
                        if(operants.peek().getTk()!='('){
                            valors.add(operants.pop());
                        }
                        if(operants.peek().getTk()=='('){
                            operants.pop();
                            break;
                        }
                    }
                }

            }
            if (i==tokens.length-1){
                valors.addAll(operants);
            }


        }
        // Efectua el procediment per convertir la llista de tokens en notació RPN
        Token[] list = valors.toArray(new Token[valors.size()]);
        System.out.println(Arrays.toString(list));
        // Finalment, crida a calcRPN amb la nova llista de tokens i torna el resultat
        return calcRPN(list);
    }

    public static int calcRPN(Token[] list) {
        LinkedList<Token> RPN = new LinkedList<>();
        int result = 0;
        for (int i = 0; i < list.length; i++) {
            if(list[i].getTtype()== Token.Toktype.NUMBER){
                RPN.addFirst(list[i]);
            }else if (list[i].getTtype() == Token.Toktype.OP){
                int y = RPN.removeFirst().getValue();
                int x = RPN.removeFirst().getValue();
                switch (list[i].getTk()){

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
                }
                Token[] resTok = new Token[]{Token.tokNumber(result)};
                RPN.addFirst(resTok[0]);
            }
        }

        return RPN.removeFirst().getValue();
        
    }



}

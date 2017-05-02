import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Evaluator {


    public static int calculate(String expr) {
        // Convertim l'string d'entrada en una llista de tokens
        Token[] tokens = Token.getTokens(expr);
        // Efectua el procediment per convertir la llista de tokens en notació RPN
        // Finalment, crida a calcRPN amb la nova llista de tokens i torna el resultat
        return 0;
    }

    public static int calcRPN(Token[] list) {
        int result = 0;

        LinkedList<Character> operants = new LinkedList<>();
        LinkedList<Integer> valors = new LinkedList<>();
        // Calcula el valor resultant d'avaluar la llista de tokens
//        for (int i = 0; i < list.length; i++) {
//            if (list[i].getTtype() == Token.Toktype.OP) {
//                operants.addLast(list[i].getTk());
//            }
//            if (list[i].getTtype() == Token.Toktype.NUMBER) {
//                valors.addFirst(list[i].getValue());
//            }
//        }
        int cont = 0;
        while (true){
            if(cont<list.length){
                if (list[cont].getTtype() == Token.Toktype.OP) {
                    operants.addLast(list[cont].getTk());
                    cont++;
                }else
                if (list[cont].getTtype() == Token.Toktype.NUMBER) {
                    valors.addFirst(list[cont].getValue());
                    cont++;
                    continue;
                }
            }


            int x = valors.pop();
            int y = valors.pop();
             if (operants.peek() == '-') {
                 operants.pop();
                 result = y - x;
                  valors.addFirst(result);

             } else if (operants.peek() == '+') {
                 operants.pop();
                  result = x + y;
                 valors.addFirst(result);
             }else if(operants.peek() == '*'){
                 operants.pop();
                 result = x * y;
                 valors.addFirst(result);
             }
             if(valors.size() == 1 && cont==list.length){
                 break;
             }

        }


        Integer resultat = valors.pop();



        return resultat;
        
    }



}

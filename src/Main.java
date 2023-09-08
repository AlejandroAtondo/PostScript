import java.util.*;

public class Main {
    public static Stack<Double> stack = new Stack<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingresa la expresión en formato PostScript (o escribe 'quit' para salir): ");
            String expresion = scanner.nextLine();

            if (expresion.equals("quit")) {
                break; // Salir del bucle si se ingresa "quit"
            }

            String[] tokens = expresion.split(" ");

            for (String token : tokens) {
                if (esNumero(token)) {
                    stack.push(Double.parseDouble(token));
                } else if (esOperador(token)) {
                    realizarOperacion(token);
                } else {
                    System.out.println("Error: token no reconocido - " + token);
                    break;
                }
            }
        }

        scanner.close(); // Cerrar el Scanner al salir del bucle
    }

    public static boolean esNumero(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean esOperador(String token) {
        return token.matches("add|sub|mul|div|eq|pop|exch|dup|pstack|");
    }

    public static void realizarOperacion(String operador) {
        switch (operador) {
            case "add":
                if (stack.size() >= 2) {
                    double operand2 = stack.pop();
                    double operand1 = stack.pop();
                    stack.push(operand1 + operand2);
                } else {
                    System.out.println("Error: no hay suficientes elementos para el operador " + operador);
                }
                break;
            case "sub":
                if (stack.size() >= 2) {
                    double operand2 = stack.pop();
                    double operand1 = stack.pop();
                    stack.push(operand1 - operand2);
                } else {
                    System.out.println("Error: no hay suficientes elementos para el operador " + operador);
                }
                break;
            case "mul":
                if (stack.size() >= 2) {
                    double operand2 = stack.pop();
                    double operand1 = stack.pop();
                    stack.push(operand1 * operand2);
                } else {
                    System.out.println("Error: no hay suficientes elementos para el operador " + operador);
                }
                break;
            case "div":
                if (stack.size() >= 2) {
                    double operand2 = stack.pop();
                    double operand1 = stack.pop();
                    stack.push(operand1 / operand2);
                } else {
                    System.out.println("Error: no hay suficientes elementos para el operador " + operador);
                }
                break;
            case "eq":
                if (stack.size() >= 2) {
                    double operand2 = stack.pop();
                    double operand1 = stack.pop();
                    if (operand2 == operand1){
                        stack.push(1.0);
                    }else if (operand2 != operand1){
                        stack.push(0.0);
                    }else{
                        System.out.println("Error: no hay suficientes elementos para el operador " + operador);
                    }
                    break;
                }
                break;
            case "pop":
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    System.out.println("Error: no hay elementos en la pila para eliminar");
                }
                break;
            case "exch":
                if (stack.size() >= 2) {
                    double operand2 = stack.pop();
                    double operand1 = stack.pop();
                    stack.push(operand2);
                    stack.push(operand1);
                } else {
                    System.out.println("Error: no hay suficientes operandos para el operador " + operador);
                }
                break;
            case "dup":
                if (!stack.isEmpty()) {
                    double elementoSuperior = stack.peek();
                    stack.push(elementoSuperior);
                } else {
                    System.out.println("Error: no hay elementos en la pila para duplicar");
                }
                break;
            case "pstack":
                imprimirPila();
                break;
            default:
                System.out.println("Operador no válido: " + operador);
                break;
        }
    }

    public static void imprimirPila() {
        if (!stack.isEmpty()){
            System.out.println("Contenido de la pila:");
            Stack<Double> reversedStack = new Stack<>();
            reversedStack.addAll(stack);
            Collections.reverse(reversedStack);

            for (Double elemento : reversedStack) {
                System.out.println(elemento);
            }
        }else{
            System.out.println("La pila está vacía");
        }

    }
}
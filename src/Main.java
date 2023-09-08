import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Double> pila = new Stack<>();

        while (true) {
            System.out.println("Ingresa la expresión en formato PostScript (o escribe 'quit' para salir): ");
            String expresion = scanner.nextLine();

            if (expresion.equals("quit")) {
                break; // Salir del bucle si se ingresa "quit"
            }

            String[] tokens = expresion.split(" ");

            for (String token : tokens) {
                if (esNumero(token)) {
                    pila.push(Double.parseDouble(token));
                } else if (esOperador(token)) {
                    if (pila.size() < 2) {
                        System.out.println("Error: no hay suficientes operandos para el operador " + token);
                        break;
                    }
                    double operand2 = pila.pop();
                    double operand1 = pila.pop();
                    double resultado = aplicarOperador(operand1, operand2, token);
                    pila.push(resultado);
                } else {
                    System.out.println("Error: token no reconocido - " + token);
                    break;
                }
            }

            if (pila.size() == 1) {
                double resultadoFinal = pila.pop();
                System.out.println("El resultado es: " + resultadoFinal);
            } else {
                System.out.println("Error: la expresión no pudo ser evaluada correctamente");
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
        return token.equals("add") || token.equals("mul") || token.equals("sub") ||
                token.equals("eq") || token.equals("exch") || token.equals("dup");
    }

    public static double aplicarOperador(double operando1, double operando2, String operador) {
        switch (operador) {
            case "add":
                return operando1 + operando2;
            case "sub":
                return operando1 - operando2;
            case "mul":
                return operando1 * operando2;
            case "eq":
                if (operando1==operando2){
                    return operando1 == operando2 ? 1.0 : 0.0;
                }
                return operando1 == operando2 ? 1.0 : 0.0;
            default:
                throw new IllegalArgumentException("Operador no válido: " + operador);
        }
    }


}
import java.util.Scanner;

public class TEST {

    private static Scanner scanner = new Scanner(System.in);
}

    public static int[] Numbers() {
    	
        System.out.println("Please Enter your first number");
        int num1 = scanner.nextInt();
        System.out.println("Please Enter your second number");
        int num2 = scanner.nextInt();
        scanner.close();
        
        int[] numArray = {num1,num2};
        
        return numArray;
    }

    public static void Final(int num1, int num2,char operator) {

        double result = 0.0;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;

            case '-':
                result = num1 - num2;
                break;

            case '*':
                result = num1 * num2;
                break;

            case '/':
                result = num1 / num2;
                break;

            default:
                System.out.println("Your operator is not correct");
                return;
        }

            System.out.println(num1 + " " + operator + " " + num2 + " = " + result);
    }
    public static void main(String[] args) {

        System.out.println("Please enter an operator");
        System.out.println("1. +");
        System.out.println("2. /");
        System.out.println("3. -");
        System.out.println("4. *");

        char operator=scanner.next().charAt(0);
        int[] finNumber = Numbers();
            
         Final(finNumber[0], finNumber[1], operator);

    }
    
}
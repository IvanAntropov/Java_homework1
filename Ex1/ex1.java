import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;

public class ex1{

    static int inputNumber(String text1, String text2){
        Scanner in = new Scanner(System.in);
        int number = 0;
        boolean check = true;
        while(check) {
            System.out.printf(text1);
            if (in.hasNextInt()) {
                number = in.nextInt();
                check = false;
            } else {
                System.out.println(text2);
                in.next();
            }
        }
        return number;
    }

    public static void main(String[] args) {
        var a = 2;
        var b = 5;
        int number; //вар не работает?
        int degree;
        a = inputNumber("Введите a: ", "Вводимый элемент должен быть целым числом!");
        b = inputNumber("Введите b: ", "Вводимый элемент должен быть целым числом!");

        try (FileWriter fw = new FileWriter("input.txt", false)) {
            fw.write("a " + a); //byte формат при чар?
            fw.append("\n");
            fw.write("b " + b);
            fw.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        String[] arr = new String[2];
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String str;
            while ((str = br.readLine()) != null){
                arr[i] = str;
                i++;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        number = Integer.parseInt(arr[0].split(" ")[1].trim ());
        degree = Integer.parseInt(arr[1].split(" ")[1].trim ());
        var answer = Double.toString(Math.pow(number,degree));

        System.out.printf("a: %d\nb: %d\nanswer: %s", number,degree, answer);

        try (FileWriter fw = new FileWriter("output.txt", false)) {
            fw.write(answer);
            fw.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}



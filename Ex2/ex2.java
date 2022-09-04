import java.util.Scanner;

public class ex2{

    // нерекурсивный способ решения задачи, возвращает массив с кол-вом путей к каждому числу по индексу
    static int[] solve(int a, int b, int c, int d) {
        int[] arr = new int[b + 1];
        arr[a] = 1;
        for (int index = a + 1; index <= b; index++) {
            if (index % d == 0 && index >= c) {
                arr[index] = arr[index - c] + arr[index / d];
            } else if(index >= c){
                arr[index] = arr[index - c];
            }
        }
        return arr;
    }

    // Ввод числа с проверкой
    static int inputNumber(String text1, String text2){
        Scanner in = new Scanner(System.in);
        int number = 0;
        boolean check = true;
        while(check) {
            System.out.printf(text1);
            if (in.hasNextInt()) {
                number = in.nextInt();
                if(number < 0){
                    System.out.println(text2);
                }else {
                    check = false;
                }
            } else {
                System.out.println(text2);
                in.next();
            }
        }
        return number;
    }

    // Печатает int массивы
    static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d -> %d\n", i, arr[i]);
        }
    }

    // Разворачивает строку
    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // Рекурсия, возвращает кол-во путей от a к b
    static int Recursion(int a, int b, int c, int d, int index) {
        int result = 0;
        if (index == a) {
            return 1;
        } else if (index % d == 0 && index >= c) {
            result = Recursion(a, b, c, d, index - c) + Recursion(a, b, c, d, index / d);
        } else if (index >= c) {
            result = Recursion(a, b, c, d, index - c);
        }
        return result;
    }

    // Возвращаент массив с кол-вом путей к каждому числу по индексу
    static int[] answerToArray(int a, int b, int c, int d) {
        int[] arr = new int[b + 1];
        int index = b;
        for(int i = index;i >= a; i--)
            arr[i] = Recursion(a, b, c, d, i);
        return arr;
    }

    // Собирает путь от a к b, возвращает массив строк
    static String[] reSolve(int a, int b, int c, int d, int directs) {
        String[] arrayOfreSolve = new String[directs];
        int[] arrayCheck = new int[directs];
        String SolveString = "";
        String reSolveString = "";
        int flag = 0;
        int check = directs;
        int index = b;
        while (directs != 0) {
            while (index > a){
                if (index%d == 0 ) {
                    for(int i = check - 1; i >= 0; i--){
                        int help = arrayCheck[directs - 1]  + index;
                        if(help ==  arrayCheck[i] && directs - 1 != check){
                            flag = 1;
                            break;
                        } else {
                            flag =0;
                        }
                    }
                    if(index/d >= a && directs != 1 && flag == 0) {
                        SolveString += index + "/" + d + " ";
                        reSolveString += d + "* ";
                        arrayCheck[directs - 1] += index;
                        index = index / d;
                    } else {
                        SolveString += index + "-" + c + " ";

                        reSolveString += c + "+ ";
                        index = index - c;
                    }
                } else {
                    SolveString += index + "-" + c + " ";

                    reSolveString += c + "+ ";
                    index = index - c;
                }
            }
            reSolveString = reverseString(reSolveString);
            arrayOfreSolve[directs - 1] = reSolveString;
//            System.out.println(SolveString); // распечатка обратного пути от b к a
            SolveString = "";
            index = b;
            reSolveString = "";
            directs--;
        }
        return arrayOfreSolve;
    }

    public static void main(String[] args){
        int a = 3;
        int b = 15;
        int c = 1;
        int d = 2;

        a = inputNumber("Введите a: ", "Вводимый элемент должен быть целым положителдьным числом!");
        b = inputNumber("Введите b: ", "Вводимый элемент должен быть целым положителдьным числом!");
        c = inputNumber("Введите +c: ", "Вводимый элемент должен быть целым положителдьным числом!");
        d = inputNumber("Введите *d: ", "Вводимый элемент должен быть целым положителдьным числом!");

        if (a > b){
            System.out.println("error: Нет решения!\n начальное число(a) должно быть меньшe конечного(b)!");
        }else{
            int index = b;
            int answer = Recursion(a,b,c,d,index);
            System.out.printf("Кол-во путей из %d -> %d при +%d *%d: %d \n",a,b,c,d,answer);

//            int[] arrayAnswer = answerToArray(a,b,c,d,index); // Печать массива с путями к каждому числу
//            printArr(arrayAnswer);

            String[] answer2 = reSolve(a,b,c,d,answer);
            for (int i = 0; i < answer2.length; i++) {
                System.out.println((i+1) + ": " +answer2[i]);
            }
        }
    }
}

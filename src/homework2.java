import java.util.Arrays;
import java.util.Random;

public class homework2 {
    public static void main(String[] args) {
        // Задание #1
        int[] arr1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("Задание #1");
        System.out.printf("Исходный массив:%n%s%n", Arrays.toString(arr1));
        for (int i = 0; i < arr1.length; i++)
            arr1[i] = arr1[i] == 1 ? 0 : 1;
        System.out.printf("Измененный массив%n%s%n%n", Arrays.toString(arr1));

        // Задание #2
        int[] arr2 = new int[8];
        for (int i = 0; i < arr2.length; i++)
            arr2[i] = i*3;
        System.out.printf("Задание #2%n%s%n%n", Arrays.toString(arr2));

        //Задание #3
        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Задание #3");
        System.out.printf("Исходный массив%n%s%n", Arrays.toString(arr3));
        for (int i = 0; i < arr3.length; i++) {
            if(arr3[i] < 6)
                arr3[i] *= 2;
        }
        System.out.printf("Измененный массив%n%s%n%n", Arrays.toString(arr3));

        // Задание #4
        int[][] square = new int[3][3];
        for (int i = 0; i < square.length; i++)
            square[i][i] = 1;
        System.out.println("Задание #4");
        System.out.println("Заполненный массив");
        for (int[] ints : square) {
            for (int j = 0; j < square.length; j++) { // Использую это значение т.к. массив квадратный
                System.out.printf("%d ", ints[j]);
            }
            System.out.println();
        }
        System.out.println(); // Зрительно отделяю задания

        // Задание #5
        int[] arr4 = new int[20];
        int diff = 200; // Ограниечение для генерации чисел
        Random rnd = new Random();
        for (int i = 0; i < arr4.length; i++)
            arr4[i] = rnd.nextInt(diff + 1);            // Заполняем массив псевдослучайными числами
        System.out.println("Задание #5");
        System.out.printf("Исходный массив%n%s%n", Arrays.toString(arr4));
        int min = arr4[0];  // Присваиваю первый элемент массива
        int max = arr4[0];  // так как пофиг какой :)
        for (int j : arr4) { // не знаю как упротить конструкцию
            if (j > max)
                max = j;
            if (j < min)
                min = j;
        }

        System.out.printf("Минимальное значение в массиве: %d%n", min);
        System.out.printf("Максимальное значение в массиве: %d%n%n", max);

        // Задание #6
        System.out.println("Задание #6");
        int[] arr5 = {3, 1, 2, 3, 4, 5, 6, 7, 8, 9}; // этот массив вернет true
        //int[] arr5 = {1, 1, 2, 3, 4, 5, 6, 7, 8, 9}; // этот вернет false
        System.out.println(checkBalance(arr5));
        System.out.println();

        // Задание #7
        int[] arr6 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("Задание #7");
        System.out.println("Исходный сассив");
        System.out.println(Arrays.toString(arr6));
        System.out.println("Массив после сдвига");
        if(arr6.length < 100) // Понятно что при этих исходных данных это уловие всегда true
            System.out.println(Arrays.toString(shiftArray(arr6, 7)));
        else
            System.out.println(Arrays.toString(shiftBigArray(arr6, 7))); // но этот метод тоже работает ))
    }

    private static boolean checkBalance(int[] arr) {
        if(arr.length < 4)
            return false; // потому что не получится суммы правой и левой половины массива
        int leftSum;
        int rightSum;
        int leftIdx = 1;
        int rightIdx = arr.length - 2;

        leftSum = arr[leftIdx - 1] + arr[leftIdx];
        rightSum = arr[rightIdx] + arr[rightIdx + 1];
        if(arr.length == 4) {
            return leftSum == rightSum;
        }

        while (leftIdx + 1 != rightIdx) {
            if(leftSum < rightSum)
                leftSum += arr[++leftIdx];
            else if (rightSum < leftSum)
                rightSum += arr[--rightIdx];
        }
        return leftSum == rightSum;
    }

    private static int[] shiftArray(int[] arr, int shift) {
        if(shift == 0)
            return arr;
        if(arr.length < 2)
            return arr;
        if(shift % arr.length == 0)
            return arr;

        int sh = shift % arr.length;

        if(shift < 0)
           sh = arr.length - Math.abs(sh); // для простоты будем сдвигать только в правую сторону

        for (int i = 0; i < sh; i++) {
            int last = arr[arr.length - 1];
            for (int j = arr.length - 1; j > 0; j--) {
                arr[j] = arr[j-1];
            }
            arr[0] = last;
        }

        return arr;
    }

    // Этот метод реализован для больших массивов в попытке оптимизировать сдвиг.
    private static int[] shiftBigArray(int[] arr, int shift) {
        if(shift == 0)
            return arr;
        if(arr.length < 2)
            return arr;
        if(shift % arr.length == 0)
            return arr;

        int sh = shift % arr.length;
        if(Math.abs(sh) > arr.length / 2) { // если сдвиг больше половины массива,
                                            // то быстрее сдвинуть массив в другую сторону
            sh = arr.length - Math.abs(sh);
            if(shift > 0)
                sh *= -1;
        }
        if(sh > 0) {
            for (int i = 0; i < sh; i++) {
                int last = arr[arr.length - 1];
                for (int j = arr.length - 1; j > 0; j--) {
                    arr[j] = arr[j-1];
                }
                arr[0] = last;
            }
        } else {
            for (int i = 0; i > sh; i--) {
                int last = arr[0];
                for (int j = 0; j < arr.length - 1; j++) {
                    arr[j] = arr[j+1];
                }
                arr[arr.length-1] = last;
            }
        }

        return arr;
    }
}

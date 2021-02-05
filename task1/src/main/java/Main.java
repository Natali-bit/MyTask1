import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
public class Main {

    public static void main(String[] args) {
        BufferedReader reader;

        ArrayList<Short> numbers = new ArrayList<Short>();
        try {
            reader = new BufferedReader(new FileReader("C:\\task1\\test1.txt"));

            while (reader.ready()) { //читаем файл
                numbers.add(Short.parseShort(reader.readLine()));
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Считывание невозможно");
        }

        // вывод в консоль
        // 90 перцентиль
        System.out.printf("перцентиль 90 = %.2f%n", percentile(numbers,90));

        //медиана
        System.out.printf("медиана = %.2f%n", median(numbers));
        // максимум
        System.out.printf("максимум = %.2f%n", maximum(numbers));
        //минимум
        System.out.printf("минимум = %.2f%n", minimum(numbers));
        //среднее
        System.out.printf("среднее = %.2f%n", average(numbers));


    }
    public static double maximum(ArrayList<Short>numbers){

        return Collections.max(numbers);
    }

    public static double minimum(ArrayList<Short>numbers){

        return  Collections.min(numbers);
    }
    public static double average(ArrayList<Short>numbers) {
        double sum = 0;
        for (int i=0; i< numbers.size(); i++) {
            sum += i;
        }
        return sum / numbers.size();
    }

    public static double median(ArrayList<Short> numbers){

        Collections.sort(numbers);
        int n = numbers.size()/2;
        double m;
        if (numbers.size() % 2 == 0)
            m = (numbers.get(n) + numbers.get( n - 1))/2;
        else
            m = numbers.get(n);

        return m;
    }
    public static double percentile(ArrayList<Short> numbers, double percentile) {
        Collections.sort(numbers);

        int N = numbers.size();
        double realIndex=percentile/100*(N-1);
        int index=(int)realIndex;
        double frac =realIndex-index;
        if(index+1<numbers.size())
            return (double) numbers.get(index)*(1-frac)+numbers.get(index+1)*frac;
        else
            return (double) numbers.get(index);
    }

}

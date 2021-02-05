import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        File dir = new File("C:\\task3\\testing"); //path указывает на директорию

        List<File> list = Arrays.asList(dir.listFiles());

        BufferedReader reader = null;
        Float[] cashArray;
        ArrayList<Float[]> cash = new ArrayList<Float[]>();
        int i = 0;
        String temp;
        for (File file : list) {
            cashArray = new Float[16];
            try {
                reader = new BufferedReader(new FileReader(file));

                while (reader.ready()) { //читаем файл
                    temp = reader.readLine();
                    cashArray[i] = Float.parseFloat(temp.substring(0, temp.length() - 2));
                    i++;
                }
                i=0;
                cash.add(cashArray);
                reader.close();

            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден");
            } catch (IOException e) {
                System.out.println("Считывание невозможно");
            }



        }

        List<Float> floatForMax;


        for (Float[] tempArray : cash){ //находим время максимальной загруженности на каждой их касс
            floatForMax = Arrays.asList(tempArray);
            int index = IntStream.range(0, floatForMax.size()).boxed()
                    .max(Comparator.comparing(floatForMax::get)).orElse(-1);

            System.out.println(index + 1);



        }


    }

}

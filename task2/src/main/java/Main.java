import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        BufferedReader reader;
        String s;

        float[] arrayShape = new float[8];
        ArrayList<Point2D> arrayPoint = new ArrayList<Point2D>();
        int i = 0;

        try {
            reader = new BufferedReader(new FileReader("C:\\task2\\file1.txt"));

            while (reader.ready()) { //читаем файл
                s = reader.readLine();
                s = s.substring(0, s.length() - 2);
                String[] arrStr = s.split(" ");
                arrayShape[i] = Float.parseFloat(arrStr[0]);
                arrayShape[i+1] = Float.parseFloat(arrStr[1]);
                i++;

            }
            reader = new BufferedReader(new FileReader("C:\\task2\\file2.txt"));

            while (reader.ready()) { //читаем файл
                s = reader.readLine();
                s = s.substring(0, s.length() - 2);
                String[] arrStr = s.split(" ");
                arrayPoint.add(new Point2D.Float(Float.parseFloat(arrStr[0]),Float.parseFloat(arrStr[1])));


            }

            reader.close();

        } catch (
                FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Файл не считан");
        }



        float x1 = arrayShape[0],x2 = arrayShape[2],x3 = arrayShape[4],x4 = arrayShape[6],
                y1 = arrayShape[1],y2 = arrayShape[3],y3 = arrayShape[5],y4 = arrayShape[7];




        float maxX = Math.max(Math.max(x1, x2), Math.max(x3, x4));
        float minX = Math.min(Math.min(x1, x2), Math.min(x3, x4));

        float maxY = Math.max(Math.max(y1, y2), Math.max(y3, y4));
        float minY = Math.min(Math.min(y1, y2), Math.min(y3, y4));
        for (Point2D point2D : arrayPoint) {
            double x= point2D.getX();
            double y = point2D.getY();

            if ((x > minX && x < maxX) && (y > minY && y < maxY)) {
                System.out.println("Точка внутри четырёхугольника");
            } else if (((x > x1 && y > y1) && (x < x2 && y < y2)) || ((x > x2 && y > y2) && (x < x3 && y < y3))
                    || ((x > x3 && y > y3) && (x < x4 && y < y4))) {
                System.out.println("Точка лежит на сторонах треугольника");
            } else if ((x == x1 && y == y1) || (x == x2 && y == y2) || (x == x3 && y == y3) || (x == x4 && y == y4)) {
                System.out.println("Точка - вершина четырёхугольника");
            } else {
                System.out.println("Точка снаружи четырёхугольника");
            }
        }


    }
}



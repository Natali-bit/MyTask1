import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = null;
        String temp;
        ArrayList<Visitor> visitors = new ArrayList<Visitor>();
        String[] tempForTime;

        try {
            reader = new BufferedReader(new FileReader("C:\\task4\\task4.txt"));

            while (reader.ready()) { //читаем файл
                temp = reader.readLine();
                temp = temp.substring(0, temp.length() - 2);
                tempForTime = temp.split(" ");
                DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                Date tDuration = timeFormat.parse(tempForTime[0]);
                long in = tDuration.getTime();
                tDuration = timeFormat.parse(tempForTime[1]);
                long out = tDuration.getTime();
                visitors.add(new Visitor(in, out));


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }





        int count = 0;
        int max = 0;
        int maxVissitors = 0;
        long timeIn = 0;
        long timeOut = 0;
        ArrayList<Long> times = fillTime(visitors);
        Collections.sort(times);
        TreeMap<Long, Integer> timesWhisMax = new TreeMap<Long, Integer>();
        for (Long time : times) {

            for (Visitor visitor : visitors) {
                if (time >= visitor.getIn() && time < visitor.getOut()) {
                    count++;
                    timesWhisMax.put(time, count);
                } else if ( time == visitor.getOut())
                    timesWhisMax.put(time, count);

            }

            if (count > max) {
                max = count;
            }
            count=0;
            maxVissitors = max;

        }
        int match = 0;
        for (Map.Entry<Long,Integer> entry : timesWhisMax.entrySet()){
            if(entry.getValue() == maxVissitors && match == 0){
                timeIn = entry.getKey();
                match++;
            }
            if ((entry.getValue() != maxVissitors) && match != 0) {
                timeOut = entry.getKey();
                System.out.print(new SimpleDateFormat("HH:mm").format(new Date(timeIn)));
                System.out.println(" " + new SimpleDateFormat("HH:mm").format(new Date(timeOut))+"/n");
                match = 0;
            }
        }
    }



    private static ArrayList<Long> fillTime(List<Visitor> visitors) {
        ArrayList<Long> timeSet = new ArrayList<Long>();
        for (Visitor element : visitors) {
            timeSet.add(element.getIn());
            timeSet.add(element.getOut());
        }
        return timeSet;
    }

}
class Visitor {

    private long in;

    private long out;

    public Visitor(long in, long out) {
        this.in = in;
        this.out = out;
    }

    public long getIn() {
        return in;
    }

    public long getOut() {
        return out;
    }
}

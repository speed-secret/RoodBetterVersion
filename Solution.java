package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.*;

/*
High LEVEL:
1. DRIVER Class
2. Trip Class
3. Solution Class
 */
public class Solution {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        //detect each drivers as well as each trip
        //put each driver as a new HashMap with key is: String
        //and its value is : Driver(class)
        File file = new File("/Users/Bill-Wang/IdeaProjects/RootCodeChallengeFinalVersion/source.txt");
        Scanner sc = new Scanner(file);
        List<String> midRes = new ArrayList<>();
        while (sc.hasNextLine()) {
            midRes.add(sc.nextLine());
        }
        List<String[]> res = new ArrayList<>();
        for (String str: midRes) {
            String[] splited = str.split("\\s+");
            res.add(splited);
        }
        //until now, I already parsed all the strings into the current List of String[];
        Map<String, Driver> driverMap = new HashMap<>();
        for (String[] strArray : res) {
            Driver curDriver = new Solution().getCurDriverStatus(Arrays.asList(strArray), driverMap);
            driverMap.put(curDriver.getName(), curDriver);
        }
        List<String> result = new Solution().getAllResult(driverMap);
        StringBuilder sb = new StringBuilder();
        while (result.iterator().hasNext()) {
            String str = result.iterator().next();
            sb.append(str).append("\n");
            result.remove(result.iterator().next());
        }
        System.out.println(sb.toString());
    }

    public static Driver getCurDriverStatus(List<String> str, Map<String, Driver> driverMap) throws ParseException {
        //assume: str != null && str.size() > 0;
        //assume there is no duplicated drivers
        //case 1:
        if (str.get(0).equals("Driver")) {
            Driver newDriver = new Driver(str.get(1), 0.0 ,0.0, 0.0);
            driverMap.put(str.get(1), newDriver);
            return newDriver;
        }
        //case 2:
        Trip curTrip = new Trip(new int[]{0, 0, 0, 0}, 0);
        String owner = str.get(1);
        String startTime = str.get(2);
        String endTime = str.get(3);
        String dis = str.get(4);
        double distance = Double.parseDouble(dis);
        //then I need to calculate time and speed;
        double curTime = curTrip.getTime(startTime, endTime);
        double curSpeed = curTrip.getCurSpeed(curTime, distance);
        double preSpeed = driverMap.get(owner).getAverageSpeed();
        double preTime = driverMap.get(owner).getPreTime();
        double sumOfDistance = driverMap.get(owner).getSumOfDistance() + distance;
        curSpeed = curSpeed * curTime / (preTime + curTime) + preSpeed * preTime / (preTime + curTime);
        //creat a new Driver with updated speed and distance
        Driver newDriver = new Driver(owner, sumOfDistance, curSpeed, preTime + curTime);
        return newDriver;
    }

    public static List<String> getAllResult(Map<String, Driver> map) {
        List<String> result = new ArrayList<>();
        PriorityQueue<Driver> maxHeap = new PriorityQueue<>(
                (a, b) -> (int) (b.getSumOfDistance() - a.getSumOfDistance()));
        for (Map.Entry<String, Driver> entry: map.entrySet()) {
            maxHeap.add(entry.getValue());
        }
        while (!maxHeap.isEmpty()) {
            Driver curDriver = maxHeap.poll();
            StringBuilder sb = new StringBuilder();
            sb.append(curDriver.getName() + ':' + ' ');
            if (curDriver.getSumOfDistance() > 0) {
                sb.append((int) Math.round(curDriver.getSumOfDistance())+ " miles" + ' ' + "@ ");
            } else {
                sb.append("0 miles");
            }
            if (curDriver.getAverageSpeed() > 0) {
                sb.append((int) Math.round(curDriver.getAverageSpeed()) + " mph" );
            }
            result.add(sb.toString());
        }
        return result;
    }
}

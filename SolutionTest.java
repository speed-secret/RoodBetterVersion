package Main;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    @Test
    void main() throws ParseException, FileNotFoundException {
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
        assertEquals("It is a successful build",
                "Lauren: 42 miles @ 34 mph" + "\n" +
                        "Dan: 39 miles @ 47 mph" + "\n" +
                        "Kumi: 0 miles" + "\n", sb.toString());
    }
}
import LoggerFactory.MyLogger;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class controll {
    public static void main(String[] args) throws IOException {
        int n = 5, h = 5, t = 5, N = 5, k = 0, MV = 5;
        String filePath = "./test.conf";
        File file = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String content;
        Pattern regex = Pattern.compile("n\\s*=\\s*([0-9]*)");
        content = bufferedReader.readLine();
        Matcher matcher = regex.matcher(content);
        if (matcher.find())
            n = Integer.parseInt(matcher.group(1));
        else {
            MyLogger.severe("Miss the Parameter n");
            System.exit(0);
        }

        regex = Pattern.compile("h\\s*=\\s*([0-9]*)");
        content = bufferedReader.readLine();
        matcher = regex.matcher(content);
        if (matcher.find())
            h = Integer.parseInt(matcher.group(1));
        else {
            MyLogger.severe("Miss the Parameter h");
            System.exit(0);
        }

        regex = Pattern.compile("t\\s*=\\s*([0-9]*)");
        content = bufferedReader.readLine();
        matcher = regex.matcher(content);
        if (matcher.find())
            t = Integer.parseInt(matcher.group(1));
        else {
            MyLogger.severe("Miss the Parameter t");
            System.exit(0);
        }

        regex = Pattern.compile("N\\s*=\\s*([0-9]*)");
        content = bufferedReader.readLine();
        matcher = regex.matcher(content);
        if (matcher.find())
            N = Integer.parseInt(matcher.group(1));
        else {
            MyLogger.severe("Miss the Parameter N");
            System.exit(0);
        }

        regex = Pattern.compile("k\\s*=\\s*([0-9]*)");
        content = bufferedReader.readLine();
        matcher = regex.matcher(content);
        if (matcher.find())
            k = Integer.parseInt(matcher.group(1));
        else {
            MyLogger.severe("Miss the Parameter k");
            System.exit(0);
        }

        regex = Pattern.compile("MV\\s*=\\s*([0-9]*)");
        content = bufferedReader.readLine();
        matcher = regex.matcher(content);
        if (matcher.find())
            MV = Integer.parseInt(matcher.group(1));
        else {
            MyLogger.severe("Miss the Parameter MV");
            System.exit(0);
        }

        bufferedReader.close();
        LadderGenerator.generateLadders(n, h);
        MonkeyGenerator monkeyGenerator = new MonkeyGenerator(t, N, k, MV);
        monkeyGenerator.generate();
    }
}

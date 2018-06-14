import java.io.*;

public class controll {
    public static void main(String[] args) throws IOException {
        int n, h, t, N, k, MV;
        String filePath = "./test.conf";
        File file = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String content;
        content = bufferedReader.readLine();
        String[] arguments = content.split(" ");
        n = Integer.parseInt(arguments[0]);
        h = Integer.parseInt(arguments[1]);
        t = Integer.parseInt(arguments[2]);
        N = Integer.parseInt(arguments[3]);
        k = Integer.parseInt(arguments[4]);
        MV = Integer.parseInt(arguments[5]);
        bufferedReader.close();
        LadderGenerator.generateLadders(n, h);
        MonkeyGenerator monkeyGenerator = new MonkeyGenerator(t, N, k, MV);
        monkeyGenerator.generate();
    }
}

package LoggerFactory;

import java.io.IOException;
import java.util.logging.*;

public class MyLogger {
    private static Logger myLogger = null;
    private static FileHandler fileHandler;

    private static void getLogger(String name, String FilePath) throws IOException {
        myLogger = Logger.getLogger(name);
        myLogger.setUseParentHandlers(true); // 设置此可以只在文件中输出，不在控制台打印

        // 设置日志输出的样式
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        LogRecord logRecord = new LogRecord(Level.INFO, "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %2$s%n%4$s: %5$s%n");
        simpleFormatter.format(logRecord);

        // 以文件的方式处理产生的日志
        fileHandler = new FileHandler(FilePath, 100000000, 1, true);  // 参数依次是 文件路径 一个文件的最大byte数，文件的个数，是否追加内容
        fileHandler.setFormatter(simpleFormatter);
        myLogger.addHandler(fileHandler);
    }

    /**
     * 对logger的info方法的封装
     *
     * @param message 需要info的信息
     */
    public static void info(String message) {
        try {
            if (myLogger == null)
                getLogger("Lab6", "./Lab.log");
            else
                myLogger.info(message + "\n");
        } catch (IOException ignored) {
        }
    }

    /**
     * 对logger的warning方法的封装
     *
     * @param message 需要warning的信息
     */
    public static void warning(String message) {
        try {
            if (myLogger == null)
                getLogger("Lab6", "./Lab.log");
            else
                myLogger.warning(message + "\n");
        } catch (IOException ignored) {
        }
    }

    /**
     * 对logger的severe方法的封装
     *
     * @param message 需要severe的信息
     */
    public static void severe(String message) {
        try {
            if (myLogger == null)
                getLogger("Lab6", "./Lab.log");
            else
                myLogger.severe(message + "\n");
        } catch (IOException ignored) {
        }
    }

    public static void closeLogger() {
        fileHandler.close();
    }

    public static String toString(Exception e) {
        StringBuilder StackTrace = new StringBuilder();
        for (StackTraceElement item : e.getStackTrace()) {
            StackTrace.append(item).append("\n");
        }
        return StackTrace.toString();
    }
}

package LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.*;

class LoggerFactory {
    /**
     * 返回一个可以满足自己要求的logger对象
     * 这个对象就是java.util.logging中的对象
     * 基于它进行了一层封装
     *
     * @param name     logger的唯一标识
     * @param FilePath 输出日志文件的路径
     * @return 满足自己要求的logger对象
     * @throws IOException 输入的日志路径不合法异常
     */
    static List<Object> getLogger(String name, String FilePath) throws IOException {
        Logger logger = Logger.getLogger(name);
        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(true); // 设置此可以只在文件中输出，不在控制台打印

        // 设置日志输出的样式
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        LogRecord logRecord = new LogRecord(Level.INFO, "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %2$s%n%4$s: %5$s%n");
        simpleFormatter.format(logRecord);

        // 以文件的方式处理产生的日志
        FileHandler fileHandler = new FileHandler(FilePath, 100000000, 1, true);  // 参数依次是 文件路径 一个文件的最大byte数，文件的个数，是否追加内容
        fileHandler.setFormatter(simpleFormatter);
        fileHandler.setLevel(Level.ALL);
        logger.addHandler(fileHandler);
        return new ArrayList<>(Arrays.asList(logger, fileHandler));
    }
}

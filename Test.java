import Agents.Consumer.TimeModel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Test {


@SneakyThrows
    public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
        TimeModel timeModel = new TimeModel();
        Thread.sleep(15000);
        log.info(Long.toString(timeModel.getHour()));
    }
}

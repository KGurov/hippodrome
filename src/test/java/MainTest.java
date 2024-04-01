import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {
    //Проверить, что метод выполняется не дольше 22 секунд. Для этого воспользуйся аннотацией Timeout. После написания этого теста,
    // отключи его (воспользуйся аннотацией Disabled). Так он не будет занимать время при запуске всех тестов,
    // а при необходимости его можно будет запустить вручную.
    @Disabled
    @Test
    @Timeout(22)
    void testTimeout() {
        try {
            Main.main(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

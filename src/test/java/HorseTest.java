import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    //Проверить, что при передаче в конструктор первым параметром null, будет выброшено IllegalArgumentException.
    // Для этого нужно воспользоваться методом assertThrows;

    //Проверить, что при передаче в конструктор первым параметром null, выброшенное исключение будет содержать сообщение "Name cannot be null.".
    //Для этого нужно получить сообщение из перехваченного исключения и воспользоваться методом assertEquals;
    @Test
    public void testNullException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 30, 10));
        assertEquals("Name cannot be null.", e.getMessage());
    }

    //Проверить, что при передаче в конструктор первым параметром пустой строки или строки содержащей только пробельные символы
    //(пробел, табуляция и т.д.), будет выброшено IllegalArgumentException. Чтобы выполнить проверку с разными вариантами пробельных
    //символов, нужно сделать тест параметризованным;
    //Проверить, что при передаче в конструктор первым параметром пустой строки или строки содержащей
    //только пробельные символы (пробел, табуляция и т.д.), выброшенное исключение будет содержать сообщение "Name cannot be blank.";

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t\t"})
    public void testBlankException(String horse) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Horse(horse, 30, 10));
        assertEquals("Name cannot be blank.", e.getMessage());
    }
    //Проверить, что при передаче в конструктор вторым параметром
    // отрицательного числа, будет выброшено IllegalArgumentException;

    //Проверить, что при передаче в конструктор вторым параметром
    // отрицательного числа, выброшенное исключение будет содержать сообщение "Speed cannot be negative.";
    @Test
    public void testNegativeSpeedParametersException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Horse", -30, 10));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }
    //Проверить, что при передаче в конструктор третьим параметром
    // отрицательного числа, будет выброшено IllegalArgumentException;

    //Проверить, что при передаче в конструктор третьим параметром
    // отрицательного числа, выброшенное исключение будет содержать сообщение "Distance cannot be negative.";
    @Test
    public void testNegativeDistanceParametersException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Horse", 30, -10));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    //    Проверить, что метод возвращает строку, которая была передана первым параметром в конструктор;
    @Test
    public void testGetNameReturnsString() {
        Horse horse = new Horse("Horse", 30, 10);
        assertEquals("Horse", horse.getName());
    }

    //    Проверить, что метод возвращает число, которое было передано вторым параметром в конструктор;конструктор
    @Test
    public void testGetSpeedReturnsValue() {
        Horse horse = new Horse("Horse", 30, 10);
        assertEquals(30, horse.getSpeed());
    }

    //    Проверить, что метод возвращает число, которое было передано третьим параметром в конструктор;
    @Test
    public void testGetDistanceReturnsValue() {
        Horse horse = new Horse("Horse", 30, 10);
        assertEquals(10, horse.getDistance());
    }

    //    Проверить, что метод возвращает ноль, если объект был создан с помощью конструктора с двумя параметрами;
    @Test
    public void testReturnsZeroDistance() {
        Horse horse = new Horse("Horse", 30);
        assertEquals(0, horse.getDistance());
    }

    //    Проверить, что метод вызывает внутри метод getRandomDouble с параметрами 0.2 и 0.9. Для этого нужно использовать MockedStatic и его метод verify;
    @Test
    public void testMoveGetRandom() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("Horse", 30, 10).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    //    Проверить, что метод присваивает дистанции значение высчитанное по формуле: distance + speed * getRandomDouble(0.2, 0.9).
    //    Для этого нужно замокать getRandomDouble,чтобы он возвращал определенные значения, которые нужно задать параметризовав тест.
    @Test
    public void testMoveCalculateParameters() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Horse", 30.0, 10.0);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(10.0);
            horse.move();
            double expectedDistance = 0 + 30 * 10;
            assertEquals(expectedDistance, horse.getDistance(), 10);
        }
    }
}

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {
    //    Проверить, что при передаче в конструктор null, будет выброшено IllegalArgumentException;
    //    Проверить, что при передаче в конструктор null, выброшенное исключение будет содержать сообщение "Horses cannot be null.";
    @Test
    public void testNullException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", e.getMessage());
    }

    //    Проверить, что при передаче в конструктор пустого списка, будет выброшено IllegalArgumentException;
    //    Проверить, что при передаче в конструктор пустого списка, выброшенное исключение будет содержать сообщение "Horses cannot be empty.";
    @Test
    public void testEmptyException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    //    Проверить, что метод возвращает список, который содержит те же объекты и в той же последовательности,
    //    что и список который был передан в конструктор. При создании объекта Hippodrome передай в конструктор список из 30 разных лошадей;
    @Test
    public void testGetHorse() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            horses.add(new Horse("" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    //    Проверить, что метод вызывает метод move у всех лошадей.
    //    При создании объекта Hippodrome передай в конструктор список из 50 моков лошадей и воспользуйся методом verify.
    @Test
    public void testMoveHorse() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        horses.forEach(horse -> Mockito.verify(horse, Mockito.times(1)).move());
    }

    //    Проверить, что метод возвращает лошадь с самым большим значением distance.
    @Test
    public void testReturnsHorseMaxValueDistance() {
        Horse horse1 = new Horse("Horse1", 1, 10);
        Horse horse2 = new Horse("Horse2", 1, 20);
        Horse horse3 = new Horse("Horse3", 1, 30);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));
        assertSame(horse3, hippodrome.getWinner());

    }
}


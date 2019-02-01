package ru.eremin.noteboard.unit;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.eremin.noteboard.api.TestUnit;

import static junit.framework.TestCase.assertTrue;

public class SimpleTest {
    @Test
    @Category(TestUnit.class)
    public void test() {
        assertTrue(true);
    }
}

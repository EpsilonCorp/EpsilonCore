package fr.epsilonmc.api.type;

import fr.epsilonmc.api.type.TypeConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTypeConverter {

    @Test
    @DisplayName("Test conversion between Class<? extends T>[] and T[]")
    public void testInitiateArray() {
        Class[] classes = new Class[]{TestType.class};
        List testTypes = TypeConverter.instantiateArray(classes);

        assertEquals(1, testTypes.size());
        assertTrue(testTypes.get(0) instanceof TestType);
    }

    public static class TestType {}

}

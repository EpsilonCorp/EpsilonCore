package fr.epsilonmc.api.type;

import lombok.SneakyThrows;

import java.lang.reflect.Array;

public class TypeConverter {

    @SneakyThrows
    public static <T> T[] instantiateArray(Class<? extends T>[] array) {
        T[] tArray = (T[]) Array.newInstance(array[0], array.length);

        for(int i = 0; i < array.length; i++) {
            tArray[i] = array[i].newInstance();
        }

        return tArray;
    }

}

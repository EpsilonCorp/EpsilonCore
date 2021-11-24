package fr.epsilonmc.api.type;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class TypeConverter {

    @SneakyThrows
    public static <T> List<T> instantiateArray(Class<? extends T>[] array) {
        List<T> tList = new ArrayList<>();

        for (Class<? extends T> aClass : array) {
            tList.add(aClass.newInstance());
        }

        return tList;
    }

}

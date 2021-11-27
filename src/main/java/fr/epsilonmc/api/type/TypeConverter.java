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

    public static String intToTime(int timeInSeconds) {
        int hours = timeInSeconds / 3_600;
        int minutes = (timeInSeconds - hours * 3_600) / 60;
        int seconds = timeInSeconds - hours * 3_600 - minutes * 60;

        String hoursTxt = hours + "h";
        String minutesTxt = minutes + "m";
        String secondsTxt = seconds + "s";

        return String.format(
                "%s%s%s",
                hours != 0 ? hoursTxt + " " : "",
                (hours != 0 || minutes != 0) ? minutesTxt + " et " : "",
                secondsTxt
        );
    }
}

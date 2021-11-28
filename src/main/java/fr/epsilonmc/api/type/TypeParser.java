package fr.epsilonmc.api.type;

public class TypeParser {

    public static int intTryParse(String txt, int defaultValue) {
        int parsed = defaultValue;
        try {
            parsed = Integer.parseInt(txt);
        } catch (NumberFormatException ignored) {}
        return parsed;
    }

}

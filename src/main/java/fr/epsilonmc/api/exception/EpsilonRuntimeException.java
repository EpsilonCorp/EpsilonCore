package fr.epsilonmc.api.exception;

public class EpsilonRuntimeException extends RuntimeException {

    public EpsilonRuntimeException(String text, Object... objects) {
        super(String.format(text, objects));
    }

}

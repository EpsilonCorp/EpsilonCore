package fr.epsilonmc.api.exception;

public class UnknownEpsilonCommandException extends EpsilonRuntimeException {

    public UnknownEpsilonCommandException(String text, Object... objects) {
        super(text, objects);
    }
}

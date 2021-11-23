package fr.epsilonmc.api.exception;

public class ModuleRegisterException extends EpsilonRuntimeException {

    public ModuleRegisterException(String text, Object... objects) {
        super(text, objects);
    }
}

package fr.epsilonmc.api.module;

import fr.epsilonmc.api.command.IEpsilonCommand;
import org.bukkit.event.Listener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EpsilonModule {

    String name();
    Class<? extends Listener>[] listeners() default {};
    Class<? extends IEpsilonCommand>[] commands() default {};

}

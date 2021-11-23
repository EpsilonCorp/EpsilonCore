package fr.epsilonmc.api.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CommandInfo {

    private final String name;
    private final String permission;
    private final String description;
    private final boolean playerOnly;

}

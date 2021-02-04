package com.epam.jwd.controller.command;

import com.epam.jwd.context.CommandType;
import com.epam.jwd.controller.command.impl.ErrorCommand;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Optional;

/**
 * {@link CommandFactory} class that returns needed {@link Command}.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

@Log4j2
public class CommandFactory {

    /**
     * Method that returns {@link Command}.
     *
     * @param command {@link String} const value
     * @return {@link Command} command
     */
    public static Command getCommand(final String command) {
        Optional<CommandType> optional =
                Arrays.stream(CommandType.values())
                        .filter(enumCommand -> enumCommand.getUrl().equals(command)).findFirst();
        if (optional.isPresent()) {
            log.info("Get command" + command);
            return optional.get().getCommand();
        }
        return new ErrorCommand();
    }
}

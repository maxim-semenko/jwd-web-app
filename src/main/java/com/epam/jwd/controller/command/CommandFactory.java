package com.epam.jwd.controller.command;

import com.epam.jwd.context.CommandType;
import com.epam.jwd.controller.command.impl.ErrorCommand;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Optional;

/**
 * {@link CommandFactory} CommandFactory class,
 * which return needed {@link Command} command.
 */

@Log4j2
public class CommandFactory {

    /**
     * Method return {@link Command} command
     *
     * @param url {@link String} const value
     * @return {@link Command} command
     */
    public static Command getCommand(final String url) {
        Optional<CommandType> optional =
                Arrays.stream(CommandType.values())
                        .filter(enumCommand -> enumCommand.getUrl().equals(url)).findFirst();
        if (optional.isPresent()) {
            log.info("Get page " + url);
            return optional.get().getCommand();
        }
        return new ErrorCommand();
    }
}

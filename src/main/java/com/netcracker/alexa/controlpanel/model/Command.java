package com.netcracker.alexa.controlpanel.model;

public class Command {

    private CommandType commandType;

    private String context;

    public Command() {
    }

    public Command(CommandType commandType) {
        this(commandType, "");
    }

    public Command(CommandType commandType, String context) {
        this.commandType = commandType;
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }
}

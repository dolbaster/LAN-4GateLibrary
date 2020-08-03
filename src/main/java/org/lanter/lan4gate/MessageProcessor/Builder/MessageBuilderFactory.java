package org.lanter.lan4gate.MessageProcessor.Builder;

public class MessageBuilderFactory {
    public static IMessageBuilder getBuilder(){
        return new JSONMessageBuilder();
    }
}

package org.lanter.lan4gate.MessageProcessor.Parser;

public class MessageParserFactory {
    public static IMessageParser getParser() {
        return new JSONParser();
    }
}

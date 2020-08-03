package org.lanter.lan4gate.MessageProcessor.Parser;

import org.lanter.lan4gate.Implementation.Messages.Fields.ClassFieldValuesList;
import org.lanter.lan4gate.Implementation.Messages.Responses.Notification;
import org.lanter.lan4gate.Implementation.Messages.Responses.Response;

public interface IMessageParser {
    ClassFieldValuesList getType();
    Response getResponse();
    Notification getNotification();
    boolean parse(String json);
}

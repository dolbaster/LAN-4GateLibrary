package org.lanter.lan4gate.Implementation.Messages.Bridge;

import org.lanter.lan4gate.Messages.Bridge.BridgeCommand;
import org.lanter.lan4gate.Messages.Bridge.BridgeFieldsList;
import org.lanter.lan4gate.Messages.Bridge.BridgeStatus;
import org.lanter.lan4gate.Messages.Bridge.IBridge;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

public class Bridge implements IBridge {
    private BridgeCommand mCommand;
    private int mLinkID;
    private String mIP;
    private int mPort;
    private ByteBuffer mData;
    private BridgeStatus mStatus;

    private final Set<BridgeFieldsList> mFields = new HashSet<>();

    private final Set<BridgeFieldsList> mMandatoryFieldsList = new HashSet<>();
    private final Set<BridgeFieldsList> mOptionalFieldsList = new HashSet<>();

    protected final void addMandatoryFields(BridgeFieldsList field) {
        mMandatoryFieldsList.add(field);
    }
    protected final void addOptionalFields(BridgeFieldsList field) {
        mOptionalFieldsList.add(field);
    }

    protected Bridge() {
        addMandatoryFields(BridgeFieldsList.Command);
        addMandatoryFields(BridgeFieldsList.LinkID);
    }
    @Override
    public Set<BridgeFieldsList> getMandatoryFields() {
        return mMandatoryFieldsList;
    }

    @Override
    public Set<BridgeFieldsList> getOptionalFields() {
        return mOptionalFieldsList;
    }

    @Override
    public Set<BridgeFieldsList> getCurrentFields() {
        return mFields;
    }

    @Override
    public boolean checkMandatoryFields() {
        return mFields.containsAll(mMandatoryFieldsList);
    }

    @Override
    public BridgeCommand getCommand() {
        return mCommand;
    }

    @Override
    public void setCommand(BridgeCommand command) {
        mCommand = command;
    }

    @Override
    public int getLinkID() {
        return mLinkID;
    }

    @Override
    public void setLinkID(int linkID) {
        mLinkID = linkID;
    }

    @Override
    public String getIP() {
        return mIP;
    }

    @Override
    public void setIP(String ip) {
        mIP = ip;
    }

    @Override
    public int getPort() {
        return mPort;
    }

    @Override
    public void setPort(int port) {
        mPort = port;
    }

    @Override
    public ByteBuffer getData() {
        return mData;
    }

    @Override
    public void setData(ByteBuffer data) {
        mData = data;
    }

    @Override
    public BridgeStatus getStatus() {
        return mStatus;
    }

    @Override
    public void setStatus(BridgeStatus status) {
        mStatus = status;
    }
}

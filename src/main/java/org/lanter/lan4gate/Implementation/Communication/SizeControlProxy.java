package org.lanter.lan4gate.Implementation.Communication;

import org.lanter.lan4gate.Communication.ICommunication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class SizeControlProxy implements ICommunication {
    private final int mSizeCharsCount = 4;


    private ICommunication mCommunication = null;

    public SizeControlProxy(ICommunication communication) {
        mCommunication = communication;
    }
    @Override
    public void openCommunication() throws IOException {
        if(mCommunication != null) {
            mCommunication.openCommunication();
        }
    }

    @Override
    public void closeCommunication() throws IOException {
        if(mCommunication != null) {
            mCommunication.closeCommunication();
        }
    }

    @Override
    public boolean isOpen() {
        boolean result = false;
        if(mCommunication != null) {
            result = mCommunication.isOpen();
        }
        return result;
    }

    @Override
    public boolean isConnected() {
        boolean result = false;
        if(mCommunication != null) {
            result = mCommunication.isConnected();
        }
        return result;
    }

    @Override
    public void sendData(ByteBuffer data) throws IOException {
        if(mCommunication != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(getSize(data).getBytes());
            stream.write(data.array());
            mCommunication.sendData(ByteBuffer.wrap(stream.toByteArray()));
        }
    }

    @Override
    public ByteBuffer getData() throws IOException {
        return null;
    }

    @Override
    public void doCommunication() throws IOException {
        if(mCommunication != null) {
            mCommunication.doCommunication();
        }
    }

    private String getSize(ByteBuffer buffer) {
        return String.format("%0" + mSizeCharsCount +"X", buffer.limit());
    }
}

package org.lanter.lan4gate.Implementation.Communication;

import org.lanter.lan4gate.Communication.ICommunication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SizeControlProxy implements ICommunication {

    private final ByteArrayOutputStream mCurrentDataStreamBuffer = new ByteArrayOutputStream();
    private final Queue<ByteBuffer> mReceivedData = new ConcurrentLinkedDeque<>();

    private int mMessageSize = -1;
    private final int mSizeCharsCount = 4;


    private final ICommunication mCommunication;

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
        if(mCommunication != null && data != null && data.limit() > 0) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(getSendSize(data).getBytes());
            stream.write(data.array());
            byte[] arr = stream.toByteArray();
            ByteBuffer buf = ByteBuffer.wrap(arr, 0, mSizeCharsCount + data.limit()).slice();
            mCommunication.sendData(buf);
        }
    }

    @Override
    public ByteBuffer getData() throws IOException {
        if(mCommunication != null) {
            ByteBuffer buffer = mCommunication.getData();
            if(buffer != null) {
                mCurrentDataStreamBuffer.write(buffer.array(), 0, buffer.limit());
            }
            if(mCurrentDataStreamBuffer.size() > 0) {
                if(mMessageSize < 0) {
                    mMessageSize = getReceiveSize(mCurrentDataStreamBuffer.toByteArray());
                }
                if (mMessageSize > 0) {
                    ByteBuffer message = getReceiveData(mCurrentDataStreamBuffer.toByteArray(), mMessageSize);
                    if(message != null) {
                        mReceivedData.offer(message);
                        sliceStream();
                        mMessageSize = -1;
                    }
                }
            }
        }
        return mReceivedData.poll();
    }

    @Override
    public void doCommunication() throws IOException {
        if(mCommunication != null) {
            mCommunication.doCommunication();
        }
    }

    private String getSendSize(ByteBuffer buffer) {
        return String.format("%0" + mSizeCharsCount +"X", buffer.limit());
    }

    private int getReceiveSize(byte[] buffer) {
        int result = -1;
        if(buffer.length >= mSizeCharsCount) {
            String size = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(buffer, 0, mSizeCharsCount)).toString();
            if(size.matches("[0-9a-fA-F]{" + mSizeCharsCount + "}")) {
                result = Integer.parseInt(size, 16);
            }
        }
        return result;
    }
    private ByteBuffer getReceiveData(byte[] data, int dataSize) {
        ByteBuffer result = null;
        if(data.length >= dataSize + mSizeCharsCount) {
            result = ByteBuffer.wrap(data, mSizeCharsCount, dataSize).slice();
        }
        return result;
    }

    private void sliceStream() {
        byte[] array = mCurrentDataStreamBuffer.toByteArray();
        mCurrentDataStreamBuffer.reset();
        int offset = mSizeCharsCount + mMessageSize;
        int len = array.length - mSizeCharsCount - mMessageSize;
        if(offset < array.length && len > 0) {
            mCurrentDataStreamBuffer.write(array, offset, len);
        }
    }
}


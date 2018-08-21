package com.js.worker.code.netty.client;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.js.worker.code.netty.server.RpcMessage;

public interface IConnection {

    /**
     * (flags != 1) synchronously (flags==1) asynchronously
     * 
     * @param flags
     * @return
     */
    public Object recv(Integer taskId, int flags);

    /**
     * In the new design, receive flow is through registerQueue, then push message into queue
     * 
     * @param recvQueu
     */
    public void registerRecvQueue(Integer taskId, BlockingQueue<Object> recvQueu);
    
    public void registerSendQueue(Integer taskId, BlockingQueue<Object> sendQueu);

    public void enqueue(Object message);

    public void send(List<Object> messages);

    public void send(Object message);

    public boolean available();
    
    public void setLogin();

    public void start() throws Exception;
    
    /**
     * close this connection
     */
    public void close();

    public boolean isClosed();
}

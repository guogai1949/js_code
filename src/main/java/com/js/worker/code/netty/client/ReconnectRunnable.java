package com.js.worker.code.netty.client;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReconnectRunnable implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(ReconnectRunnable.class);

    private BlockingQueue<NettyClient> queue = new LinkedBlockingDeque<NettyClient>();

    public void pushEvent(NettyClient client) {
        queue.offer(client);
    }

    private boolean closed = false;
    private Thread thread = null;

    
    public void run() {
        LOG.info("Successfully start reconnect thread");
        thread = Thread.currentThread();
        while (closed == false) {
            NettyClient client = null;
            try {
                client = queue.take();
            } catch (InterruptedException e) {
                continue;
            }
            if (client != null) {
                client.doReconnect();
            }

        }

        LOG.info("Successfully shutdown reconnect thread");
    }

    public void shutdown() {
        closed = true;
        thread.interrupt();
    }

}


package com.js.worker.code.callback.zkcurator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.PropertyConfigurator;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CuratorListenerTest1 {
	
	private static Logger LOG = LoggerFactory.getLogger(CuratorListenerTest1.class);
	
	public static void halt_process(int val, String msg) {
		LOG.info("Halting process: " + msg);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		if (val == 0) {
			// throw new RuntimeException(msg);
		} else {
			haltProcess(val);
		}
	}
	
	public static void haltProcess(int val) {
		Runtime.getRuntime().halt(val);
	}
	
    public static void main(String[] args) {
    	LOG.info(LOG.getClass().toString());
    	ExecutorService pool = Executors.newFixedThreadPool(2);
        CuratorFramework client = getClient();
        String path = "/demo";
        try {
            CuratorListener listener = new CuratorListener() {
                @Override
                public void eventReceived(CuratorFramework client, CuratorEvent e) throws Exception {
                    if (e.getType().equals(CuratorEventType.WATCHED)) {
                        WatchedEvent event = e.getWatchedEvent();
                        System.out.println("watched");
                        System.out.println(e);
                    }
                }
            };
            client.getCuratorListenable().addListener(listener);
            client.getUnhandledErrorListenable().addListener(new UnhandledErrorListener() {

			public void unhandledError(String msg, Throwable error) {
				String errmsg = "Unrecoverable Zookeeper error, halting process: " + msg;
				LOG.error(errmsg, error);
				halt_process(1, "Unrecoverable Zookeeper error");

			}
		});
            //创建node
//            client.create().withMode(CreateMode.PERSISTENT).forPath("/node3", "123456".getBytes());
            // 异步获取节点数据
            System.out.println("1111111");
            client.getData().inBackground().forPath(path);
            // 变更节点内容

            System.out.println("2222222");
            client.setData().forPath(path,"3435544666464".getBytes());
            //
            System.out.println("3333333");
            client.getData().inBackground().forPath(path);
            
            System.out.println("4444444");
            Thread.sleep(1000);
            System.out.println("sleep 10s");
            //
            client.getCuratorListenable().addListener(listener);
            client.setData().forPath(path,"3456".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
//            client.close();
        } finally {
//            client.close();
        }
    }
    private static CuratorFramework getClient(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.171.50:15560")
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .build();
        client.start();
        return client;
    }
}

package com.js.worker.code.callback.zkcurator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

public class CuratorListenerTest2 {
	
	
	public void createZkClent() {
		long start = System.currentTimeMillis();
	 	CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.161.28:2283,192.168.161.24:2283,192.168.161.25:2283", 
	 			5000, 3000, new ExponentialBackoffRetry(1000, 3));
//        CuratorFramework client = CuratorFrameworkFactory.builder()
//            .connectString("192.168.161.28:2283,192.168.161.24:2283,192.168.161.25:2283")
//            .sessionTimeoutMs(5000)
//            .connectionTimeoutMs(3000)
//            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
//            .build();
        client.start();
        System.out.println(System.currentTimeMillis() - start);
	}
	
	 public static void main(String[] args) throws Exception {
		 	long start = System.currentTimeMillis();
		 	CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.171.54:15560", 5000, 3000, new ExponentialBackoffRetry(1000, 3));
//	        CuratorFramework client = CuratorFrameworkFactory.builder()
//	            .connectString("192.168.161.28:2283,192.168.161.24:2283,192.168.161.25:2283")
//	            .sessionTimeoutMs(5000)
//	            .connectionTimeoutMs(3000)
//	            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
//	            .build();
	        client.start();
	        System.out.println(System.currentTimeMillis() - start);
//	        client.create()
//	            .creatingParentsIfNeeded()
//	            .forPath("/zk-huey/cnode", "hello".getBytes());
	        
	        /**
	         * 在注册监听器的时候，如果传入此参数，当事件触发时，逻辑由线程池处理
	         */
	        ExecutorService pool = Executors.newFixedThreadPool(2);
	        
	        /**
	         * 监听数据节点的变化情况
	         */
	        final NodeCache nodeCache = new NodeCache(client, "/jstorm/test", false);
	        nodeCache.start(true);
	        nodeCache.getListenable().addListener(
	            new NodeCacheListener() {
	                @Override
	                public void nodeChanged() throws Exception {
	                    System.out.println("Node data is changed, new data: " + 
	                        new String(nodeCache.getCurrentData().getData()));
	                    nodeCache.getCurrentData().getPath();
	                }
	            }, 
	            pool
	        );
	        
	        /**
	         * 监听子节点的变化情况
	         */
	        final PathChildrenCache childrenCache = new PathChildrenCache(client, "/jstorm/test", true);
	        childrenCache.start(StartMode.POST_INITIALIZED_EVENT);
	        childrenCache.getListenable().addListener(
	            new PathChildrenCacheListener() {
	                @Override
	                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event)
	                        throws Exception {
	                        switch (event.getType()) {
	                        case CHILD_ADDED:
	                            System.out.println("CHILD_ADDED: " + event.getData().getPath());
	                            break;
	                        case CHILD_REMOVED:
	                            System.out.println("CHILD_REMOVED: " + event.getData().getPath());
	                            break;
	                        case CHILD_UPDATED:
	                            System.out.println("CHILD_UPDATED: " + event.getData().getPath());
	                            break;
	                        default:
	                            break;
	                    }
	                }
	            },
	            pool
	        );
	        
	        client.setData().forPath("/jstorm/test", "world".getBytes());
	        Thread.sleep(10);
//	        client.create().forPath("/zk-huey/cnode1");
	        
	        Thread.sleep(10 * 1000);
	        client.getData().forPath("/jstorm/test");
//	        pool.shutdown();
//	        client.close();
	    }
}

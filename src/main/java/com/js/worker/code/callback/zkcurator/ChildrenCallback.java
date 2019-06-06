package com.js.worker.code.callback.zkcurator;

import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ChildrenCallback {

	private CuratorFramework getClient(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.171.54:15560")
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .build();
        client.start();
        return client;
    }
	
	public static void main(String[] args) throws Exception {
		CuratorFramework client = new ChildrenCallback().getClient();
		PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/jstorm/test", true);
		PathChildrenCacheListener childrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                ChildData data = event.getData();
                switch (event.getType()) {
                    case CHILD_ADDED:
                    	System.out.println("[TreeCache]节点增加, path="+ data.getPath()+ ",data=" + new String(data.getData()));
                    	String path = data.getPath();
                    	PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);
                		PathChildrenCacheListener childrenCacheListener = new PathChildrenCacheListener() {
                            @Override
                            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) {
                                ChildData data = event.getData();
                                switch (event.getType()) {
                                    case CHILD_ADDED:
                                    	System.out.println("[TreeCache]节点增加, path="+ data.getPath()+ ",data=" + new String(data.getData()));
                                        break;
                                    case CHILD_UPDATED:
                                    	System.out.println("[TreeCache]节点更新, path="+ data.getPath()+ ",data=" + new String(data.getData()));
                                        break;
                                    case CHILD_REMOVED:
                                    	System.out.println("============================="+ data.getData());
                                        break;
                                    default:
                                        break;
                                }
                            }
                        };
                        pathChildrenCache.getListenable().addListener(childrenCacheListener,Executors.newFixedThreadPool(2));
                        pathChildrenCache.start(StartMode.POST_INITIALIZED_EVENT);
                        break;
                    case CHILD_UPDATED:
                    	System.out.println("[TreeCache]节点更新, path="+ data.getPath()+ ",data=" + new String(data.getData()));
                        break;
                    case CHILD_REMOVED:
                    	System.out.println("============================="+ data.getData());
                        break;
                    default:
                        break;
                }
            }
        };
        pathChildrenCache.getListenable().addListener(childrenCacheListener,Executors.newFixedThreadPool(2));
        pathChildrenCache.start(StartMode.POST_INITIALIZED_EVENT);
        Thread.sleep(10000);
//	    client.create().creatingParentContainersIfNeeded().forPath("/jstorm/test/test1", "test".getBytes());
//        client.setData().forPath("/jstorm/test/test1","34355446664641212312".getBytes());
//        client.delete().forPath("/jstorm/test");
	}

}

package com.js.worker.code.callback.zkcurator;

import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class NodeCallBack {
	
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
		CuratorFramework client = new NodeCallBack().getClient();
		TreeCache treeCache = new TreeCache(client, "/jstorm/test");
		TreeCacheListener treeCacheListener = new TreeCacheListener() {

			@Override
			public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
				ChildData data = event.getData();
				String path = data.getPath();
                if(data != null){
                    switch (event.getType()) {
                        case NODE_ADDED:
                            System.out.println("[TreeCache]节点增加, path="+ data.getPath()+ ",data=" + new String(data.getData()));
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
                            break;
                        case NODE_UPDATED:
                        	System.out.println("[TreeCache]节点更新, path="+ data.getPath()+ ",data=" + new String(data.getData()));
                            break;
                        case NODE_REMOVED:
                        	System.out.println("============================="+ data.getData());
                            break;
                        default:
                            break;
                    }
                }else{
                	System.out.println("[TreeCache]节点数据为空, path=");
                }
				
			}

        };
        treeCache.getListenable().addListener(treeCacheListener, Executors.newFixedThreadPool(2));
        treeCache.start();
//	    client.create().creatingParentContainersIfNeeded().forPath("/jstorm/test/test1", "test".getBytes());
//        client.setData().forPath("/jstorm/test/test1","34355446664641212312".getBytes());
//        client.delete().forPath("/jstorm/test/test1");
		}
}

package com.js.worker.code.encache;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

public class Test {

	public static void main(String[] args) throws UnknownHostException {
//		CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder() 
//			    .withCache("preConfigured",
//			        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10))) 
//			    .build(); 
//			cacheManager.init(); 
//
//			Cache<Long, String> preConfigured =
//			    cacheManager.getCache("preConfigured", Long.class, String.class); 
//
//			Cache<Long, String> myCache = cacheManager.createCache("myCache", 
//			    CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)));
//
//			myCache.put(1L, "da one!"); 
//			String value = myCache.get(1L); 
//
//			cacheManager.removeCache("preConfigured"); 
//
//			cacheManager.close(); 
		InetAddress ia = InetAddress.getByName("1.2.3.4");
		System.out.println(Arrays.toString(ia.getAddress()));
	}

}

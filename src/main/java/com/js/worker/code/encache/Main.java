package com.js.worker.code.encache;

import java.time.Duration;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.ehcache.event.EventType;
import org.ehcache.xml.XmlConfiguration;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
        .with(CacheManagerBuilder.persistence("C:\\work\\test\\ehcache")) 
        .withCache("threeTieredCache",
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, String.class,
                ResourcePoolsBuilder.newResourcePoolsBuilder()
                    .heap(10, EntryUnit.ENTRIES)  //堆
                    .offheap(1, MemoryUnit.MB)    //堆外
                    .disk(20, MemoryUnit.GB)      //磁盘
                )
            .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(2)))
            .add(CacheEventListenerConfigurationBuilder
            	    .newEventListenerConfiguration(new CacheEventListener() {

						@Override
						public void onEvent(CacheEvent event) {
							System.out.println("==============================" + event.getType());
							System.out.println("==============================" + event.getSource());
							System.out.println("==============================" + event.getKey());
							System.out.println("==============================" + event.getOldValue());
							System.out.println("==============================" + event.getNewValue());
						}
            	    	
            	    }, EventType.EXPIRED, EventType.UPDATED,EventType.EVICTED,EventType.CREATED) 
            	    .unordered().synchronous())
        ).build(true);
        Cache<Integer, String> threeTieredCache = persistentCacheManager.getCache("threeTieredCache", Integer.class, String.class);

        //读
        for (int i=0;i<=2;i++){
            threeTieredCache.put(i, "$"+i);
        }
        
        //写
//        for (int i=0;i<=20;i++){
//            String value = threeTieredCache.get(i);
//            System.out.println("get at "+i+":"+value);
//        }
      String value1 = threeTieredCache.get(1);
      System.out.println("1==============get at "+1+":"+value1);
      Thread.sleep(1000);
      String value2 = threeTieredCache.get(1);
      System.out.println("2==============get at "+1+":"+value2);
      Thread.sleep(10000);
      String value3 = threeTieredCache.get(1);
      System.out.println("3==============get at "+1+":"+value3);
      

//        persistentCacheManager.close();
    	
//    	 //静态的org.ehcache.config.builders.CacheManagerBuilder.newCacheManagerBuilder方法，
//        // 返回一个org.ehcache.config.builders.CacheManagerBuilder实例
//        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
//                .withCache("preConfigured",
//                        //使用构建起定义一个名为preConfigured的Cache
//                        // 该cache在执行cacheManager.build()之后被实际的CacheManager实例所创建
//                        // 第一个String作为cache的别名。
//                        // 第二个参数org.ehcache.config.CacheConfiguration，被用来配置Cache
//                        // 我们在org.ehcache.config.builders.CacheConfigurationBuilder中
//                        // 使用静态的newCacheConfigurationBuilder()方法创建一个默认的配置文件
//                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
//                                ResourcePoolsBuilder.heap(100))
//                                //最后调用build()返回一个完整的实例，但是该实例并未初始化，CacheManager我们就能够使用了。
//                                .build())
//                //在使用CacheManager之前，我们先对其进行初始化。该初始化操作有一两种方式。
//                // 在CacheManager中采用CacheManager.init()进行初始化
//                // 或者是调用CacheManagerBuilder.build(boolean init)完成初始化操作，该参数是布尔型，设置为true。
//                .build(true);

//        Cache<Long, String> preConfigured
//                //通过别名，键类型，值类型我们可以在cacheManager中检索出一个Cache
//                // 举例来说，要获得第2步中声明的缓存，您需要它的别名=“preConfigured”，
//                // keyType=Long.class和valueType = String.class。
//                // 处于类型安全考虑，我们要求键和值类型都要传递。如果这些和我们期望的不同
//                // CacheManager将会抛出一个ClassCastException在应用程序生命周期的早期
//                // 这可以保护缓存免受随机类型的污染。
//                = cacheManager.getCache("preConfigured", Long.class, String.class);
//        //CacheManager能够被用于创建需要的Cache实例，就像第2步一样，它需要像CacheConfiguration一样通过别名检索
//        // 这个实例和完全初始化的缓存将通过CacheManager.getCache()接口返回和/或访问。
//        Cache<Long, String> myCache = cacheManager.createCache("myCache",
//                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
//                        ResourcePoolsBuilder.heap(100)).build());
//        //新添加的Cache能够通过键值对的形式被用于存储条目。put方法的第一个参数是键，第二个参数是值。
//        // 记住，键和值类型必须是与在cacheconfig容器中定义的类型相同的类型。
//        // 另外，键必须是惟一的，并且只与一个值相关联。
//        myCache.put(1L, "da one!");
//        //通过调用cache.get(key)方法，从缓存中检索值。它只需要一个参数，这个参数是键，然后返回与该键关联的值。
//        // 如果没有与该键相关联的值，则返回null。Cache将释放所有本地持有的临时资源(例如内存)。
//        // 对这个缓存的引用变得不可用。
//        String value = myCache.get(1L);
//
//        System.out.println("从myCache中取出的信息为：{}" + value);
//
//        //我们可以通过CacheManager.removeCache(String)移除一个给定的Cache,
//        // CacheManager不仅会删除它对Cache的引用，而且还会关闭它。
//        cacheManager.removeCache("preConfigured");
//        //为了释放一个CacheManager提供的所有临时资源(内存、线程、…)，您必须调用CacheManager.close()，
//        // 它将关闭所有在当时已知的缓存实例。
//        cacheManager.close();
    }

    private static String getStoragePath() {
        // TODO Auto-generated method stub
        return "d:";
    }

}

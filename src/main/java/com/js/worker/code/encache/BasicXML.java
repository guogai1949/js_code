package com.js.worker.code.encache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.xml.XmlConfiguration;
import org.slf4j.Logger;

import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManager;
import static org.slf4j.LoggerFactory.getLogger;

public class BasicXML {
  private static final Logger LOGGER = getLogger(BasicXML.class);

  public static void main(String[] args) throws InterruptedException {
    LOGGER.info("Creating cache manager via XML resource");
    Configuration xmlConfig = new XmlConfiguration(BasicXML.class.getResource("/ehcache.xml"));
    CacheManager cacheManager = newCacheManager(xmlConfig);
	cacheManager.init();
	
	Cache<Long, String> basicCache = cacheManager.getCache("foo", Long.class, String.class);
	
	LOGGER.info("Putting to cache");
	basicCache.put(1L, "da one!");
	String value1 = basicCache.get(1L);
	LOGGER.info("Retrieved '{}'", value1);
	Thread.sleep(10000);
	String value2 = basicCache.get(1L);
	LOGGER.info("Retrieved '{}'", value2);
	LOGGER.info("Closing cache manager");

    LOGGER.info("Exiting");
  }
}

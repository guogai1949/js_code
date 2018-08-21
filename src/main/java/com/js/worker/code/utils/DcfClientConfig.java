package com.js.worker.code.utils;

import java.util.Map;

public class DcfClientConfig {
	


	protected static final String STORM_MESSAGING_NETTY_CLIENT_WORKER_THREADS = "storm.messaging.netty.client_worker_threads";

	public static int getStormMessageNettyClientWorkerThreads(Map<String, Object> conf) {
		return Utils.parseInt(conf.get(STORM_MESSAGING_NETTY_CLIENT_WORKER_THREADS), 0);
	}

	protected static final String STORM_MESSAGING_NETTY_BUFFER_SIZE = "storm.messaging.netty.buffer_size";

	public static int getStormMessagingNettyBufferSize(Map<String, Object> conf) {
		return Utils.parseInt(conf.get(STORM_MESSAGING_NETTY_BUFFER_SIZE), 262144);
	}

	protected static final String STORM_MESSAGING_NETTY_MAX_RETRIES = "storm.messaging.netty.max_retries";

	public static int getStormMessagingNettyMaxRetries(Map<String, Object> conf) {
		return Utils.parseInt(conf.get(STORM_MESSAGING_NETTY_MAX_RETRIES), 30);
	}

	protected static final String STORM_MESSAGING_NETTY_MIN_SLEEP_MS = "storm.messaging.netty.min_wait_ms";

	public static int getStormMessagingNettyMinSleepMs(Map<String, Object> conf) {
		return Utils.parseInt(conf.get(STORM_MESSAGING_NETTY_MIN_SLEEP_MS), 100);
	}

	protected static final String STORM_MESSAGING_NETTY_MAX_SLEEP_MS = "storm.messaging.netty.max_wait_ms";

	public static int getStormMessagingNettyMaxSleepMs(Map<String, Object> conf) {
		return Utils.parseInt(conf.get(STORM_MESSAGING_NETTY_MAX_SLEEP_MS), 1000);
	}

	protected static final String STORM_NETTY_MESSAGE_BATCH_SIZE = "storm.messaging.netty.transfer.batch.size";

	public static int getStormNettyMessageBatchSize(Map<String, Object> conf) {
		return Utils.parseInt(conf.get(STORM_NETTY_MESSAGE_BATCH_SIZE), 262144);
	}

	protected static String NETTY_BUFFER_THRESHOLD_SIZE = "storm.messaging.netty.buffer.threshold";

	public static long getNettyBufferThresholdSize(Map<String, Object> conf) {
		return Utils.parseLong(conf.get(NETTY_BUFFER_THRESHOLD_SIZE), 8 * Utils.SIZE_1_M);
	}

	public static void setNettyBufferThresholdSize(Map<String, Object> conf, long size) {
		conf.put(NETTY_BUFFER_THRESHOLD_SIZE, size);
	}

	protected static String NETTY_PENDING_BUFFER_TIMEOUT = "storm.messaging.netty.pending.buffer.timeout";

	public static void setNettyPendingBufferTimeout(Map<String, Object> conf, Long timeout) {
		conf.put(NETTY_PENDING_BUFFER_TIMEOUT, timeout);
	}

	public static long getNettyPendingBufferTimeout(Map<String, Object> conf) {
		return Utils.parseLong(conf.get(NETTY_PENDING_BUFFER_TIMEOUT), 120 * 1000);
	}

	protected static String NETTY_MAX_SEND_PENDING = "storm.messaging.netty.max.pending";

	public static void setNettyMaxSendPending(Map<String, Object> conf, long pending) {
		conf.put(NETTY_MAX_SEND_PENDING, pending);
	}

	public static long getNettyMaxSendPending(Map<String, Object> conf) {
		return Utils.parseLong(conf.get(NETTY_MAX_SEND_PENDING), 32);
	}

	protected static String RATELIMITER_TYPE = "ratelimiter.type";

	public static void setRateLimiterType(Map<String, Object> conf, String type) {
		conf.put(RATELIMITER_TYPE, type);
	}

	public static String getRateLimiterType(Map<String, Object> conf) {
		return (String) conf.get(RATELIMITER_TYPE);
	}

	protected static String RATELIMITER_PERMITS_PERSECOND = "ratelimiter.permitsPerSecond";

	public static void setRateLimiterpermitsPerSecond(Map<String, Object> conf, double permitsPerSecond) {
		conf.put(RATELIMITER_PERMITS_PERSECOND, permitsPerSecond);
	}

	public static double getRateLimiterpermitsPerSecond(Map<String, Object> conf) {
		return (double) conf.get(RATELIMITER_PERMITS_PERSECOND);
	}



}

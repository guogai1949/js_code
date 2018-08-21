package com.js.worker.code.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);

    public static long SIZE_1_K = 1024;
    public static long SIZE_1_M = SIZE_1_K * 1024;
    public static long SIZE_1_G = SIZE_1_M * 1024;
    public static long SIZE_1_T = SIZE_1_G * 1024;
    public static long SIZE_1_P = SIZE_1_T * 1024;

    public static final int MIN_1 = 60;
    public static final int MIN_30 = MIN_1 * 30;
    public static final int HOUR_1 = MIN_30 * 2;
    public static final int DAY_1 = HOUR_1 * 24;

  
    public static Long parseLong(Object o) {
        if (o == null) {
            return null;
        }

        if (o instanceof String) {
            return Long.valueOf(String.valueOf(o));
        } else if (o instanceof Integer) {
            Integer value = (Integer) o;
            return Long.valueOf(value);
        } else if (o instanceof Long) {
            return (Long) o;
        } else {
            throw new RuntimeException("Invalid value " + o.getClass().getName() + " " + o);
        }
    }

    public static Double parseDouble(Object o) {
        if (o == null) {
            return null;
        }

        if (o instanceof String) {
            return Double.valueOf(String.valueOf(o));
        } else if (o instanceof Integer) {
            Number value = (Integer) o;
            return value.doubleValue();
        } else if (o instanceof Long) {
            Number value = (Long) o;
            return value.doubleValue();
        } else if (o instanceof Double) {
            return (Double) o;
        } else {
            throw new RuntimeException("Invalid value " + o.getClass().getName() + " " + o);
        }
    }

    public static Double parseDouble(Object o, double defaultValue) {
        if (o == null) {
            return defaultValue;
        }
        try {
            return parseDouble(o);
        } catch (Exception ignored) {
            return defaultValue;
        }
    }

    public static Long parseLong(Object o, long defaultValue) {

        if (o == null) {
            return defaultValue;
        }

        if (o instanceof String) {
            return Long.valueOf(String.valueOf(o));
        } else if (o instanceof Integer) {
            Integer value = (Integer) o;
            return Long.valueOf((Integer) value);
        } else if (o instanceof Long) {
            return (Long) o;
        } else {
            return defaultValue;
        }
    }

    public static Integer parseInt(Object o) {
        if (o == null) {
            return null;
        }

        if (o instanceof String) {
            return Integer.parseInt(String.valueOf(o));
        } else if (o instanceof Long) {
            long value = (Long) o;
            return (int) value;
        } else if (o instanceof Integer) {
            return (Integer) o;
        } else {
            throw new RuntimeException("Invalid value " + o.getClass().getName() + " " + o);
        }
    }

    public static Integer parseInt(Object o, int defaultValue) {
        if (o == null) {
            return defaultValue;
        }

        if (o instanceof String) {
            return Integer.parseInt(String.valueOf(o));
        } else if (o instanceof Long) {
            long value = (Long) o;
            return (int) value;
        } else if (o instanceof Integer) {
            return (Integer) o;
        } else {
            return defaultValue;
        }
    }

    public static Boolean parseBoolean(Object o) {
        if (o == null) {
            return null;
        }

        if (o instanceof String) {
            return Boolean.valueOf((String) o);
        } else if (o instanceof Boolean) {
            return (Boolean) o;
        } else {
            throw new RuntimeException("Invalid value " + o.getClass().getName() + " " + o);
        }
    }

    public static boolean parseBoolean(Object o, boolean defaultValue) {
        if (o == null) {
            return defaultValue;
        }

        if (o instanceof String) {
            return Boolean.valueOf((String) o);
        } else if (o instanceof Boolean) {
            return (Boolean) o;
        } else {
            return defaultValue;
        }
    }

    public static String formatSimpleDouble(Double value) {
        try {
            java.text.DecimalFormat form = new java.text.DecimalFormat("##0.000");
            String s = form.format(value);
            return s;
        } catch (Exception e) {
            return "0.000";
        }

    }

    public static double formatDoubleDecPoint2(Double value) {
        try {
            java.text.DecimalFormat form = new java.text.DecimalFormat("##.00");
            String s = form.format(value);
            return Double.valueOf(s);
        } catch (Exception e) {
            return 0.0;
        }
    }

    public static double formatDoubleDecPoint4(Double value) {
        try {
            java.text.DecimalFormat form = new java.text.DecimalFormat("###.0000");
            String s = form.format(value);
            return Double.valueOf(s);
        } catch (Exception e) {
            return 0.0;
        }
    }

    public static Double convertToDouble(Object value) {
        Double ret;

        if (value == null) {
            ret = null;
        } else {
            if (value instanceof Integer) {
                ret = ((Integer) value).doubleValue();
            } else if (value instanceof Long) {
                ret = ((Long) value).doubleValue();
            } else if (value instanceof Float) {
                ret = ((Float) value).doubleValue();
            } else if (value instanceof Double) {
                ret = (Double) value;
            } else {
                ret = null;
            }
        }

        return ret;
    }

    public static String formatValue(Object value) {
        if (value == null) {
            return "0";
        }

        if (value instanceof Long) {
            return String.valueOf((Long) value);
        } else if (value instanceof Double) {
            return formatSimpleDouble((Double) value);
        } else {
            return String.valueOf(value);
        }
    }

    public static void sleepMs(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        	LOG.error("InterruptedException:",e);
        }
    }

    public static void sleepNs(int ns) {
        try {
            Thread.sleep(0, ns);
        } catch (InterruptedException e) {

        }
    }

    public static String HEXES = "0123456789ABCDEF";

    public static String toPrintableString(byte[] buf) {
        if (buf == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (byte b : buf) {
            if (index % 10 == 0) {
                sb.append("\n");
            }
            index++;

            sb.append(HEXES.charAt((b & 0xF0) >> 4));
            sb.append(HEXES.charAt((b & 0x0F)));
            sb.append(" ");

        }

        return sb.toString();
    }

    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE);
        buffer.putLong(x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE);
        buffer.put(bytes);
        buffer.flip();// need flip
        return buffer.getLong();
    }

    public static long halfValueOfSum(long v1, long v2, boolean increment) {
        long ret = (v1 + v2) / 2;
        if (increment) {
            ret += (v1 + v2) % 2;
        }
        return ret;
    }
    
    public static String replaceWithEnv(String params){
		if(params == null ){
			return null;
		}else if(params.equals("")){
			return params;
		}
		String rst = params;
		String pattern = "(\\$\\(\\w+\\))";
		  // 创建 Pattern 对象
	      Pattern r = Pattern.compile(pattern);
	      // 现在创建 matcher 对象
	      Matcher m = r.matcher(params);
	      while (m.find( )) {
	    	  String  env = m.group(1);
	    	  String _env  = null;
			try {
				_env = System.getenv(env.substring(2, env.length()-1));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  rst =  rst.replace(env, _env== null ? "":_env);
	      } 
		return rst;
	}
    
    public static String[] replaceWithEnv(String[] params) {
    	if(params == null || params.length<1 ){
    		return params;
    	}
    	String[] rst =  new String[params.length];
    	for(int i = 0; i< params.length;i++){
    		if(params[i] == null || params[i].equals("") ){
    			rst[i]=params[i];
    			continue;
    		}
    		String pattern = "(\\$\\(\\w+\\))";
    		  // 创建 Pattern 对象
    	      Pattern r = Pattern.compile(pattern);
    	      // 现在创建 matcher 对象
    	      Matcher m = r.matcher(params[i]);
    	      while (m.find( )) {
    	    	  String  env = m.group(1);
    	    	  String _env = System.getenv(env.substring(2, env.length()-1));
    	    	  rst[i] =  params[i].replace(env, _env== null ? "":_env);
    	      }
    	      if(rst[i] == null){
    	    	  rst[i] = params[i];
    	      }
    	}
    	
    	return rst;
	}
    
    public static String ip() {
        String hostname = null;
        try {
            hostname = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            LOG.error("local_hostname", e);
        }
        return hostname;
    }

	public static int retry(int port) {
		return (int) (port + 200 + Math.random()*100);
	}
}

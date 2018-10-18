package com.js.worker.code;

public class Test7 {
	
	public static int bytesToInt(byte[] src, int offset) {  
        int value;    
        value = (int) ((src[offset] & 0xFF)   
                | ((src[offset+1] & 0xFF)<<8)   
                | ((src[offset+2] & 0xFF)<<16)   
                | ((src[offset+3] & 0xFF)<<24));  
        return value;  
    } 
	
    public static int bytesToInt1(byte[] src, int offset) {  
	    int value;    
	    value = (int) ( ((src[offset] & 0xFF)<<16)  
	            |((src[offset+1] & 0xFF)<<8)  
	            |(src[offset+2] & 0xFF));  
	    return value; 
    } 
	
	public static int bytesToInt2(byte[] src, int offset) {  
	    int value;    
	    value = (int) ( ((src[offset] & 0xFF)<<24)  
	            |((src[offset+1] & 0xFF)<<16)  
	            |((src[offset+2] & 0xFF)<<8)  
	            |(src[offset+3] & 0xFF));  
	    return value;  
	} 
	
	public static int bytesToInt3(byte[] src, int offset) {
		int value = 0;
		if(src == null) {
			 throw new NullPointerException();
		}else if(offset < 0 || offset >= src.length) {
			throw new IndexOutOfBoundsException();
		}
		for(int i=0; i<src.length; i++) {
			value |= (src[offset + i] & 0xFF) << (8 * (src.length - 1 -i));
			System.out.println(value);
		}
		return value;
	}
	
	public static byte[] intToBytes( int value )   
	{   
		byte[] src = new byte[3];  
	    src[0] = (byte) ((value>>16) & 0xFF);  
	    src[1] = (byte) ((value>>8)& 0xFF);  
	    src[2] = (byte) ((value)&0xFF);      
	    return src;    
	}  
	
	public static void main(String[] args) {
		byte[] res = new byte[2];
    	res[0] = 19;
    	res[1] = 0;
    	System.out.println(new String(res));
    	System.out.println(bytesToInt3(res,0));
    	byte[] ret = intToBytes(1);
    	System.out.println(ret[0]);
    	System.out.println(ret[1]);
    	System.out.println(ret[2]);
	}

}

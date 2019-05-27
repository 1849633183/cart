/*package com.zb.utill;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Tool {
	
	public static String time() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}
	public static String date() {
		return time().replace(":", "-");
	}
	public static int getIntRnd(int n) {
		return (int) (Math.random() * n);
	}
	
	*//**
	 * 计算两个时间相隔秒数 
	 * 参数格式"2018-06-10 16:54:24";
	 * 
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return 
	 * @throws ParseException
	 *//*
	public static int secondsOf(String startTime, String endTime) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = df.parse(startTime);
		Date endDate = df.parse(endTime);
		long a = startDate.getTime();
		long b = endDate.getTime();
		int c = (int)((b - a) / 1000);
		return c;
	}
	
	public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();  
        tmp.ensureCapacity(src.length() * 6);  
        for (i = 0; i < src.length(); i++) {  
            j = src.charAt(i);  
            if (Character.isDigit(j) || Character.isLowerCase(j)  
                    || Character.isUpperCase(j))  
                tmp.append(j);  
            else if (j < 256) {  
                tmp.append("%");  
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));  
            } else {  
                tmp.append("%u");  
                tmp.append(Integer.toString(j, 16));  
            }  
        }  
        return tmp.toString();  
    }
	
	
    public static String unescape(String src) {  
        StringBuffer tmp = new StringBuffer();  
        tmp.ensureCapacity(src.length());  
        int lastPos = 0, pos = 0;  
        char ch;  
        while (lastPos < src.length()) {  
            pos = src.indexOf("%", lastPos);  
            if (pos == lastPos) {  
                if (src.charAt(pos + 1) == 'u') {  
                    ch = (char) Integer.parseInt(src  
                            .substring(pos + 2, pos + 6), 16);  
                    tmp.append(ch);  
                    lastPos = pos + 6;  
                } else {  
                    ch = (char) Integer.parseInt(src  
                            .substring(pos + 1, pos + 3), 16);  
                    tmp.append(ch);  
                    lastPos = pos + 3;  
                }  
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));  
                    lastPos = src.length();  
                } else {
                    tmp.append(src.substring(lastPos, pos));  
                    lastPos = pos;  
                }
            }
        }
        return tmp.toString();  
    }

*//**
 * @Description: 将base64编码字符串转换为图片
 * @Author:
 * @CreateTime:
 * @param imgStr base64编码字符串
 * @param path 图片路径-具体到文件
 * @return
 *//*
public static boolean generateBase64ToFile(String imgStr, String path) {
    if (imgStr == null) return false;
    BASE64Decoder decoder = new BASE64Decoder();
    try {
       byte[] b = decoder.decodeBuffer(imgStr);
       for (int i = 0; i < b.length; ++i) {
          if (b[i] < 0) {
             b[i] += 256;
          }
       }
       OutputStream out = new FileOutputStream(path);
       out.write(b);
       out.flush();
       out.close();
       return true;
    } catch (Exception e) {
       return false;
    }
}

public static String imageToBase64(String imgFile) {
	// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	InputStream in = null;
	byte[] data = null;
	// 读取图片字节数组
	try {
		in = new FileInputStream(imgFile);
		
		data = new byte[in.available()];
		in.read(data);
		in.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	// 对字节数组Base64编码
	BASE64Encoder encoder = new BASE64Encoder();
	return encoder.encode(data);
}
}
*/
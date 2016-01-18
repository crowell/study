package com.mycompany.ssm.commons;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author JinBingBing
 *
 */
public class AdressToCity{
	public void getCity(int address) throws IOException{
			URL url = new URL("http://opendata.baidu.com/post/s?wd="+address);
			URLConnection hul = url.openConnection();
			InputStream is = hul.getInputStream();
			 byte[] by = new byte[1024];
		        int len = 0;
		        String result = null;
		        while((len=is.read(by))!=-1){
		            result += new String(by,0,len,"GBK");
		        }
		        try {
		            result=result.substring(result.indexOf("</em>：")+6,result.indexOf("</h3>"));
		        } catch (Exception e) {
		            result="对不起，您的输入有误";
		        }
	}
}
package com.im.constant;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ReadWriteFile {

	@Test
	public void read() {
		 Gson gson = new Gson();  
		 FileReader fr;
		try {
			
			fr = new FileReader(getClass().getResource("/com/im/constant/code.json").getPath());
			Map<String, Province> citys = gson.fromJson(fr, new TypeToken<Map<String, Province>>() {}.getType());
			pars(citys);
//			for(Entry<String,Province> en:citys.entrySet()){
//				System.out.println(en.getKey()+":"+en.getValue());
//				if(en.getValue() != null){
//					
//				}
//			}
		} catch (FileNotFoundException e1) {
			System.out.println(e1.toString());
		}
           
//         
//		String outpath = getClass().getResource("/com/im/constant/code2.json").getPath();
//		System.out.println(outpath);
//		InputStream in = null;
//
//		// 输出流
//		OutputStream out = null;
//
//		try {
//			in = getClass().getResourceAsStream("/com/im/constant/code.json");
//			out = new FileOutputStream(outpath);
//			byte[] buffer = new byte[1024];
//			while (true) {
//				System.out.println(new String(buffer));
//				int byteRead = in.read(buffer);
//				if (byteRead == -1)
//					break;
//				out.write(buffer, 0, buffer.length);
//			}
//			out.flush();
//		}catch (Exception ex) {
//			System.out.println(ex.toString());
//		} finally {
//			if (in != null)
//				try {
//					in.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			if (out != null) {
//				try {
//					out.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
	}
	
	private void pars(Map<String, Province>  citys){
		
		for(Entry<String,Province> en:citys.entrySet()){
			System.out.println(en.getKey()+":"+en.getValue().getName());
			if(en.getValue() != null){
				pars(en.getValue().getSub());
			}else{
				continue;
			}
		}
	}
	

	
	
}

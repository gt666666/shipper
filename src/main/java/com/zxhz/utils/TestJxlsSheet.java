package com.zxhz.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
public class TestJxlsSheet {
 
	
	public static class Foo {
 
		public String id;
		public String name;
		public int age;
 
		public Foo(String id, String name, int age) {
			this.id = id;
			this.name = name;
			this.age = age;
		}
 
	}
 
	public static void main(String args[]) throws IOException {
		List<Foo> foolist=new ArrayList<Foo>();
		foolist.add(new Foo("001","lee",11));
		foolist.add(new Foo("002","wang",14));
		foolist.add(new Foo("003","zhang",19));
		List<String> sheetNames= new ArrayList<String>();
		sheetNames.add("001");
		sheetNames.add("002");
		sheetNames.add("003");
		InputStream in = new FileInputStream("F:\\template.xlsx");
		OutputStream out = new FileOutputStream("F:\\test1.xlsx");
		Context context = new Context();
		context.putVar("foolist", foolist);
		context.putVar("sheetNames", sheetNames);
		JxlsHelper.getInstance().processTemplate(in, out, context);
	}
	
}

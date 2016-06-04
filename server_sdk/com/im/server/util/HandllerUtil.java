package com.im.server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.im.server.handler.IRequestHandler;

public class HandllerUtil {

	private static Map<Integer, IRequestHandler> handlers = new HashMap<Integer, IRequestHandler>();;

	public static IRequestHandler getIRequestHandler(Integer cmd) {
		return handlers.get(cmd);
	}

	static {
		System.out.println("load handler ===========================>>>>>>>>>>>");
		System.out.println("load handler ===========================>>>>>>>>>>>");
		System.out.println("load handler ===========================>>>>>>>>>>>");
		try {
			System.out.println("load handler of IRequestHandler");
			Class cls = IRequestHandler.class;
			List<String> packages = new ArrayList<String>();
			packages.add("com.im.server.handler");
		
			List<Class<?>> tocalClasses = new ArrayList<Class<?>>();
			for (String pk : packages) {
				tocalClasses.addAll(ClassUtil.getClasses(pk));
			}
			List<Class<?>> classes = new ArrayList<Class<?>>();
			for (Class<?> c : tocalClasses) {
				if (cls.isAssignableFrom(c) && !cls.equals(c)) {
					System.out.println("handler[ " + c.getName() + " ]");
					IRequestHandler instance = (IRequestHandler) c.newInstance();
					handlers.put(instance.getCmd(), instance);
				}
			}
			System.out.println("handler size[ " + handlers.size() + " ]");
			for (Class<?> c : classes) {
				System.out.println(c.getName());
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("load handler ===========================<<<<<<<<<<<<<");
		System.out.println("load handler ===========================<<<<<<<<<<<<<");
		System.out.println("load handler ===========================<<<<<<<<<<<<<");
	}

	@Test
	public void test2() {
		try {
			System.out.println("load handler of IRequestHandler");
			Class cls = IRequestHandler.class;
			List<String> packages = new ArrayList<String>();
			packages.add("com.im.server.handler");
			List<Class<?>> tocalClasses = new ArrayList<Class<?>>();
			for (String pk : packages) {
				tocalClasses.addAll(ClassUtil.getClasses(pk));
			}
			List<Class<?>> classes = new ArrayList<Class<?>>();
			for (Class<?> c : tocalClasses) {
				if (cls.isAssignableFrom(c) && !cls.equals(c)) {
					System.out.println("handler[ " + c.getName() + " ]");
					IRequestHandler instance = (IRequestHandler) c.newInstance();
					handlers.put(instance.getCmd(), instance);
				}
			}
			System.out.println("handler size[ " + handlers.size() + " ]");
			for (Class<?> c : classes) {
				System.out.println(c.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}

}

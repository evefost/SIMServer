package com.im.server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.im.server.core.ProtocolHandler;

/**
 * 协议加载器
 * @author xieyang
 *
 */
public class ProtocolHandllerLoader {

	private static Map<Integer, ProtocolHandler> handlers = new HashMap<Integer, ProtocolHandler>();;

	public static ProtocolHandler getProtocolHandler(Integer cmd) {
		return handlers.get(cmd);
	}

	static {
		System.out.println("load handler ===========================>>>>>>>>>>>");
		System.out.println("load handler ===========================>>>>>>>>>>>");
		System.out.println("load handler ===========================>>>>>>>>>>>");
		try {
			System.out.println("load handler of ServerHandler");
			Class cls = ProtocolHandler.class;
			List<String> packages = new ArrayList<String>();
			packages.add("com.im.protocol.handler");
			List<Class<?>> tocalClasses = new ArrayList<Class<?>>();
			for (String pk : packages) {
				tocalClasses.addAll(ClassUtil.getClasses(pk));
			}
			List<Class<?>> classes = new ArrayList<Class<?>>();
			for (Class<?> c : tocalClasses) {
				if (cls.isAssignableFrom(c) && !cls.equals(c)) {
					System.out.println("handler[ " + c.getName() + " ]");
					ProtocolHandler instance = (ProtocolHandler) c.newInstance();
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
			System.out.println("load handler of ServerHandler");
			Class cls = ProtocolHandler.class;
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
					ProtocolHandler instance = (ProtocolHandler) c.newInstance();
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

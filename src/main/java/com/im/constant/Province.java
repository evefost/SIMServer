package com.im.constant;

import java.util.Map;

public  class Province{
		private String name;
		private Map<String,Province>sub;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Map<String, Province> getSub() {
			return sub;
		}
		public void setSub(Map<String, Province> sub) {
			this.sub = sub;
		}
	
		
	}
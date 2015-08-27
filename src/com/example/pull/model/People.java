/**
 * 
 */
package com.example.pull.model;

/**
 * @author linweihao
 *
 */
public class People {
	
	public double dis;
	public P obj;
	public double getDis() {
		return dis;
	}
	public void setDis(double dis) {
		this.dis = dis;
	}
	public P getObj() {
		return obj;
	}
	public void setObj(P obj) {
		this.obj = obj;
	}
	
	public class P {
		
		public String name;
		public String category;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		
	}

	
}


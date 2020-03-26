package org.onlineSubmit.entity;

import org.springframework.stereotype.Component;

@Component
public class Nav {
	int nav_id;
	String nav_name;
	public int getNav_id() {
		return nav_id;
	}
	public void setNav_id(int nav_id) {
		this.nav_id = nav_id;
	}
	public String getNav_name() {
		return nav_name;
	}
	public void setNav_name(String nav_name) {
		this.nav_name = nav_name;
	}
	
}

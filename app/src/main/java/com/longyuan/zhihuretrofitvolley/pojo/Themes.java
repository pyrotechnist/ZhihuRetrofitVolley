package com.longyuan.zhihuretrofitvolley.pojo;

import java.util.List;

public class Themes{
	private List<Object> subscribed;
	private int limit;
	private List<ThemeItem> others;

	public void setSubscribed(List<Object> subscribed){
		this.subscribed = subscribed;
	}

	public List<Object> getSubscribed(){
		return subscribed;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	public void setOthers(List<ThemeItem> others){
		this.others = others;
	}

	public List<ThemeItem> getOthers(){
		return others;
	}

	@Override
 	public String toString(){
		return 
			"Themes{" + 
			"subscribed = '" + subscribed + '\'' + 
			",limit = '" + limit + '\'' + 
			",others = '" + others + '\'' + 
			"}";
		}
}
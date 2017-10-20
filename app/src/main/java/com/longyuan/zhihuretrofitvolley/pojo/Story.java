package com.longyuan.zhihuretrofitvolley.pojo;

import java.util.List;

public class Story {
	private List<String> images;
	private String gaPrefix;
	private String id;
	private int type;
	private String title;

	public void setImages(List<String> images){
		this.images = images;
	}

	public List<String> getImages(){
		return images;
	}

	public void setGaPrefix(String gaPrefix){
		this.gaPrefix = gaPrefix;
	}

	public String getGaPrefix(){
		return gaPrefix;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setType(int type){
		this.type = type;
	}

	public int getType(){
		return type;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"Story{" +
			"images = '" + images + '\'' + 
			",ga_prefix = '" + gaPrefix + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}
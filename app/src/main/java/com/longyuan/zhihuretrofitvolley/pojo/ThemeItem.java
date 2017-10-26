package com.longyuan.zhihuretrofitvolley.pojo;

public class ThemeItem {
	private String thumbnail;
	private int color;
	private String name;
	private String description;
	private int id;

	public void setThumbnail(String thumbnail){
		this.thumbnail = thumbnail;
	}

	public String getThumbnail(){
		return thumbnail;
	}

	public void setColor(int color){
		this.color = color;
	}

	public int getColor(){
		return color;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"ThemeItem{" +
			"thumbnail = '" + thumbnail + '\'' + 
			",color = '" + color + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}

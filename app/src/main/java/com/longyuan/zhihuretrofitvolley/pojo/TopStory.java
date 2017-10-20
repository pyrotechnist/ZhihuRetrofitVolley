package com.longyuan.zhihuretrofitvolley.pojo;

public class TopStory {
	private String image;
	private String gaPrefix;
	private int id;
	private int type;
	private String title;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setGaPrefix(String gaPrefix){
		this.gaPrefix = gaPrefix;
	}

	public String getGaPrefix(){
		return gaPrefix;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
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
			"TopStory{" +
			"image = '" + image + '\'' + 
			",ga_prefix = '" + gaPrefix + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}

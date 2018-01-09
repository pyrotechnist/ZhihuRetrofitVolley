package com.longyuan.zhihuretrofitvolley.pojo;

public class EditorsItem{
	private String name;
	private String bio;
	private int id;
	private String avatar;
	private String url;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setBio(String bio){
		this.bio = bio;
	}

	public String getBio(){
		return bio;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getAvatar(){
		return avatar;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"EditorsItem{" + 
			"name = '" + name + '\'' + 
			",bio = '" + bio + '\'' + 
			",id = '" + id + '\'' + 
			",avatar = '" + avatar + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}

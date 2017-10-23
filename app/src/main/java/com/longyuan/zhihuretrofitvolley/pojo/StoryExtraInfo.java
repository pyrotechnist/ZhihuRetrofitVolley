package com.longyuan.zhihuretrofitvolley.pojo;

public class StoryExtraInfo{
	private String comments;
	private String popularity;
	private String shortComments;
	private String longComments;

	public void setComments(String comments){
		this.comments = comments;
	}

	public String getComments(){
		return comments;
	}

	public void setPopularity(String popularity){
		this.popularity = popularity;
	}

	public String getPopularity(){
		return popularity;
	}

	public void setShortComments(String shortComments){
		this.shortComments = shortComments;
	}

	public String getShortComments(){
		return shortComments;
	}

	public void setLongComments(String longComments){
		this.longComments = longComments;
	}

	public String getLongComments(){
		return longComments;
	}

	@Override
 	public String toString(){
		return 
			"StoryExtraInfo{" + 
			"comments = '" + comments + '\'' + 
			",popularity = '" + popularity + '\'' + 
			",short_comments = '" + shortComments + '\'' + 
			",long_comments = '" + longComments + '\'' + 
			"}";
		}
}

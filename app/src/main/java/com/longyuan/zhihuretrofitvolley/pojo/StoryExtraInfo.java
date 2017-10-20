package com.longyuan.zhihuretrofitvolley.pojo;

public class StoryExtraInfo{
	private int comments;
	private int popularity;
	private int shortComments;
	private int longComments;

	public void setComments(int comments){
		this.comments = comments;
	}

	public int getComments(){
		return comments;
	}

	public void setPopularity(int popularity){
		this.popularity = popularity;
	}

	public int getPopularity(){
		return popularity;
	}

	public void setShortComments(int shortComments){
		this.shortComments = shortComments;
	}

	public int getShortComments(){
		return shortComments;
	}

	public void setLongComments(int longComments){
		this.longComments = longComments;
	}

	public int getLongComments(){
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

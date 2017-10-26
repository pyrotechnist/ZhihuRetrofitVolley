package com.longyuan.zhihuretrofitvolley.pojo;

public class CommentItem {
	private String author;
	private String avatar;
	private Long time;
	private int id;
	private String content;
	private String likes;

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getAvatar(){
		return avatar;
	}

	public void setTime(Long time){
		this.time = time;
	}

	public Long getTime(){
		return time;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setLikes(String likes){
		this.likes = likes;
	}

	public String getLikes(){
		return likes;
	}

	@Override
 	public String toString(){
		return 
			"CommentItem{" +
			"author = '" + author + '\'' + 
			",avatar = '" + avatar + '\'' + 
			",time = '" + time + '\'' + 
			",id = '" + id + '\'' + 
			",content = '" + content + '\'' + 
			",likes = '" + likes + '\'' + 
			"}";
		}
}

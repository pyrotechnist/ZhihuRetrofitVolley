package com.longyuan.zhihuretrofitvolley.pojo;

import java.util.List;

public class ShortComments{
	private List<CommentItem> comments;

	public void setComments(List<CommentItem> comments){
		this.comments = comments;
	}

	public List<CommentItem> getComments(){
		return comments;
	}

	@Override
 	public String toString(){
		return 
			"ShortComments{" + 
			"comments = '" + comments + '\'' + 
			"}";
		}
}
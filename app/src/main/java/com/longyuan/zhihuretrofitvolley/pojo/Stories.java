package com.longyuan.zhihuretrofitvolley.pojo;

import java.util.List;

public class Stories{
	private String date;
	private List<TopStory> topStories;
	private List<Story> stories;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setTopStories(List<TopStory> topStories){
		this.topStories = topStories;
	}

	public List<TopStory> getTopStories(){
		return topStories;
	}

	public void setStories(List<Story> stories){
		this.stories = stories;
	}

	public List<Story> getStories(){
		return stories;
	}

	@Override
 	public String toString(){
		return 
			"Stories{" + 
			"date = '" + date + '\'' + 
			",top_stories = '" + topStories + '\'' + 
			",stories = '" + stories + '\'' + 
			"}";
		}
}
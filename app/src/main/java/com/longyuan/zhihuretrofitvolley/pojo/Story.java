package com.longyuan.zhihuretrofitvolley.pojo;

import java.util.List;

public class Story extends StoryBase{
	private List<String> images;

	public void setImages(List<String> images){
		this.images = images;
	}

	public List<String> getImages(){
		return images;
	}

	@Override
 	public String toString(){
		return 
			"Story{" +
			"images = '" + images + '\'' + 
			",ga_prefix = '" + super.getGaPrefix() + '\'' +
			",id = '" + super.getId() + '\'' +
			",type = '" + super.getType() + '\'' +
			",title = '" + super.getTitle() + '\'' +
			"}";
		}
}
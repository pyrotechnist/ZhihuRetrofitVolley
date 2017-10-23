package com.longyuan.zhihuretrofitvolley.pojo;

public class TopStory extends StoryBase{

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	private String image;

	@Override
 	public String toString(){
		return 
			"TopStory{" +
			"image = '" + image + '\'' + 
			",ga_prefix = '" + super.getGaPrefix() + '\'' +
			",id = '" + super.getId() + '\'' +
			",type = '" + super.getType() + '\'' +
			",title = '" + super.getTitle() + '\'' +
			"}";
		}
}

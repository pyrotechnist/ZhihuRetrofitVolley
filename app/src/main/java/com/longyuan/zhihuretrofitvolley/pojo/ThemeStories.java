package com.longyuan.zhihuretrofitvolley.pojo;

import java.util.List;

public class ThemeStories{
	private String image;
	private List<Story> stories;
	private int color;
	private String background;
	private String name;
	private String description;
	private List<EditorsItem> editors;
	private String imageSource;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setStories(List<Story> stories){
		this.stories = stories;
	}

	public List<Story> getStories(){
		return stories;
	}

	public void setColor(int color){
		this.color = color;
	}

	public int getColor(){
		return color;
	}

	public void setBackground(String background){
		this.background = background;
	}

	public String getBackground(){
		return background;
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

	public void setEditors(List<EditorsItem> editors){
		this.editors = editors;
	}

	public List<EditorsItem> getEditors(){
		return editors;
	}

	public void setImageSource(String imageSource){
		this.imageSource = imageSource;
	}

	public String getImageSource(){
		return imageSource;
	}

	@Override
 	public String toString(){
		return 
			"ThemeStories{" + 
			"image = '" + image + '\'' + 
			",stories = '" + stories + '\'' + 
			",color = '" + color + '\'' + 
			",background = '" + background + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",editors = '" + editors + '\'' + 
			",image_source = '" + imageSource + '\'' + 
			"}";
		}
}
package com.longyuan.zhihuretrofitvolley.pojo;

import java.util.List;

public class StoryDetail {

	private String image;
	private List<String> images;
	private List<String> css;
	private String shareUrl;
	private String gaPrefix;
	private List<Object> js;
	private int id;
	private String body;
	private String title;
	private int type;
	private String imageSource;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setImages(List<String> images){
		this.images = images;
	}

	public List<String> getImages(){
		return images;
	}

	public void setCss(List<String> css){
		this.css = css;
	}

	public List<String> getCss(){
		return css;
	}

	public void setShareUrl(String shareUrl){
		this.shareUrl = shareUrl;
	}

	public String getShareUrl(){
		return shareUrl;
	}

	public void setGaPrefix(String gaPrefix){
		this.gaPrefix = gaPrefix;
	}

	public String getGaPrefix(){
		return gaPrefix;
	}

	public void setJs(List<Object> js){
		this.js = js;
	}

	public List<Object> getJs(){
		return js;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setBody(String body){
		this.body = body;
	}

	public String getBody(){
		return body;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setType(int type){
		this.type = type;
	}

	public int getType(){
		return type;
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
			"StoryDetail{" +
			"image = '" + image + '\'' + 
			",images = '" + images + '\'' + 
			",css = '" + css + '\'' + 
			",share_url = '" + shareUrl + '\'' + 
			",ga_prefix = '" + gaPrefix + '\'' + 
			",js = '" + js + '\'' + 
			",id = '" + id + '\'' + 
			",body = '" + body + '\'' + 
			",title = '" + title + '\'' + 
			",type = '" + type + '\'' + 
			",image_source = '" + imageSource + '\'' + 
			"}";
		}
}
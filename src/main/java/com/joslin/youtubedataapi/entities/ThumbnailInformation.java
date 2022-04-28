package com.joslin.youtubedataapi.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "thumbnail") 
public class ThumbnailInformation {
	
	@Id
	private String videoId;
	private String type;
	private Long height;
	private String url;
	private Long width;
	
	@ManyToOne
	private VideoInformation videoInformation;
	
	public ThumbnailInformation() {

	}

	public ThumbnailInformation(String videoId, String type, Long height, String url, Long width) {
		this.videoId = videoId;
		this.type = type;
		this.height = height;
		this.url = url;
		this.width = width;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getWidth() {
		return width;
	}

	public void setWidth(Long width) {
		this.width = width;
	}

}

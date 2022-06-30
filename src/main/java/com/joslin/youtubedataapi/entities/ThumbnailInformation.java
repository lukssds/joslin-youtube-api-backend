package com.joslin.youtubedataapi.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "thumbnail") 
public class ThumbnailInformation {
	
	@Id
	private String url;

	private String videoId;
	private String type;
	private Long height;
	private Long width;
	
	@ManyToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
	@JoinColumn(name="videoInformation")
	private VideoInformation videoInformation;
	
	public ThumbnailInformation() {

	}

	public ThumbnailInformation(String type, String videoId, Long height, String url, Long width, VideoInformation videoInformation) {
		this.type = type;
		this.height = height;
		this.url = url;
		this.width = width;
		this.videoInformation = videoInformation;
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

	public VideoInformation getVideoInformation() {
		return videoInformation;
	}

	public void setVideoInformation(VideoInformation videoInformation) {
		this.videoInformation = videoInformation;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

}

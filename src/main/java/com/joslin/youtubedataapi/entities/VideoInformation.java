package com.joslin.youtubedataapi.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "video") 
public class VideoInformation {
	
	@Id
	private String id;
	private String title;
	private String description;
	private String playlist;
	private String embedHtml;
	
	@OneToMany(mappedBy = "videoInformation")
	private List<ThumbnailInformation> thumbnail;
	
	public VideoInformation() {
		
	}

	public VideoInformation(String id, String title, String description, String playlist, String embedHtml, List<ThumbnailInformation> thumbnail) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.playlist = playlist;
		this.embedHtml = embedHtml;
		this.thumbnail = thumbnail;
	}

	public String getPlaylist() {
		return playlist;
	}

	public void setPlaylist(String playlist) {
		this.playlist = playlist;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmbedHtml() {
		return embedHtml;
	}

	public void setEmbedHtml(String embedHtml) {
		this.embedHtml = embedHtml;
	}

	public List<ThumbnailInformation> getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(List<ThumbnailInformation> thumbnail) {
		this.thumbnail = thumbnail;
	}

}

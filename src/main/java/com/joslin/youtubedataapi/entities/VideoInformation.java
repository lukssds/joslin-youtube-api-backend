package com.joslin.youtubedataapi.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "video") 
public class VideoInformation {
	
	@Id
	private String id;
	private String title;
	
	@Column(length=10485760)
	private String description;
	private String playlist;
	
	@OneToMany(mappedBy = "videoInformation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ThumbnailInformation> thumbnail;
	
	public VideoInformation() {
		
	}

	public VideoInformation(String id, String title, String description, String playlist, List<ThumbnailInformation> thumbnail) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.playlist = playlist;
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

	public List<ThumbnailInformation> getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(List<ThumbnailInformation> thumbnail) {
		this.thumbnail = thumbnail;
	}

}

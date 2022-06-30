package com.joslin.youtubedataapi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

public class Playlist {
	
	List<VideoInformation> videoInformationList = new ArrayList<VideoInformation>();
	
	public Playlist() {

	}

	public Playlist(List<VideoInformation> videoInformationList) {
		this.videoInformationList = videoInformationList;
	}

	public List<VideoInformation> getVideoInformationList() {
		return videoInformationList;
	}

	public void setVideoInformationList(List<VideoInformation> videoInformationList) {
		this.videoInformationList = videoInformationList;
	}

}

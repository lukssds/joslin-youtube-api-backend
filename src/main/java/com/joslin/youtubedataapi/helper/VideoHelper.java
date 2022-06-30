package com.joslin.youtubedataapi.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.Thumbnail;
import com.joslin.youtubedataapi.entities.ThumbnailInformation;
import com.joslin.youtubedataapi.entities.VideoInformation;

public class VideoHelper {
	
    public static List<VideoInformation> buildVideoInformationList(List<PlaylistItem> videos, String playlistId) {
        List<VideoInformation> videoInformationList = new ArrayList<VideoInformation>();
        
        for(PlaylistItem video : videos) {
    		VideoInformation videoInformation = new VideoInformation();
    		videoInformation.setDescription(video.getSnippet().getDescription());
    		videoInformation.setId(video.getContentDetails().getVideoId());
    		videoInformation.setPlaylist(playlistId);
    		videoInformation.setThumbnail(buildThumbnails(video.getContentDetails().getVideoId(), video.getSnippet().getThumbnails()));
    		videoInformation.setTitle(video.getSnippet().getTitle());
    		
    		videoInformationList.add(videoInformation);
        }
        
        return videoInformationList;
    }
	
	public static List<ThumbnailInformation> buildThumbnails(String videoId, Map<String, Thumbnail> thumbList) {
		List<ThumbnailInformation> thumbnailInformations = new ArrayList<ThumbnailInformation>();
		
		for (Map.Entry<String,Thumbnail> thumb : thumbList.entrySet()) {
			ThumbnailInformation thumbnailInformation = new ThumbnailInformation();
			thumbnailInformation.setHeight(thumb.getValue().getHeight());
			thumbnailInformation.setVideoId(videoId);
			thumbnailInformation.setWidth(thumb.getValue().getWidth());
			thumbnailInformation.setUrl(thumb.getValue().getUrl());
			thumbnailInformation.setType(findType(thumb.getValue().getHeight()));
			
			thumbnailInformations.add(thumbnailInformation);
		}
		
		 
		return thumbnailInformations;
	}
	
	private static String findType(Long height) {
		String type = "";
		
		if(height.equals(Long.valueOf(90))) {
			type = "default";
		}
		else if(height.equals(Long.valueOf(180))) {
			type = "medium";
		}
		else if(height.equals(Long.valueOf(360))) {
			type = "high";
		}
		else if(height.equals(Long.valueOf(480))) {
			type = "standard";
		}
		else if(height.equals(Long.valueOf(720))) {
			type = "maxres";
		}
		
		return type;
	}
}
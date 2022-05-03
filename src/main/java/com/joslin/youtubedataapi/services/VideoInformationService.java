package com.joslin.youtubedataapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joslin.youtubedataapi.entities.Playlist;
import com.joslin.youtubedataapi.entities.ThumbnailInformation;
import com.joslin.youtubedataapi.entities.VideoInformation;
import com.joslin.youtubedataapi.repository.ThumbnailInformationRepository;
import com.joslin.youtubedataapi.repository.VideoInformationRepository;

@Service
public class VideoInformationService {
	
	@Autowired
	private VideoInformationRepository videoRepository;
	
	@Autowired
	private ThumbnailInformationRepository thumbRepository;

    public VideoInformation findById(String id) throws Exception {
    	Optional<VideoInformation> obj = videoRepository.findById(id);
    	return obj.orElseThrow(() -> new Exception("Video não encontrado!"));
    }
    
    public List<VideoInformation> findByPlaylist(String id) {
    	List<VideoInformation> savedVideos = videoRepository.findByPlaylist(id);
    	
    	List<VideoInformation> videos = new ArrayList<VideoInformation>();
    	
    	for(VideoInformation video : savedVideos) {
    		video.setThumbnail(thumbRepository.findThumbByVideoId(video.getId()));
    		
    		videos.add(video);
    	}
    	
    	return videos;
    }
    
    public Playlist insertVideos(List<VideoInformation> videos, String playlist) {
    	Playlist videoPlaylist = new Playlist(videos);
    
    	for(VideoInformation video : videos) {
    		videoRepository.save(video);
    	}
    	
    	return videoPlaylist;
    }
    
    public void insertThumbnails(List<ThumbnailInformation> thumbs, String playlist) {
    	
    	for(ThumbnailInformation thumb : thumbs) {
    		thumbRepository.save(thumb);
    	}
    }
    
}

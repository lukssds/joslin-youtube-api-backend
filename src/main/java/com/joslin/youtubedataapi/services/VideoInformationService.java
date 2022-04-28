package com.joslin.youtubedataapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joslin.youtubedataapi.entities.Playlist;
import com.joslin.youtubedataapi.entities.VideoInformation;
import com.joslin.youtubedataapi.repository.VideoInformationRepository;

@Service
public class VideoInformationService {
	
	@Autowired
	private VideoInformationRepository repository;

    public VideoInformation findById(String id) throws Exception {
    	Optional<VideoInformation> obj = repository.findById(id);
    	return obj.orElseThrow(() -> new Exception("Video não encontrado!"));
    }
    
    public List<VideoInformation> findByPlaylist(String id) {
    	return repository.findByPlaylist(id);
    }
    
    public Playlist insert(List<VideoInformation> videos, String playlist) {
    	Playlist videoPlaylist = new Playlist(videos);
    
    	for(VideoInformation video : videos) {
    		repository.save(video);
    	}
    	
    	return videoPlaylist;
    }
    
}

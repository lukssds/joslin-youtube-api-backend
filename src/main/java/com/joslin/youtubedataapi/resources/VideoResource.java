package com.joslin.youtubedataapi.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.Video;
import com.joslin.youtubedataapi.entities.Playlist;
import com.joslin.youtubedataapi.entities.VideoInformation;
import com.joslin.youtubedataapi.helper.VideoHelper;
import com.joslin.youtubedataapi.services.VideoInformationService;
import com.joslin.youtubedataapi.services.VideoService;

@RestController
public class VideoResource {

    @Autowired
    private VideoService videoService;
    
    @Autowired
    private VideoInformationService videoInformationService;

    @GetMapping("/fetch-playlist-videos")
    public ResponseEntity<List<PlaylistItem>> findVideosByPlayListId(@RequestParam String id){

        List<PlaylistItem> videos = videoService.findVideosByPlayListId(id);
        return ResponseEntity.ok().body(videos);
    }

    @GetMapping("/fetch-videos-by-id/{id}")
    public ResponseEntity<List<Video>> findVideosById(@PathVariable List<String> id){

        List<Video> videos = videoService.findVideosById(id);
        return ResponseEntity.ok().body(videos);
    }
    
    @PostMapping("/insert")
    public ResponseEntity<Playlist> insert(@RequestParam String id) {
    	
        List<PlaylistItem> videos = videoService.findVideosByPlayListId(id);
        List<VideoInformation> videoInformationList = new ArrayList<VideoInformation>();
        
        for(PlaylistItem video : videos) {
    		VideoInformation videoInformation = new VideoInformation();
    		videoInformation.setDescription(video.getSnippet().getDescription());
    		videoInformation.setId(video.getId());
    		videoInformation.setPlaylist(id);
    		videoInformation.setThumbnail(VideoHelper.buildThumbnails(video.getSnippet().getThumbnails()));
    		videoInformation.setTitle(video.getSnippet().getTitle());
    		
    		videoInformationList.add(videoInformation);
        }
    	Playlist savedVideos = videoInformationService.insert(videoInformationList, id);
    	return ResponseEntity.ok().body(savedVideos);
    }
}

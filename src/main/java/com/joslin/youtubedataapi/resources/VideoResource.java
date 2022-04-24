package com.joslin.youtubedataapi.resources;

import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.Video;
import com.joslin.youtubedataapi.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VideoResource {

    @Autowired
    private VideoService service;

    @GetMapping("/fetch-playlist-videos")
    public ResponseEntity<List<PlaylistItem>> findVideosByPlayListId(@RequestParam String id){

        List<PlaylistItem> videos = service.findVideosByPlayListId(id);
        return ResponseEntity.ok().body(videos);
    }

    @GetMapping("/fetch-videos-by-id/{id}")
    public ResponseEntity<List<Video>> findVideosById(@PathVariable List<String> id){

        List<Video> videos = service.findVideosById(id);
        return ResponseEntity.ok().body(videos);
    }
}

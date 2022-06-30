package com.joslin.youtubedataapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joslin.youtubedataapi.entities.VideoInformation;
import com.joslin.youtubedataapi.services.VideoInformationService;

@RestController
@RequestMapping("/video")
public class VideoInformationResource {

    @Autowired
    private VideoInformationService service;

    @GetMapping("/{id}")
    public ResponseEntity<VideoInformation> findVideo(@PathVariable String id) throws Exception{

        VideoInformation videos = service.findById(id);
        return ResponseEntity.ok().body(videos);

    }

    @GetMapping("/playlist")
    public ResponseEntity<List<VideoInformation>> findByPlaylist(@RequestParam String id){

        List<VideoInformation> videos = service.findByPlaylist(id);
        return ResponseEntity.ok().body(videos);
    }
    
}

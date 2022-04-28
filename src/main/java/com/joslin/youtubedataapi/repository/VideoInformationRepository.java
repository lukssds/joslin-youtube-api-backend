package com.joslin.youtubedataapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joslin.youtubedataapi.entities.VideoInformation;

public interface VideoInformationRepository extends JpaRepository<VideoInformation, String> {
	
	List<VideoInformation> findByPlaylist(String id);

}

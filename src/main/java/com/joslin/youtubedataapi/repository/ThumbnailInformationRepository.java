package com.joslin.youtubedataapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.joslin.youtubedataapi.entities.ThumbnailInformation;

public interface ThumbnailInformationRepository extends JpaRepository<ThumbnailInformation, String> {
	
	@Query("SELECT t FROM thumbnail t where t.videoId = :id")
	List<ThumbnailInformation> findThumbByVideoId(@Param("id") String id);
	
	
	
//	List<ThumbnailInformation> findByVideoInformation(String id);
	

}

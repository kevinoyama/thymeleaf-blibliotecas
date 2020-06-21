package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {
	
	List<Library> findByBuildingId(Long buildingId);
	
}

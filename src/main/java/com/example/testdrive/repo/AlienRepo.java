package com.example.testdrive.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.testdrive.model.Alien;

public interface AlienRepo extends CrudRepository<Alien, Integer>{
	
	public List<Alien> findByLanguage(String lang);
	
	public List<Alien> findByAidGreaterThan(int aid);
	
	
	@Query("from Alien where language=?1 order by aname")
	public List<Alien> findByLanguageSortedWithName(String lang);

}

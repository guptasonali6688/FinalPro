package com.zycus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zycus.entity.Response;
import com.zycus.entity.Survey;

@Repository
public interface surveyRepository extends CrudRepository<Survey,Integer> {
	
	@Query("SELECT p FROM Survey p WHERE  p.surveyUser.Uid=:id ")
    public List<Survey> findSurveys(@Param ("id") int id); 
	
	  

}

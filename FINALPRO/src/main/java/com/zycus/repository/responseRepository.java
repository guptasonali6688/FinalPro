package com.zycus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zycus.entity.ShareSurvey;
import com.zycus.entity.Response;

@Repository
public interface responseRepository extends CrudRepository<Response,Integer> {
	
	@Query("SELECT p FROM Response p WHERE  p.surveyUser.Uid=:id ")
    public List<Response> findResponses(@Param ("id") int id); 

	
	@Query("SELECT p FROM Response p WHERE  p.surveyUser.Uid=:user_id and p.survey.Sid=:surveyId")
    public List<Response> getReponsesForSurvey(@Param("user_id") int user_id,@Param("surveyId") int surveyId );
}

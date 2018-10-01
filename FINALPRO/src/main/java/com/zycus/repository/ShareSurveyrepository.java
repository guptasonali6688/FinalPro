package com.zycus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zycus.entity.ShareSurvey;
import com.zycus.entity.Response;

@Repository
public interface ShareSurveyrepository extends CrudRepository<ShareSurvey,Integer>{

	@Query("SELECT p FROM ShareSurvey p WHERE  p.user.Uid=:id")
    public List<ShareSurvey> findUsers(@Param ("id") int id); 
	
	@Query("SELECT p FROM ShareSurvey p WHERE  p.Admin.Uid=:id")
    public List<ShareSurvey> findAdmin(@Param ("id") int id); 
	
	@Query("SELECT p FROM ShareSurvey p WHERE  (p.user.Uid=:user_id and p.survey.Sid=:surveyId)")
    public ShareSurvey getrespondedharesurveys(@Param("user_id") int user_id,@Param("surveyId") int surveyId );

	
	@Query("SELECT p FROM ShareSurvey p WHERE  (p.survey.Sid=:surveyId)")
    public List<ShareSurvey> getShareSurveyBysurveys(@Param("surveyId") int surveyId );
	
	
	
}

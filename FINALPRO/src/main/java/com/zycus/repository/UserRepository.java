package com.zycus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zycus.entity.Survey;
import com.zycus.entity.SurveyUser;

@Repository
public interface UserRepository extends CrudRepository<SurveyUser,Integer> {
	
	@Query("SELECT p FROM SurveyUser p WHERE p.Urole='User'")
	public List<SurveyUser> findUser();
		
	@Query("SELECT p FROM SurveyUser p WHERE p.Uid='User'")
	public SurveyUser findUserByID();

}

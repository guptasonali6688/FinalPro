package com.zycus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zycus.entity.Question;
import com.zycus.entity.Response;


@Repository
public interface questionRepository extends CrudRepository<Question,Integer> {
	
	@Query("SELECT p FROM Question p WHERE  p.Qid=:id ")
    public Question findQuestions(@Param ("id") int id);

	
	@Query("SELECT p FROM Question p WHERE  p.survey.Sid=:id ")
	public List<Question> findQuestion(int sid); 

}

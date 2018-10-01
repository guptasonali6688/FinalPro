package com.zycus.service;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.zycus.DTO.UserDetail;
import com.zycus.entity.*;
import com.zycus.repository.*;

@Service
@Transactional
public class SurveyServiceImpl{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private surveyRepository surveyrepository;
	
	@Autowired
	private questionRepository questionrepository;
	
	@Autowired
	private responseRepository responserepository;
	
	@Autowired
	private ShareSurveyrepository shareSurveyrepository;
	
	

	 
	
	/* (non-Javadoc)
	 * @see com.zycus.service.ShoppingService#registerCustomer(com.zycus.entity.Customer)
	 */
	//@CacheEvict(value="user.cache",allEntries=true)
	public void registerUser(SurveyUser user)
	{
		userRepository.save(user);
	}
	
	
	public int SurveyLogIn(SurveyUser surveyUser)//DTO-DATA TRANSFER OBJECT= OrderDTO
	{
		
		int userId=surveyUser.getUid();
		String password=surveyUser.getUpassword();
		
		
		
		SurveyUser user=(userRepository.findById(surveyUser.getUid())).get();
		
		System.out.println(user.toString());
		
		if(password.equals(user.getUpassword()) && user.getUrole().equals("Admin"))
		{
			return 1; 
		}
		else if(password.equals(user.getUpassword()) && user.getUrole().equals("User"))
		{
			return 2;
		}
		else
		{
			return 0;
		}
		
	}
	
	public SurveyUser surveyList(int userid)
	{
		SurveyUser user=(userRepository.findById(userid)).get();
		
		
		
		 return user;
	}
	
	
	
	public void registerSurvey(Survey survey)
	{
		for(Question q:survey.getQuestion())
		{
			System.out.println( q.getQues());
			q.setSurvey(survey);
		}
		surveyrepository.save(survey);
	}
	
	
	public Survey GetSurvey(Survey survey)
	{
		
		Survey survey1=(surveyrepository.findById(survey.getSid())).get();
		System.out.println("Service Working");
		System.out.println(survey1);
		
		 return survey1;
	}


	public void updateStatus(Survey survey) 
	{
		
		String surveStatus=survey.getSstatus();
		Survey survey1=(surveyrepository.findById(survey.getSid())).get();
		
		survey1.setSstatus(survey.getSstatus());
		
		
		
		
	}


	public List<SurveyUser> getallUsers() {
		
		
		return userRepository.findUser();
		
	}


	public void addSurvey(SurveyUser surveyuser,SurveyUser admin, Survey survey) {


		/*SurveyUser userA=(userRepository.findUsers(admin.getUid())).get();
		SurveyUser user=(userRepository.findById(surveyuser.getUid())).get();
		Survey surveys=(surveyrepository.findById(survey.getSid())).get();*/
		
		
		SurveyUser userdb=userRepository.findById(surveyuser.getUid()).get();
		ShareSurvey sharesurvey=new ShareSurvey();
		
		sharesurvey.setAdmin(admin);
		sharesurvey.setUser(userdb);
		sharesurvey.setSurvey(survey);
		sharesurvey.setStat("share");
		
		
		shareSurveyrepository.save(sharesurvey);
		
		System.out.println("Service Implementation");
		
		
	}


	

	public void addResponse(Response response) {
		
		
//		
//		System.out.println(question1);
		
		responserepository.save(response);
		
		
		
		
		
		
	}


	public List<Response> GetResponse(SurveyUser user) {
		
		
		
		return (responserepository.findResponses(user.getUid()));
	}


	public List<Survey> GetUsersSurveys(SurveyUser user) {
		
		return surveyrepository.findSurveys(user.getUid());
	}


	public List<Survey> GetUsersSurvey(SurveyUser user) {
		
		
		return surveyrepository.findSurveys(user.getUid());
	}


	public List<Response> GetUsersResponses(Survey survey,SurveyUser user) {
		
		
		return responserepository.getReponsesForSurvey(user.getUid(),survey.getSid());
	}


public List<Response> GetUsersResponse(SurveyUser user) {
		
		
		return responserepository.findResponses(user.getUid());
	}


	public SurveyUser getUserFromID(int uid) {


		return userRepository.findById(uid).get();
		
	}


	public void ADDUser(SurveyUser user) {
		
		userRepository.save(user);
	}


	public List<ShareSurvey> GetUseShareSurvey(SurveyUser user) {
		
		return shareSurveyrepository.findUsers(user.getUid());
	}
	
	public void ChangeSharedSurvey(SurveyUser user,Survey survey)
	{
		ShareSurvey sharesurvey=shareSurveyrepository.getrespondedharesurveys(user.getUid(),survey.getSid());
		sharesurvey.setStat("attempted");
		
	}


	public List<ShareSurvey> getShareSurveyByAdmin(SurveyUser admin) {
		
		
		return shareSurveyrepository.findAdmin(admin.getUid());
	}


	public List<Response> getResponsesForAdmin(ShareSurvey sharedsurvey) {
		// TODO Auto-generated method stub
		return responserepository.getReponsesForSurvey(sharedsurvey.getUser().getUid(),sharedsurvey.getSurvey().getSid());
	}


	public List<ShareSurvey> getUsersFromSurvey(Survey survey) {
		
		return shareSurveyrepository.getShareSurveyBysurveys(survey.getSid());
	}
	
	
	/* (non-Javadoc)
	 * @see com.zycus.service.ShoppingService#registerProduct(com.zycus.entity.Product)
	 
	@CacheEvict(value="survey.cache",allEntries=true)
	public void registerSurvey(Survey survey)
	{
		surveyrepository.save(survey);
	}
	
	 (non-Javadoc)
	 * @see com.zycus.service.ShoppingService#registerOrder(com.zycus.entity.Order)
	 
	@CacheEvict
	public void registerQuestion(Question question)
	{
		questionrepository.save(question);
	}
	
	
	 (non-Javadoc)
	 * @see com.zycus.service.ShoppingService#registerOrder(com.zycus.entity.Order)
	 
	@CacheEvict
	public void registerresponse(response response)
	{
		responserepository.save(response);
	}
	*/
	
	
	
	
	
/*
	@Cacheable(value="customer.cache")
	public Iterable<Customer> getRegisterdCustomers()
	{
		return customerRepository.findAll();
	}
	@Cacheable(value="product.cache")
	public Iterable<Product> getRegisterdProducts()
	{
		return productRepository.findAll();
	}
	
	/* (non-Javadoc)
	 * @see com.zycus.service.ShoppingService#placeOrder(com.zycus.DTO.OrderDTO)
	 */
	/*
	public void placeOrder(OrderDTO orderDTO)//DTO-DATA TRANSFER OBJECT= OrderDTO
	{
		
		int customerId=orderDTO.getCustomerId();
		Map<Integer,Integer> cart=orderDTO.getCart();
		
		Customer customer=(customerRepository.findById(orderDTO.getCustomerId())).get();
		
		SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
		
		Order order=new Order();
		order.setId(1);
		order.setDate(dtf);
		order.setCustomer(customer);
		
		Set<LineItem> lineItems=new HashSet<LineItem>();
		
		for(Map.Entry<Integer, Integer> entry:cart.entrySet())
		{
			int productId=entry.getKey();
			int quantity=entry.getValue();
		
		
		
		Product product=productRepository.findById(productId).get();
		product.setQuantity(product.getQuantity()-quantity);
		productRepository.save(product);
		
		LineItem lineitem=new LineItem();
		lineitem.setOrder(order);
		lineitem.setProduct(product);
		lineitem.setQuantity(quantity);
		lineItems.add(lineitem);
		
		
		
		}
		
		order.setLineItems(lineItems);
		orderRepository.save(order);
		
		
		
	}
	*/
}

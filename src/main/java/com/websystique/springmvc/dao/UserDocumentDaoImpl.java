package com.websystique.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.UserDocument;

@Repository("userDocumentDao")
public class UserDocumentDaoImpl extends AbstractDao<Integer, UserDocument> implements UserDocumentDao{

	@SuppressWarnings("unchecked")
	public List<UserDocument> findAll() {
		Criteria crit = createEntityCriteria();
		return (List<UserDocument>)crit.list();
	}
	@Override
	public List<UserDocument> findTop3() {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("path", "/news"));
		crit.addOrder(Order.desc("datetime"));
		List<UserDocument> documents =  (List<UserDocument>)crit.list();
		List<UserDocument> documentsTop3 =  new ArrayList();
		documentsTop3.add(documents.get(0));
		documentsTop3.add(documents.get(1));
		documentsTop3.add(documents.get(2));
		return documentsTop3;
	}
	
	public void save(UserDocument document) {
		persist(document);
	}

	@Override
	public UserDocument findById(int id) {
		
		return getByKey(id);
	}

	public void update(UserDocument document){
		merge(document);
	}

	@SuppressWarnings("unchecked")
	public List<UserDocument> findAllByUserId(int userId){
		Criteria crit = createEntityCriteria();
		Criteria userCriteria = crit.createCriteria("user");
		userCriteria.add(Restrictions.eq("id", userId));
		return (List<UserDocument>)crit.list();
	}

	
	public void deleteById(int id) {
		UserDocument document =  getByKey(id);
		delete(document);
	}

	@Override
	public UserDocument findByPath(String path) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("path", path));
		crit.addOrder(Order.desc("datetime"));
		List<UserDocument> documents =  (List<UserDocument>)crit.list();		
		return documents.get(0);
	}

}

package com.sivalabs.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.entity.Contact;


@Repository
@Transactional
public class ContactsDAO
{
	private Logger logger = LoggerFactory.getLogger(ContactsDAO.class);
    @Autowired
    private SessionFactory sessionFactory;
    
    public Contact getById(int id)
    {
        return (Contact) sessionFactory.getCurrentSession().get(Contact.class, id);
    }
    
    public Contact getByName(String name){
    	return (Contact) sessionFactory.getCurrentSession().get(Contact.class, name);
    }
    
    public boolean loginContacts(String name,String password){
    	if(name==null)
    		name="";
    	if(password == null)
    		password = ""; 
    	Criteria criteria = null;
    	try {
    		criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class);
        	criteria.add(Restrictions.and(Restrictions.eq("name", name), Restrictions.eq("password", password)));
		} catch (Exception e) {
			logger.info("query failed!");
		}
    	boolean flag = true;
    	if(criteria.list().size()==0)
    		flag = false;
    	return flag;
    }
    
    @SuppressWarnings("unchecked")
    public List<Contact> searchContacts(String name)
    {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class);
        criteria.add(Restrictions.ilike("name", name+"%"));
        return criteria.list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Contact> getAllContacts()
    {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class);
        return criteria.list();
    }
    
    public int save(Contact contact)
    {
    	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class);
    	criteria.add(Restrictions.ilike("name",contact.getName()));
    	if(criteria.list().size()>0){
    		return 0;
    	}else{
    		return (Integer) sessionFactory.getCurrentSession().save(contact);
    	}
        
    }
    
    public void update(Contact contact)
    {
        sessionFactory.getCurrentSession().merge(contact);
    }
    
    public void delete(int id)
    {
        Contact c = getById(id);
        sessionFactory.getCurrentSession().delete(c);
    }
    
    public int checkName(String name){
    	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class);
    	criteria.add(Restrictions.ilike("name",name));
    	if(criteria.list().size()>0){
    		return 1;
    	}else{
    		return 0;
    	}
    }
}
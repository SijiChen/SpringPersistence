package com.spring.persistence.dao;

import com.spring.persistence.domain.CustomerProfile;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sjchen on 8/22/16.
 */

public class CustomerProfileDao extends NamedParameterJdbcDaoSupport {

    /**
     * select row by id
     * @param id
     * @return customer_profile with id and email;
     */
    public CustomerProfile customerProfile(Long id) {

        final String SELECT = "select customer_profile_id as id, email as email from " +
                "customer_profile where customer_profile_id = :contactId";
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("contactId", id);
        CustomerProfile profile = getNamedParameterJdbcTemplate().queryForObject(SELECT, namedParameters,
                new BeanPropertyRowMapper<CustomerProfile>(CustomerProfile.class));
        return profile;

    }

    /**
     * select email by id
     * @param id
     * @return string
     */
    public String findEmailById(Long id) {
        return getJdbcTemplate().queryForObject(
                "select email from customer_profile where  customer_profile_id = ?", new Object[]{id}, String.class);
    }

    /**
     * insert a new row into table customer_profile
     * @param id
     * @param email
     * @param firstname
     * @param lastname
     * @return
     */
    public int insertRow(long id, String email, String firstname, String lastname){
        final String INSERT = "INSERT INTO customer_profile  VALUES (:id, :email, :firstname,:lastname)";
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("id", id);
        namedParameters.put("email",email);
        namedParameters.put("firstname",firstname);
        namedParameters.put("lastname",lastname);
        getNamedParameterJdbcTemplate().update(INSERT,namedParameters);
        return 1;
    }

    public List<CustomerProfile> selectAll(){
        final String SELECT = "select customer_profile_id, email,first_name,last_name from customer_profile";

        List<CustomerProfile> profiles = getNamedParameterJdbcTemplate().query(SELECT,(rs,rowNum)->{
            String email = rs.getString("email");
            Long id = rs.getLong("customer_profile_id");

            CustomerProfile customerProfile = new CustomerProfile();
            customerProfile.setEmail(email);
            customerProfile.setId(id);
            customerProfile.setFirstName(rs.getString("first_name"));
            customerProfile.setLastName(rs.getString("last_name"));
            return  customerProfile;
        });

        return profiles;
    }

    public CustomerProfile findByEmail(String email){
        final String SELECT = "SELECT * FROM customer_profile where email = :email";
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("email",email);
        CustomerProfile profile = getNamedParameterJdbcTemplate().queryForObject(SELECT,
                namedParameters, new BeanPropertyRowMapper<CustomerProfile>(CustomerProfile.class));
          return profile;
    }

    public void updateNamesbyEmail(String email,String firstname, String lastname){
        CustomerProfile profile=findByEmail(email);
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("email",email);
        namedParameters.put("firstname", firstname);
        namedParameters.put("lastname",lastname);
        String UPDATE = "UPDATE customer_profile SET first_name =:firstname, last_name = :lastname where email =:email";
        getNamedParameterJdbcTemplate().update(UPDATE,namedParameters);
    }
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("spring-persistence.xml");
        ctx.refresh();
        CustomerProfileDao profile = ctx.getBean("customerProfileDao",CustomerProfileDao.class);
        System.out.println(profile.customerProfile(1L));
    }
}

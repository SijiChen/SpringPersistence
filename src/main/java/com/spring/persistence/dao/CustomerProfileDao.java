package com.spring.persistence.dao;

import com.spring.persistence.domain.CustomerProfile;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
/**
 * Created by sjchen on 8/22/16.
 */

public class CustomerProfileDao extends JdbcDaoSupport {

    public CustomerProfile customerProfile(Long id) {

        final String SELECT = "select customer_profile_id as id, email as email from customer_profile where customer_profile_id = ?";

        CustomerProfile profile = getJdbcTemplate().queryForObject(SELECT, new Object[]{id},
                new BeanPropertyRowMapper<CustomerProfile>(CustomerProfile.class));

        return profile;

    }
    @Autowired
    private static CustomerProfileDao customerProfileDao;

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("spring-persistence.xml");
        ctx.refresh();
        CustomerProfileDao profile = ctx.getBean("customerProfileDao",CustomerProfileDao.class);
        System.out.println(profile.customerProfile(1L));
    }
}

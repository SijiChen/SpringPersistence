package com.spring.persistence.dao;

import com.spring.persistence.domain.CustomerProfile;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by sjchen on 8/23/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-persistence.xml"})
public class CustomerProfileDaoTest extends TestCase {

    @Autowired
    private CustomerProfileDao customerProfileDao;

    @Test
    public void testFindEmailNameById() throws Exception {
        String email = customerProfileDao.findEmailById(1L);
        System.out.println(email);
        Assert.notNull(email);
    }
    @Test
    public void testCustomerProfile() {

        CustomerProfile profile = customerProfileDao.customerProfile(1L);
        Assert.notNull(profile);
    }

    @Test
    public void testInsertRow(){
        //int r = customerProfileDao.insertRow(2,"chensiji@test.com","siji", "chen");
        CustomerProfile profile = customerProfileDao.customerProfile(2L);
        //Assert.notNull(profile);
    }

    @Test
    public void testSelectAll(){
        List<CustomerProfile> profiles = customerProfileDao.selectAll();
        System.out.println(profiles);
        Assert.notNull(profiles);

    }
    @Test
    public void testUpdateNamesbyEmail(){
        customerProfileDao.updateNamesbyEmail("sijichen@gmail.com","Siji","Chen");
        System.out.println(customerProfileDao.selectAll());
    }

}
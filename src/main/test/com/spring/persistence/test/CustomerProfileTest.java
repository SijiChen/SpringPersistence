package com.spring.persistence.test;

import com.spring.persistence.dao.CustomerProfileDao;
import com.spring.persistence.domain.CustomerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

/**
 * Created by sjchen on 8/22/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-persistence.xml"})
public class CustomerProfileTest {
    @Autowired
    private CustomerProfileDao customerProfileDao;

    @Test
    public void testFetch() {

        CustomerProfile profile = customerProfileDao.customerProfile(1L);
        Assert.notNull(profile);
    }


}

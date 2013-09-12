package org.jz13;

import org.junit.runner.RunWith;
import org.jz13.TestService;
import org.jz13.config.DefaultApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertNotNull;

/**
 * @author Kristian Rosenvold
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ DefaultApplicationConfiguration.class})

public class TestServiceTest
{
    @Autowired
    TestService testService;

    @org.junit.Test
    public void testExecute() throws Exception {
        assertNotNull( testService);
    }
}

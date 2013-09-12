package org.jz13;

import org.jz13.TestService;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertNotNull;

/**
 * @author Kristian Rosenvold
 */
public class TestService2Test extends AbstractTestCase
{
    @Autowired
    TestService testService;

    @org.junit.Test
    public void testExecute() throws Exception {
        assertNotNull( testService);
    }
}
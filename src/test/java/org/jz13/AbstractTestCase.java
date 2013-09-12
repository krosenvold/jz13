package org.jz13;

import org.junit.runner.RunWith;
import org.jz13.config.DefaultApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Kristian Rosenvold
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ DefaultApplicationConfiguration.class})
public abstract class AbstractTestCase
{
}
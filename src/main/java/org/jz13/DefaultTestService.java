package org.jz13;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author Kristian Rosenvold
 */
public class DefaultTestService
    implements TestService
{

    @Override
    public String reverse( String string )
    {
        return StringUtils.reverse(string);
    }
}
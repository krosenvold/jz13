package org.jz13;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author Kristian Rosenvold
 */
public class MyHomePage extends WebPage
{
    @SpringBean
    private TestService testService;


    public MyHomePage(){
        // todo: Make page :)
    }

}

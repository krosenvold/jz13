package org.jz13.config;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * @author Kristian Rosenvold
 */
public class Log4JConfigurator
{
    public static void setupDefaults() {
            DailyRollingFileAppender appender = new DailyRollingFileAppender();
            appender.setLayout( new PatternLayout( "%d %p  - %m%n" ) );
        Logger.getLogger( "org.jz.FirstAppender" ).addAppender( appender );

        Logger rootLogger = Logger.getRootLogger();

            DailyRollingFileAppender secondAppender = new DailyRollingFileAppender();
        Logger.getLogger( "org.jz.SecondAppender" ).addAppender( appender );
        rootLogger.addAppender(secondAppender);
    }

}

package org.jz13;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;
import org.jz13.config.DefaultApplicationConfiguration;
import org.jz13.config.Log4JConfigurator;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.io.File;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


public class EmbeddedJettyStarter
{

    public static void main(String[] args)
        throws Exception
    {
        new EmbeddedJettyStarter().startJettyWithDefaultServlets("/foo", 8080);
    }


    public void startJettyWithDefaultServlets(String contextPath, int port)
        throws Exception
    {
            Log4JConfigurator.setupDefaults();

            dieIfRunning(port);

            Server server = createServer( port );

        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        springContext.register( DefaultApplicationConfiguration.class );


            final File webappRootLocation = getWebappRotLocation();
//            WebAppContext appContext = createAppContext(contextPath, webappRootLocation);

            ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
            server.setHandler(contextHandlerCollection);

            server.start();

    }

    private WebAppContext createAppContext(String contextPath, File file) throws IOException {
        return new WebAppContext(file.getAbsolutePath(), contextPath);
    }

    @SuppressWarnings({"UnusedDeclaration"})
    private WebAppContext getTestAppContext(String contextPath, File file) {
        String testLocation = file.getAbsolutePath().replace("main", "test");
        final File testScope = new File(testLocation);
        System.out.println("testContext.getAbsolutePath() = " + testScope.getAbsolutePath());
        return new WebAppContext(testScope.getAbsolutePath(), contextPath);
    }


    private File getWebappRotLocation() throws IOException {
        final File file = computeWebappRootLocation(this.getClass());
        if (null == file) {
            throw new IllegalStateException("The application must " + "have the module path as the working directory, was ["
                    + new File("./").getCanonicalPath() + "]");
        }
        System.out.println( "webapp.getAbsolutePath() = " + file.getAbsolutePath() );
        return file;
    }

    private Server createServer(int port) {
        Server server = new Server(port);
        server.setStopAtShutdown( true );
        return server;
    }

    private static File computeWebappRootLocation(Class<? extends EmbeddedJettyStarter> webAppClass) {
        File projectPath;
        URL resource = webAppClass.getResource(webAppClass.getSimpleName() + ".class");
        String path = resource.getPath();
        System.out.println("path = " + path);
        int targetDirIdx = path.indexOf("/target");
        if (targetDirIdx < 0) {
            /*
            * this is really no better but I need to find a better way...
            */
            resource = webAppClass.getResource("/applicationContext.xml");
            path = resource.getPath();
            System.out.println("path = " + path);
            targetDirIdx = path.indexOf("/target");

        }
        projectPath = new File(path.substring(0, targetDirIdx));
        String relativePath = "src/main/webapp";
        List<File> webAppFoldersToTry = Arrays.asList(new File(relativePath), new File(projectPath, relativePath));
        for (File folder : webAppFoldersToTry) {
            if (folder.exists()) {
                return folder;
            }
        }
        return null;
    }

    private static void dieIfRunning(int port) throws IOException {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(port);
        } catch (BindException e) {
            throw new RuntimeException("Server is already running on port [" + port + "]");
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

}

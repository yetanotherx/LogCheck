package yetanotherx.util.LogCheck;

import java.io.File;
import java.io.IOException;

public class LogCheck {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {

	File file = new File("server/server.log");

	if( !file.exists() ) {
	    System.out.println( "ERROR: Could not find server/server.log!!!" );
	    System.exit(1);
	}

	long lastModified = file.lastModified();
	long currentTime = System.currentTimeMillis();

	//900000 ms = 15 minutes
	if( currentTime - lastModified > 300000 ) {
	    //It's been 15 minutes, something went wrong.
	    Runtime.getRuntime().exec("killall java");
	    Runtime.getRuntime().exec("server/start");
	    System.out.println( "I had to kill the process..." );
	    System.exit(1);
	}
	else {
	    //Nothing wrong.
	    System.out.println( "Server appears to be up." );
	    System.exit(0);
	}

    }

}

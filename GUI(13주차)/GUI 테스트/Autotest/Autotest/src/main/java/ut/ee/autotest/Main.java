package ut.ee.autotest;

import org.sikuli.script.FindFailed;

public class Main {

	public static void main(String[] args) throws InterruptedException, FindFailed {
		
		int iteration = 1; // From 1-3 Since lab practical work flow has 3 iterations
		int jarVersion = 1; // From 1-2 Add software2.jar to resources folder later and specificy which version you want to launch:
		//software+jarVersion.jar

		Autotest sikuli = new Autotest(iteration, jarVersion);
		sikuli.run();
		
	
	}
	
	

}

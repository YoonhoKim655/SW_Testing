package ut.ee.autotest;

import java.io.FileNotFoundException;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.reporting.ColumnDataTypesReport;

public class Autotest implements Runnable {

	private SikuliSteps sikuli;
	private DRDataSource reportFile;
	private int iteration;
	private int jarVersion;

	public Autotest(int iteration, int jarVersion) {
		this.iteration = iteration;
		this.jarVersion = jarVersion;
	}


	public void run() {
		
		reportFile = new DRDataSource("result", "tab", "comment"); //Create report file
		sikuli = new SikuliSteps(); //Create Example class object
		
		//Open the software that opens the software+jarVersion.jar located in src/main/resources folder
		openSoftware();
		
		
		if (iteration == 1) {
			
			//YOUR CODE GOES HERE
			
			//Running this example now that is not related to any of the specification points
			exampleTestCase(); //delete it
			
			//tab1();	
			//tab2();
			//...
		}
		
		if (iteration == 2) {
			
			//tab1();
			//tab2();
			//...
			
		}
		
		if (iteration == 3) {
		//...
		//tabX();
		//...
		}
		
		//Create the report
		ColumnDataTypesReport report = new ColumnDataTypesReport(reportFile, "Report" + iteration + ".pdf");
		try {
			report.build();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	

	private void exampleTestCase() {
		
		/**
		 * 
		 */
		//Example test case that clicks on the text editor tab and finally compares the text that was entered by copying
		//it to the clipboard and then comparing it
		sikuli.click("TextEditorTab.png");
		if(sikuli.write("writeSomething.png", "I am writing here")) {
			System.out.println("Using resolution 1920 x 1080 picture");
			//Only needed for this example, you don't have to take this into consideration
		} else {
			//1366x768 resolution picture
			System.out.println("Using resolution 1366 x 768 picture");
			sikuli.write("writeSomethingLower.png", "I am writing here");
		}
		
		//Navigate away and back from the tab if spec requires it
		
		boolean succeeded = sikuli.compareTextToClipboards("I am writing here");
		if (succeeded) {
			reportFile.add("PASSED", "Tab 1", "Entered text was the same");
		} else {
			reportFile.add("FAILED", "Tab 1", "Entered text was not the same");
		}
		// and 1 bullet point can be similarly covered like this.
		/**
		 * 
		 */
		//Another test case goes here
		
		//ends after reporting
		
	}
	
	private void openSoftware() {
		try {
			sikuli.openJavaJar(jarVersion);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}

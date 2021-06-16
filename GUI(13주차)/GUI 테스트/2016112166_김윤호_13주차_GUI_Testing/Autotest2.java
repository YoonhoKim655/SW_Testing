package ut.ee.autotest;

import java.io.FileNotFoundException;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.reporting.ColumnDataTypesReport;

public class Autotest2 implements Runnable {

	private SikuliSteps sikuli;
	private DRDataSource reportFile;
	private int iteration;
	private int jarVersion;

	public Autotest2(int iteration, int jarVersion) {
		this.iteration = iteration;
		this.jarVersion = jarVersion;
	}

	//해상도 1440 * 900으로 해야 인식 가능
	public void run() {
		
		reportFile = new DRDataSource("result", "tab", "comment"); //Create report file
		sikuli = new SikuliSteps(); //Create Example class object
		
		//Open the software that opens the software+jarVersion.jar located in src/main/resources folder
		openSoftware();
		
		
		if (iteration == 1) {
			
			//YOUR CODE GOES HERE
			
			//Running this example now that is not related to any of the specification points
			//exampleTestCase1(); //delete it
			
			
		}
		
		if (iteration == 2) {
			
			tab1();	
			tab2();
			tab3();
			tab4();
			tab5();
			tab6();
			tab7();
			tab8();
			tab9();
			/**/
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
	
	private void tab1() {
		sikuli.click("TextEditorTab.png");
		sikuli.click("KoalaTab.PNG");
		
		boolean succeeded = sikuli.verifyIfExists("Koala1.PNG");
		if (succeeded) {
			reportFile.add("PASSED", "Tab 1", "Koala in this pic when returning to the tab");
		} else {
			reportFile.add("FAILED", "Tab 1", "Koala not in this pic when returning to the tab");
		}		
	}
	
	private void tab2() {
		sikuli.click("TextEditorTab.png");
		
		sikuli.write("writeSomething.png", "I am writing here");
		
		sikuli.click("KoalaTab.PNG");
		sikuli.click("TextEditorTab.png");
		
		sikuli.click("blank.PNG");
		boolean succeeded1 = sikuli.verifyIfExists("writing.PNG");
		boolean succeeded2 = sikuli.compareTextToClipboards("I am writing here");
		if (succeeded1 && succeeded2) {
			reportFile.add("PASSED", "Tab 2", "Entered text was the same");
		} else {
			reportFile.add("FAILED", "Tab 2", "Entered text was not the same");
		}	
	}
	
	private void tab3() {
		sikuli.click("KoalaTab.PNG");
		sikuli.click("TextEditorTab.png");
		
		//Navigate away and back from the tab if spec requires it
		
		boolean succeeded = sikuli.verifyIfExists("HTMLeditor2.PNG");
		if (succeeded) {
			reportFile.add("PASSED", "Tab 3", "HTMLeditor in TextEditorTab");
		} else {
			reportFile.add("FAILED", "Tab 3", "HTMLeditor not in TextEditorTab");
		}		
	}
	
	private void tab4() {
		sikuli.click("KoalaTab.PNG");
		sikuli.click("TextEditorTab.png");
		
		sikuli.click("KoalaTab.PNG");
		sikuli.click("TextEditorTab.png");
		
		boolean succeeded = sikuli.verifyIfExists("textformat2.PNG");
		boolean succeeded1 = sikuli.verifyIfExists("textformat.PNG");
		sikuli.click("default_format.PNG");
		
		boolean succeeded2 = sikuli.click("selected_format.png");
		if (succeeded &&succeeded1 &&succeeded2) {
			reportFile.add("PASSED", "Tab 4", "Selected text formatting setting stay the same when returning to the tab");
		} else {
			reportFile.add("FAILED", "Tab 4", "Selected text formatting setting doesn't stay the same when returning to the tab");
		}		
	}
	
	private void tab5() {
		sikuli.click("LoremTab.PNG");
		
		sikuli.click("LoremText.PNG");
		String copied = sikuli.getCopyText();
		int copied_len = copied.length();
		
		sikuli.click("KoalaTab.PNG");
		sikuli.click("LoremTab.PNG");
		
		sikuli.click("LoremText.PNG");
		String recopied = sikuli.getCopyText();
		int recopied_len = recopied.length();
		System.out.println(copied_len);
		System.out.println(recopied_len);
		if (copied_len == recopied.length()) {
			reportFile.add("PASSED", "Tab 5", "Text length stay the same when returning to the tab");
		} else {
			reportFile.add("FAILED", "Tab 5", "Text length doesn't stay the same when returning to the tab");
		}		
	}
	
	private void tab6() {
		sikuli.click("KoalaTab.PNG");
		sikuli.click("LoremTab.PNG");
		
		//Navigate away and back from the tab if spec requires it
		
		sikuli.click("LoremText.PNG");
		sikuli.rightClick();
		
		
		boolean succeeded = sikuli.click("select_all.PNG");
		if (succeeded) {
			reportFile.add("PASSED", "Tab 6", "Select all context can be click in This tab");
		} else {
			reportFile.add("FAILED", "Tab 6", "Select all context can't be click in This tab");
		}
	}
	
	private void tab7() {
		sikuli.click("colorpickerTab.PNG");
		sikuli.click("colorpicker.PNG");
		
		
		if(sikuli.verifyIfExists("colorpickeropen.PNG"))
		{
			sikuli.click("colorpicker.PNG");
		}
		else
		{
			reportFile.add("FAILED", "Tab 7", "Color Picker tab can't be collapsible");
		}		
		
		boolean succeeded = sikuli.verifyIfExists("colorpicker.PNG");
		if (succeeded) {
			reportFile.add("PASSED", "Tab 7", "Color Picker tab can be collapsible");
		} else {
			reportFile.add("FAILED", "Tab 7", "Color Picker tab can't be collapsible");
		}		
	}
	
	private void tab8() {
		sikuli.click("KoalaTab.PNG");
		sikuli.click("colorpickerTab.PNG");
		sikuli.click("colorpicker.PNG");
		sikuli.click("LoremTab.PNG");
		sikuli.click("colorpickerTab.PNG");
		
		boolean succeeded = sikuli.verifyIfExists("color.PNG");
		if (succeeded) {
			reportFile.add("PASSED", "Tab 8", "Selected color stay the same when returning to the tab");
		} else {
			reportFile.add("FAILED", "Tab 8", "Selected color doesn't stay the same when returning to the tab");
		}		
	}
	
	private void tab9() {
		sikuli.click("KoalaTab.PNG");
		sikuli.click("progress.PNG");
		
		boolean succeeded = sikuli.verifyIfExists("infinitestate.PNG");
		if (succeeded) {
			reportFile.add("PASSED", "Tab 9", "Loader bar is in infinite state");
		} else {
			reportFile.add("FAILED", "Tab 9", "Loader bar is not in infinite state");
		}		
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

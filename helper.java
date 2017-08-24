package libs;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class helper {

	public helper() {

	}

	public String getFile(String directory, String[] ext) throws IOException {
		File dir = new File(directory);
		String filename = "";
		String filesize = "";
		
		
		List<File> files = (List<File>) FileUtils.listFiles(dir, ext, true);
		for (File file : files) {
			try {
				filename = file.getCanonicalPath();
				filesize = FileUtils.byteCountToDisplaySize(file.length());
				System.out.println("File size ="+filesize);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return filename;
	}
	
	public void takeScreenShot(WebDriver driver, String screen){
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("C:\\tmp\\"+screen+".jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
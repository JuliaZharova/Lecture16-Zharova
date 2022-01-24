package forLecture16;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileOutputStream;
import java.io.IOException;

public class ClassForListeners implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Test1 test1 = (Test1) result.getInstance();
        TakesScreenshot src = ((TakesScreenshot) Test1.driver);
        byte[] screenshot = src.getScreenshotAs(OutputType.BYTES);

        String fileName = "screenshot.png";
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)){
            fileOutputStream.write(screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

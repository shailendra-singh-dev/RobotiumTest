package com.shail.robotiumtest.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;


@SuppressWarnings("rawtypes")
public class RobotiumRecorder extends ActivityInstrumentationTestCase2 {
  	private Solo solo;
  	
  	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.shail.robotiumtest.MainActivity";

    private static Class<?> launcherActivityClass;
    static{
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
        }
    }
  	
  	@SuppressWarnings("unchecked")
    public RobotiumRecorder() throws ClassNotFoundException {
        super(launcherActivityClass);
    }

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'com.shail.robotiumtest.MainActivity'
		solo.waitForActivity("MainActivity", 2000);
        //Set default small timeout to 10581 milliseconds
		Timeout.setSmallTimeout(10581);
        //Click on Empty Text View
		solo.clickOnView(solo.getView("input_number_1"));
        //Enter the text: '12'
		solo.clearEditText((android.widget.EditText) solo.getView("input_number_1"));
		solo.enterText((android.widget.EditText) solo.getView("input_number_1"), "1000");
        //Click on Empty Text View
		solo.clickOnView(solo.getView("input_number_2"));
        //Enter the text: '56'
		solo.clearEditText((android.widget.EditText) solo.getView("input_number_2"));
		solo.enterText((android.widget.EditText) solo.getView("input_number_2"), "200");
        //Click on Devide numbers
		solo.clickOnView(solo.getView("devide_numbers"));
        //Set default small timeout to 14044 milliseconds
		Timeout.setSmallTimeout(14044);
        //Click on Add numbers
		solo.clickOnView(solo.getView("add_numbers"));
	}
}

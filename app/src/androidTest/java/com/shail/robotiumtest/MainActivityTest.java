package com.shail.robotiumtest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.robotium.solo.Solo;

/**
 * Created by iTexico Developer on 7/29/2016.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2 {
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.shail.robotiumtest.MainActivity";
    private Solo solo;
    private EditText inputNumEditText1;
    private EditText inputNumEditText2;
    private TextView resultEditText;
    private static Class<?> launcherActivityClass;

    static {
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public MainActivityTest() throws ClassNotFoundException {
        super(launcherActivityClass);
    }

    @Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        inputNumEditText1 = (EditText) solo.getView(R.id.input_number_1);
        inputNumEditText2 = (EditText) solo.getView(R.id.input_number_2);
        resultEditText = (TextView) solo.getView(R.id.result);
    }

    //Methods name should start from test ..
    public void testNumberAddition() {
        solo.waitForActivity(MainActivity.class, 2000);
        solo.enterText(inputNumEditText1, "100");
        solo.enterText(inputNumEditText2, "22");
        solo.sleep(2000);
        final Button addNumbersButton = (Button) solo.getView(R.id.add_numbers);
        solo.clickOnView(addNumbersButton);
        solo.sleep(2000);
        assertTrue("Result is not correct !", resultEditText.getText().toString().equalsIgnoreCase("122"));
        solo.waitForText("122");

    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        launcherActivityClass = null;
    }
}

package com.shail.robotiumtest;


import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

@SuppressWarnings({"unchecked", "rawtypes"})
public class MainActivityTest extends ActivityInstrumentationTestCase2 {

    private static final String TARGET_PACKAGE_ID = "com.shail.robotiumtest";
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = TARGET_PACKAGE_ID + "." + "MainActivity";
    private static Class launcherActivityClass;

    static {
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public MainActivityTest() throws ClassNotFoundException {
        super(TARGET_PACKAGE_ID, launcherActivityClass);
    }

    private Solo solo;

    @Override
    protected void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    //test1 - Radio Buttons
    public void testRadioButton() throws Exception {
        solo.waitForActivity("MainActivity");
        boolean actual = solo.searchText(solo.getString(R.string.text_intro));
        assertEquals("Select a Button Text not found", true, actual);

        //check if Android Button is selected?
        actual = solo.isRadioButtonChecked(0);
        assertEquals("Android Button is checked", false, actual);

        //check if iPhone Button is selected
        actual = solo.isRadioButtonChecked(1);
        assertEquals("iPhone Button is checked", false, actual);

        //select Radio Button Android
        solo.clickOnRadioButton(0);

        actual = solo.isRadioButtonChecked("Android");
        assertEquals("Android Button not selected", true, actual);

        //select Radio Button iPhone
        solo.clickOnRadioButton(1);

        actual = solo.isRadioButtonChecked("iPhone");
        assertEquals("iPhone Button not selected", true, actual);
        solo.clickOnRadioButton(1);
        solo.clickOnRadioButton(0);
    }

    //Test2 -- Toggle Buttons
    public void testToggleButton() throws Exception {
        solo.waitForActivity("MainActivity");
        boolean actual = solo.searchText("Select a Button");
        assertEquals("Select a Button Text not found", true, actual);

        //search for toggle button
        actual = solo.searchToggleButton("OFF");
        assertEquals("Toggle Button not found", true, actual);

        //Turn ON toggle button
        solo.clickOnToggleButton("OFF");
        actual = solo.isToggleButtonChecked(0);
        boolean toast = solo.searchText("Toggle Button is ON");
        assertEquals("Toast :: Toggle Button is OFF", true, toast);
        assertEquals("Toggle Button is OFF", true, actual);

        //Turn OFF toggle button
        solo.clickOnToggleButton("ON");
        actual = solo.isToggleButtonChecked(0);
        toast = solo.searchText("Toggle Button is OFF");

        assertEquals("Toggle Button is ON", false, actual);
        assertEquals("Toast :: Toggle Button is ON", true, toast);

    }

    // Test 3 -- Edit Text
    public void testEditField() throws Exception {
        solo.waitForActivity("MainActivity");
        boolean actual = solo.searchText("Select a Button");
        assertEquals("Select a Button Text not found", true, actual);

        solo.clickOnEditText(0);
        String str_act = "Hi how are you";
        solo.enterText(0, str_act);

        actual = solo.searchEditText(str_act);
        solo.clearEditText(0);
        assertEquals("text entered is not matching", true, actual);
    }

    //test4 -- check boxes
    public void testCheckBoxes() throws Exception {
        solo.waitForActivity("MainActivity");
        boolean actual = solo.searchText("Select a Button");
        assertEquals("Select a Button Text not found", true, actual);

        solo.clearEditText(0);

        //click on Image Button
        solo.clickOnImageButton(0);
        assertTrue(solo.waitForText("Image Button is selected"));

        //look for activity_robo_check_boxes text
        actual = solo.searchText("Windows");
        assertEquals("Windows text not found", true, actual);

        //select activity_robo_check_boxes
        solo.clickOnCheckBox(0);

        //look for submit button
        actual = solo.searchButton("Submit");
        assertEquals("Submit Button not found", true, actual);

        //click on the submit button
        solo.clickOnButton("Submit");
        //look for the Toast message seen on the screen
        assertTrue(solo.waitForText("Android OS is Selected"));

        //assert if selected or not ?
        actual = solo.isCheckBoxChecked(0);
        assertEquals("Android OS is not Selected", true, actual);

        // uncheck and assert again
        solo.clickOnCheckBox(0);
        actual = solo.isCheckBoxChecked(0);
        assertEquals("Android OS is Selected", false, actual);

        //select two checkboxes and click on submit and look for the Toast
        solo.clickOnCheckBox(0);
        solo.clickOnCheckBox(2);
        solo.clickOnButton("Submit");
        assertTrue(solo.waitForText("Android and iPhone are Selected"));

        //uncheck previous selected
        solo.clickOnCheckBox(0);
        solo.clickOnCheckBox(2);

        //select two checkboxes and click on submit and look for the Toast
        solo.clickOnCheckBox(0);
        solo.clickOnCheckBox(1);
        solo.clickOnButton("Submit");
        assertTrue(solo.waitForText("Android and Windows are Selected"));

        //uncheck previous selected
        solo.clickOnCheckBox(0);
        solo.clickOnCheckBox(1);

        //select two checkboxes and click on submit and look for the Toast
        solo.clickOnCheckBox(2);
        solo.clickOnCheckBox(1);
        solo.clickOnButton("Submit");
        assertTrue(solo.waitForText("Windows and iPhone are Selected"));

        //uncheck previous selected
        solo.clickOnCheckBox(2);
        solo.clickOnCheckBox(1);

        //select two checkboxes and click on submit and look for the Toast
        solo.clickOnCheckBox(2);
        solo.clickOnCheckBox(1);
        solo.clickOnCheckBox(0);
        solo.clickOnButton("Submit");
        solo.takeScreenshot("Checkbox_All_OS-selected");
        assertTrue(solo.waitForText("Android, Windows and iPhone OS are Selected"));

        //assert if selected or not ?
        if ((solo.isCheckBoxChecked(0)) && (solo.isCheckBoxChecked(1)) && (solo.isCheckBoxChecked(2))) {
            actual = true;
        } else {
            actual = false;
        }
        assertEquals("All three OS are Not Selected", true, actual);

        //unselect the checkboxes
        solo.clickOnCheckBox(2);
        solo.clickOnCheckBox(1);
        solo.clickOnCheckBox(0);
        //assert if selected or not ?
        if ((!solo.isCheckBoxChecked(0)) && (!solo.isCheckBoxChecked(1)) && (!solo.isCheckBoxChecked(2))) {
            actual = true;
        } else {
            actual = false;
        }
        assertEquals("All three OS are Selected", true, actual);

        solo.clickOnButton("Submit");
        solo.takeScreenshot("Checkbox_no_OS-selected");
        assertTrue(solo.waitForText("None of the OS is Selected"));

    }

    //Test 5 :: spinner Test
    public void testSpinner() throws Exception {
        solo.waitForActivity("MainActivity");
        boolean actual = solo.searchText("Select a Button");
        assertEquals("Select a Button Text not found", true, actual);

        solo.clearEditText(0);


        //click on Image Button
        solo.clickOnImageButton(0);
        assertTrue(solo.waitForText("Image Button is selected"));

        actual = solo.searchText("Select Month");
        assertEquals("Spinner text not found", true, actual);

        //select spinner item
        solo.pressSpinnerItem(0, 1);
        actual = solo.isSpinnerTextSelected(0, "January");
        assertEquals("spinner item January is not selected", true, actual);

        /*
        if we want to select Spinner item “March”, it’s index will not be 3. Please note that, the index calculation in Spinners will be different from the other items.
        Spinner item index is calculated based on Current Spinner item selected.  In this case, since our current item selected is ‘January’,
        to select the item ‘March’, we need to move our position to two steps forward, this can be achieved by using ‘solo.pressSpinnerItem(0,2)’.
        in this statement first argument is for the Spinner, second argument is the reference to spinner item i.e. ‘March
        *
        * */

        //select spinner item
        solo.pressSpinnerItem(0, 2);
        actual = solo.isSpinnerTextSelected(0, "March");
        assertEquals("spinner item March is not selected", true, actual);

        //select spinner item
        solo.pressSpinnerItem(0, 2);
        actual = solo.isSpinnerTextSelected(0, "May");
        assertEquals("spinner item May is not selected", true, actual);

        //select spinner item
        solo.pressSpinnerItem(0, 2);
        actual = solo.isSpinnerTextSelected(0, "July");
        assertEquals("spinner item July is not selected", true, actual);

        //select spinner item
        solo.pressSpinnerItem(0, 3);
        actual = solo.isSpinnerTextSelected(0, "October");
        assertEquals("spinner item October is not selected", true, actual);

        //select spinner item
        solo.pressSpinnerItem(0, -1);
        actual = solo.isSpinnerTextSelected(0, "September");
        assertEquals("spinner item September is not selected", true, actual);

        //select spinner item
        solo.pressSpinnerItem(0, 2);
        actual = solo.isSpinnerTextSelected(0, "November");
        assertEquals("spinner item November is not selected", true, actual);

        //select spinner item
        solo.pressSpinnerItem(0, 1);
        actual = solo.isSpinnerTextSelected(0, "December");
        assertEquals("spinner item December is not selected", true, actual);
    }

    //Test Progress bar
    public void testProgressBar() throws Exception {
        solo.waitForActivity("MainActivity");
        boolean actual = solo.searchText("Select a Button");
        assertEquals("Select a Button Text not found", true, actual);

        solo.clickOnEditText(0);
        solo.enterText(0, "starting Test");

        solo.setProgressBar(0, 10);
        solo.setProgressBar(0, 20);
        solo.setProgressBar(0, 30);
        solo.setProgressBar(0, 40);
        solo.setProgressBar(0, 50);
        solo.setProgressBar(0, 10);

        solo.setProgressBar(1, 10);
        solo.setProgressBar(1, 20);
        solo.setProgressBar(1, 30);
        solo.setProgressBar(1, 40);
        solo.setProgressBar(1, 50);
        solo.setProgressBar(1, 10);

        solo.setProgressBar(2, 2);
        solo.setProgressBar(2, 3);
        solo.setProgressBar(2, 5);
        solo.setProgressBar(2, 7);
        solo.setProgressBar(2, 8);
        solo.setProgressBar(2, 10);

        solo.clearEditText(0);
        solo.enterText(0, "Test completed");
    }


    public void testListView() throws Exception {
        //select 'Product 1'
        solo.clickInList(1);
        assertTrue(solo.waitForText("Product 1 selected"));
        solo.goBack();

        //scroll to index 6 and first item.
        solo.scrollListToLine(0,6);
        solo.clickInList(1);
        assertTrue(solo.waitForText("Product 7 selected"));
        solo.goBack();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

}
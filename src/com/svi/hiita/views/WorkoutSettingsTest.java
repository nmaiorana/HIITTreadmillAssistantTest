package com.svi.hiita.views;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.svi.hiit.views.WorkoutSettings;

public class WorkoutSettingsTest extends android.test.ActivityInstrumentationTestCase2<WorkoutSettings> {

	private WorkoutSettings mActivity;
	private Spinner mSpinner;
	private SpinnerAdapter mSprintSpeedSelection;

	public WorkoutSettingsTest() {
		super("com.android.example.spinner", WorkoutSettings.class);
	}

	@Before
	public void setUp() throws Exception {
		setActivityInitialTouchMode(false);

		mActivity = getActivity();

		mSpinner = (Spinner) mActivity.findViewById(com.svi.hiita.R.id.sprint_speed_selection);

		mSprintSpeedSelection = mSpinner.getAdapter();
	}

	@Test
	public void testPreConditions() {
		Assert.assertTrue(mSpinner.getOnItemSelectedListener() !=  null);
		Assert.assertTrue(mSprintSpeedSelection != null);
		Assert.assertEquals(2, mSprintSpeedSelection.getCount());
	}

}

package com.svi.hiita.models.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.svi.hiit.models.Interval;
import com.svi.hiit.models.SpeedIntervalWorkout;

public class SpeedIntervalWorkoutTest {
	SpeedIntervalWorkout intervalWorkout;
	
	@Before
	public void setUp() {
		intervalWorkout = new SpeedIntervalWorkout();
		intervalWorkout.addInterval(new Interval(Interval.INTERVAL_TYPE_WARMUP, 5, 30));
		intervalWorkout.addInterval(new Interval(Interval.INTERVAL_TYPE_SPRINT, 20, 30));
		intervalWorkout.addInterval(new Interval(Interval.INTERVAL_TYPE_REST, 5, 30));
		intervalWorkout.addInterval(new Interval(Interval.INTERVAL_TYPE_SPRINT, 20, 30));
		intervalWorkout.addInterval(new Interval(Interval.INTERVAL_TYPE_REST, 5, 30));
		intervalWorkout.addInterval(new Interval(Interval.INTERVAL_TYPE_SPRINT, 20, 30));
		intervalWorkout.addInterval(new Interval(Interval.INTERVAL_TYPE_REST, 5, 30));
		intervalWorkout.addInterval(new Interval(Interval.INTERVAL_TYPE_COOLDOWN, 5, 30));
	}

	@Test
	public void testAddInterval() {
		int intervals = 0;
		intervalWorkout = new SpeedIntervalWorkout();
		intervalWorkout.addInterval(new Interval(Interval.INTERVAL_TYPE_WARMUP, 5, 30));
		Assert.assertEquals("Added interval",++intervals, intervalWorkout.getIntervals().size());
		intervalWorkout.addInterval(new Interval(Interval.INTERVAL_TYPE_SPRINT, 5, 30));
		Assert.assertEquals("Added sprint interval",1, intervalWorkout.getNumberOfSpeedIntervals());
		Assert.assertEquals("Added interval",++intervals, intervalWorkout.getIntervals().size());
		intervalWorkout.addInterval(new Interval(Interval.INTERVAL_TYPE_REST, 5, 30));
		Assert.assertEquals("Added interval",++intervals, intervalWorkout.getIntervals().size());
		intervalWorkout.addInterval(new Interval(Interval.INTERVAL_TYPE_COOLDOWN, 5, 30));
		Assert.assertEquals("Added interval",++intervals, intervalWorkout.getIntervals().size());
	}

	@Test
	public void testStartWorkout() {
		intervalWorkout.startWorkout();
		Assert.assertTrue(intervalWorkout.isWorkoutStarted());
		Assert.assertEquals(intervalWorkout.getIntervals().get(0), intervalWorkout.getCurrentInterval());
	}

	@Test
	public void testPauseResumeWorkout() {
		intervalWorkout.startWorkout();
		intervalWorkout.pauseWorkout();
		Assert.assertTrue(intervalWorkout.isWorkoutPaused());
		intervalWorkout.resumeWorkout();
		Assert.assertEquals(intervalWorkout.getIntervals().get(0), intervalWorkout.getCurrentInterval());
		intervalWorkout.advanceToNextInterval(); // Should advance to first sprint
		intervalWorkout.pauseWorkout();
		intervalWorkout.resumeWorkout();
		Assert.assertEquals(intervalWorkout.getIntervals().get(0), intervalWorkout.getCurrentInterval());
		intervalWorkout.advanceToNextInterval(); // Should advance to first sprint
		intervalWorkout.advanceToNextInterval(); // Should advance to first rest
		intervalWorkout.pauseWorkout();
		intervalWorkout.resumeWorkout();
		Assert.assertEquals(intervalWorkout.getIntervals().get(2), intervalWorkout.getCurrentInterval());
	}

	@Test
	public void testIsFirstInterval() {
		Assert.assertTrue(intervalWorkout.isFirstInterval());
		intervalWorkout.startWorkout();
		intervalWorkout.advanceToNextInterval();
		Assert.assertFalse(intervalWorkout.isFirstInterval());
	}

	@Test
	public void testIsLastInterval() {
		intervalWorkout = new SpeedIntervalWorkout();
		intervalWorkout.addInterval(new Interval(Interval.INTERVAL_TYPE_WARMUP, 5, 30));
		intervalWorkout.addInterval(new Interval(Interval.INTERVAL_TYPE_SPRINT, 5, 30));
		Assert.assertFalse("At last interval", intervalWorkout.isLastInterval());
		intervalWorkout.startWorkout();
		intervalWorkout.advanceToNextInterval();
		Assert.assertTrue("Not at last interval", intervalWorkout.isLastInterval());
	}

	@Test
	public void testAdvanceToNextInterval() {
		intervalWorkout.startWorkout();
		intervalWorkout.advanceToNextInterval();
		Interval interval = intervalWorkout.getCurrentInterval();
		Assert.assertEquals(1, intervalWorkout.getIntervals().indexOf(interval));
	}

	@Test
	public void testGetWorkoutTimeInSeconds() {
		Assert.assertEquals(8*30, intervalWorkout.computeIntervalTimeInSeconds());
	}

	@Test
	public void testGetNumberOfSpeedIntervals() {
		Assert.assertEquals(3, intervalWorkout.getNumberOfSpeedIntervals());
	}

	@Test
	public void testGetNumberOfSpeedIntervalsCompleted() {
		intervalWorkout.startWorkout();
		Assert.assertEquals(0, intervalWorkout.getNumberOfSpeedIntervalsCompleted());
		intervalWorkout.advanceToNextInterval();
		intervalWorkout.advanceToNextInterval();
		Assert.assertEquals(1, intervalWorkout.getNumberOfSpeedIntervalsCompleted());
	}

	@Test
	public void testGetTimeWorkedOutInSeconds() {
		intervalWorkout.startWorkout();
		intervalWorkout.advanceToNextInterval();
		Assert.assertEquals(30, intervalWorkout.getTimeWorkedOutInSeconds());
	}

	@Test
	public void testIsWorkoutPaused() {
		intervalWorkout.pauseWorkout();
		Assert.assertTrue(intervalWorkout.isWorkoutPaused());
	}

	@Test
	public void testGetSpeedIntervalVelocity() {
		Assert.assertEquals(20, intervalWorkout.getSpeedIntervalVelocity());
	}

}

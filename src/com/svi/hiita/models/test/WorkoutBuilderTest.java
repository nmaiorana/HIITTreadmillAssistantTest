package com.svi.hiita.models.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.svi.hiit.models.CoolDownTypes;
import com.svi.hiit.models.SpeedIntervalWorkout;
import com.svi.hiit.models.WarmUpTypes;
import com.svi.hiit.models.WorkoutBuilder;

public class WorkoutBuilderTest {
	private SpeedIntervalWorkout intervalWorkout;
	private int sprintSpeed = 9;
	private int restSpeed = 3;

	@Before
	public void setUp() {
		WorkoutBuilder workoutBuilder = new WorkoutBuilder();
		workoutBuilder.setWarmUpType(WarmUpTypes.STANDARD);
		workoutBuilder.setNumberOfIntervals(6);
		workoutBuilder.setSprintIntervalTime(60);
		workoutBuilder.setSprintSpeed(sprintSpeed);
		workoutBuilder.setRestIntervalTime(120);
		workoutBuilder.setRestSpeed(restSpeed);
		workoutBuilder.setCoolDownType(CoolDownTypes.STANDARD);
		intervalWorkout = workoutBuilder.createIntervals();
	}

	@Test
	public void testWorkoutTimeInSeconds() {
		int warmUpTime = 60 + 120 + 120;
		int coolDownTime = 60 + 120 + 120;
		int sprintTime = 6 * 60;
		int restTime = 6 * 120;
		int expectedTime = warmUpTime + coolDownTime + sprintTime + restTime;
		Assert.assertEquals(6, intervalWorkout.getNumberOfSpeedIntervals());
		Assert.assertEquals(expectedTime, intervalWorkout.computeIntervalTimeInSeconds());
	}

	@Test
	public void testCreatedIntervals() {
		Assert.assertEquals(3+6+6+3, intervalWorkout.getIntervals().size());
		Assert.assertEquals(6, intervalWorkout.getNumberOfSpeedIntervals());
	}

	@Test
	public void testSpeedIntervalVelocity() {
		Assert.assertEquals(sprintSpeed, intervalWorkout.getSpeedIntervalVelocity());
	}

}
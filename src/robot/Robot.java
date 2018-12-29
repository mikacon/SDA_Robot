/*
 *	  Copyright (C) 2016  John H. Gaby
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, version 3 of the License.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *    
 *    Contact: robotics@gabysoft.com
 */

package robot;

import robotCore.IterativeRobot;
import robotCore.Logger;
import subsystem.DriveSubsystem;
import subsystem.ExampleSubsystem;
import robotWpi.command.Scheduler;
import robotCore.Timer;

public class Robot extends IterativeRobot 
{
	public static final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
	public static final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
	public static OI m_OI = null;
	

	private Timer m_timer = new Timer();
	
	Robot()
	{
		Logger.Log("Robot", 2, "Robot()");
	}
	
	/**
	 * Called once to initialize the robot
	 */
	@Override
    public void robotInit() 
    {
		m_driveSubsystem.Init();
		m_exampleSubsystem.Init();
		m_OI = new OI();
    }
    
	/*
	 * Called at the start of autonomous mode
	 */
	@Override
    public void autonomousInit() 
    {
		Logger.ResetElapsedTime();
		Logger.Log("Robot", 2, "autonomousInit()");
    }

    /**
     * Called periodically during autonomous
     */
	@Override
    public void autonomousPeriodic() 
    {
		Logger.Log("Robot",  -1, "autonomousPeriodic()");
		
		Scheduler.getInstance().run();
		
		Sleep(10);
    }

	/**
	 * Called at the start of teleop mode
	 */
	@Override
	public void teleopInit()
	{
		Logger.ResetElapsedTime();
		Logger.Log("Robot", 2, "teleopInit()");
		m_driveSubsystem.SetPower(0.5,  0.5);
		m_timer.reset();
	}
	
	/**
     * Called periodically during operator control
     */
	@Override
	public void teleopPeriodic()
	{
		Logger.Log("Robot", -1, "teleopPeriodic()");
		
		if (m_timer.get() >= 2)
		{
			m_driveSubsystem.SetPower(0, 0);
		}
		
		Scheduler.getInstance().run();
		
		Sleep(10);
    }
	
	/**
	 * Called a the start of test mode
	 */
	@Override
	public void testInit()
	{
		Logger.ResetElapsedTime();
		Logger.Log("Robot", 2, "testInit()");
	}
    
    /**
     * Called periodically during test mode
     */
	@Override
    public void testPeriodic() 
	{
		Logger.Log("Robot", 0, "testPeriodic()");
		
		Sleep(10);
    }
 	
	/**
	 * Main program entry point
	 * 
	 */
    public static void main(String args[]) 
    {
    	Robot Robot = new Robot();
    	
    	Robot.Start(args);
    }

}

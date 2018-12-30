
package commands;

import robot.Robot;
import robotCore.Logger;
import robotCore.Timer;
import robotWpi.command.Command;

/**
 *
 */
public class DriveForTimeCommand extends Command 
{
    private Timer m_timer = new Timer();
    private double m_power;
    private double m_time;

	public DriveForTimeCommand(double power, double time) 
    {
    	Logger.Log("DriveForTimeCommand", 3, "DriveForTimeCommand()");
    	
    	m_power	= power;
    	m_time	= time;
    	
        // Use requires() here to declare subsystem dependencies
        requires(Robot.m_driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Logger.Log("DriveForTimeCommand", 2, "initialize()");
    	
    	Robot.m_driveSubsystem.SetPower(m_power, m_power);
    	m_timer.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Logger.Log("DriveForTimeCommand", -1, "execute()");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	Logger.Log("DriveForTimeCommand", -1, "isFinished()");
        
    	return(m_timer.get() >= m_time);
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Logger.Log("DriveForTimeCommand", 2, "end()");
    	
    	Robot.m_driveSubsystem.SetPower(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Logger.Log("DriveForTimeCommand", 2, "interrupted()");
    	
    	Robot.m_driveSubsystem.SetPower(0, 0);
    }
}
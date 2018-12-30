package commands;

import robotCore.Logger;
import robotWpi.command.Command;
import robot.Robot;
 
/**
 *
 */
public class DriveForDistanceCommand extends Command 
{
    private double m_power;
    private double m_distance;
    
    public DriveForDistanceCommand(double power, double distance) 
    {
        Logger.Log("DriveForDistanceCommand", 3, "DriveForDistanceCommand()");
        
        m_power        = power;
        m_distance    = distance;
        
        // Use requires() here to declare subsystem dependencies
        requires(Robot.m_driveSubsystem);
    }
 
    // Called just before this Command runs the first time
    protected void initialize() 
    {
        Logger.Log("DriveForDistanceCommand", 2, "initialize()");
        
        Robot.m_driveSubsystem.GetLeftEncoder().reset();
        
        Robot.m_driveSubsystem.SetPower(m_power, m_power);
    }
 
    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        Logger.Log("DriveForDistanceCommand", -1, "execute()");
    }
 
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        Logger.Log("DriveForDistanceCommand", -1, "isFinished()");
        
        return(Robot.m_driveSubsystem.GetLeftEncoder().get() >= m_distance);
    }
 
    // Called once after isFinished returns true
    protected void end() 
    {
        Logger.Log("DriveForDistanceCommand", 2, "end()");
        
        Robot.m_driveSubsystem.SetPower(0, 0);
    }
 
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
        Logger.Log("DriveForDistanceCommand", 2, "interrupted()");
        
        end();
    }
}
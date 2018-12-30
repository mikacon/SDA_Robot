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

package commands;

import robotCore.Joystick;
import robotCore.Logger;
import robotWpi.command.Command;
import robot.Robot;
/**
 *
 */
public class ArcadeDriveCommand extends Command 
{
	private Joystick m_joystick = new Joystick(0);
	 
    public ArcadeDriveCommand() 
    {
    	Logger.Log("ArcadeDriveCommand", 3, "ArcadeDriveCommand()");
    	
        // Use requires() here to declare subsystem dependencies
//        requires(Robot.m_exampleSubsystem);
    		requires(Robot.m_driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Logger.Log("ArcadeDriveCommand", 2, "initialize()");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        Logger.Log("ArcadeDriveCommand", -1, "execute()");
        
        double    y    = m_joystick.getY();
        double    x    = m_joystick.getX();
        x    = x * x * x;
        y    = y * y * y;
        
        Robot.m_driveSubsystem.SetPower(y+x, y-x);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	Logger.Log("ArcadeDriveCommand", -1, "isFinished()");
        
    	return (false);
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Logger.Log("ArcadeDriveCommand", 2, "end()");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Logger.Log("ArcadeDriveCommand", 2, "interrupted()");
    }
}

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

package subsystem;

import robotCore.Logger;
import robotCore.PWMMotor;
import robotWpi.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem 
{
	private static final int k_rightMotorPWMPin	= 5;
	private static final int k_rightMotorDirPin	= 4;
	private static final int k_leftMotorPWMPin	= 6;
	private static final int k_leftMotorDirPin	= 7;

	private PWMMotor		m_leftMotor;
	private PWMMotor		m_rightMotor;

    public void initDefaultCommand() 
    {
    		Logger.Log("DriveSubsystem", 2, "initDefaultCommand()");
    }
    
    public void Init()
    {
    		Logger.Log("DriveSubsystem", 2, "Init()");
    	
    	m_rightMotor = new PWMMotor(k_rightMotorPWMPin, k_rightMotorDirPin);
    	m_leftMotor = new PWMMotor(k_leftMotorPWMPin, k_leftMotorDirPin);
    }
    
    public void SetPower(double leftPower, double rightPower)
    {
    	m_rightMotor.set(rightPower);
    	m_leftMotor.set(leftPower);
    }
}


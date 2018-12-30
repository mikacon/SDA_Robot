package subsystem;
 
import robotWpi.command.Subsystem;
import commands.ArcadeDriveCommand;
import robotCore.Logger;
import robotCore.PWMMotor;

/**
 *
 */
public class DriveSubsystem extends Subsystem 
{
    private static final int k_rightMotorPWMPin    = 6;
    private static final int k_rightMotorDirPin    = 7;
    private static final int k_leftMotorPWMPin    = 5;
    private static final int k_leftMotorDirPin    = 4;
    
    private PWMMotor        m_leftMotor = new PWMMotor(k_rightMotorPWMPin, k_rightMotorDirPin);
    private PWMMotor        m_rightMotor = new PWMMotor(k_leftMotorPWMPin, k_leftMotorDirPin);
    
    public void initDefaultCommand() 
    {
        Logger.Log("DriveSubsystem", 2, "initDefaultCommand()");
        setDefaultCommand(new ArcadeDriveCommand());
    }
    
    public void SetPower(double leftPower, double rightPower)
    {
        m_rightMotor.set(rightPower);
        m_leftMotor.set(leftPower);
    }    
}
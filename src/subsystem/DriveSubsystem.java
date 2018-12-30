package subsystem;
 
import robotWpi.command.Subsystem;
import commands.ArcadeDriveCommand;
import robotCore.Encoder;
import robotCore.Logger;
import robotCore.PWMMotor;

/**
 *
 */
public class DriveSubsystem extends Subsystem 
{
   
	private static final int k_rightMotorPWMPin	= 6;
	private static final int k_rightMotorDirPin	= 7;
	private static final int k_leftMotorPWMPin	= 5;
	private static final int k_leftMotorDirPin	= 4;
	private static final int k_leftEncoderPin1	= 0;
	private static final int k_leftEncoderPin2	= 1;
	private static final int k_rightEncoderPin1	= 2;
	private static final int k_rightEncoderPin2	= 3;
	
    private PWMMotor        m_leftMotor = new PWMMotor(k_rightMotorPWMPin, k_rightMotorDirPin);
    private PWMMotor        m_rightMotor = new PWMMotor(k_leftMotorPWMPin, k_leftMotorDirPin);
    
    private Encoder     m_rightEncoder    = new Encoder(robotCore.Encoder.EncoderType.Analog, k_rightEncoderPin1, k_rightEncoderPin2);
    private Encoder     m_leftEncoder    = new Encoder(robotCore.Encoder.EncoderType.Analog, k_leftEncoderPin1, k_leftEncoderPin2);
    
    public DriveSubsystem()
    {
        m_leftEncoder.setInverted(true);
    }

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
    public Encoder GetLeftEncoder()
    {
    	return(m_leftEncoder);
    }
    
    public Encoder GetRightEncoder()
    {
    	return(m_rightEncoder);
    }   
}
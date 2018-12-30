
package robot;
 
import robotWpi.command.Scheduler;
import robotCore.IterativeRobot;
import robotCore.Logger;
import subsystem.DriveSubsystem;
import subsystem.ExampleSubsystem;
 
public class Robot extends IterativeRobot 
{
    public static ExampleSubsystem m_exampleSubsystem;
    public static OI m_OI;
    public static DriveSubsystem m_driveSubsystem;
    
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
        Logger.Log("Robot", 2, "robotiInit()");
        
        m_driveSubsystem = new DriveSubsystem();
        m_exampleSubsystem = new ExampleSubsystem();
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
    }
    
    /**
     * Called periodically during operator control
     */
    @Override
    public void teleopPeriodic()
    {
        Logger.Log("Robot", -1, "teleopPeriodic()");
        
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
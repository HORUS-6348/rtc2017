
package org.usfirst.frc.team6134.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team6134.robot.subsystems.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {


	public static OI oi;
	public static Garra garra;
	public static TrenMotriz trenMotriz; 
	public static Rodillo rodillo;
	public static Disparador disparador;
	Command Autonomo;


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	RobotMap.init();
    	trenMotriz = new TrenMotriz();
    	rodillo = new Rodillo();
    	garra = new Garra();
    	disparador = new Disparador();
    	oi = new OI();
        
    }
    public void disabledPeriodic() {
		
		trenMotriz.Paro();
		garra.parar();
	}
    public void autonomousInit(){
		 if (Autonomo != null) Autonomo.start();

		 RobotMap.motorR.set(1.0);
		 RobotMap.motorR1.set(1.0);
		 RobotMap.motorL.set(-1.0);
		 RobotMap.motorL1.set(-1.0);
		 Timer.delay(5);
		 RobotMap.motorR.set(1.0);
		 RobotMap.motorR1.set(1.0);
		 RobotMap.motorL.set(1.0);
		 RobotMap.motorL1.set(1.0);
	}
	 public void AutonomoPeriodic(){
	    	Scheduler.getInstance().run();
	    }
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		if (Autonomo != null) Autonomo.cancel();
    	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    }

    /**
     * This function is called periodically during operator control
     */    
    public void disabledInit(){

     }
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        trenMotriz.initDefaultCommand();
        garra.garra();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}

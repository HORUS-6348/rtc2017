package org.usfirst.frc.team6134.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public	static Jaguar motorR;
	public	static Jaguar motorL;
	public	static Victor motorR1;
	public	static Victor motorL1;
	public  static Victor motor1;
	public  static Victor motor2;
	public 	static Jaguar motor3; 
	public  static DigitalInput limit1;
	
	public static void init(){
		
		motorR = new Jaguar(0);
		motorL = new Jaguar(2);
		motorR1 = new Victor (1);
		motorL1 = new Victor(3);
		motor1= new Victor(6); //Garra
		motor2 = new Victor(4); //Disparador
		motor3 = new Jaguar(5); //rodillo
		limit1 = new DigitalInput(0);
		
		
	// For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	}
}

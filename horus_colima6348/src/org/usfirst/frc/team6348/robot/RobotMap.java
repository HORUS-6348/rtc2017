package org.usfirst.frc.team6348.robot;

import edu.wpi.first.wpilibj.Spark;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static Spark motor_sup_izq;
	public static Spark motor_sup_der;
	public static Spark motor_inf_izq;
	public static Spark motor_inf_der;
	
	public static void init(){
		motor_sup_izq = new Spark(0);
		motor_sup_der = new Spark(1);
		motor_inf_izq = new Spark(2);
		motor_inf_der = new Spark(3);
	}
}

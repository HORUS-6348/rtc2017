package org.usfirst.frc.team6348.robot;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Estos controladores pertenecen al tren motriz
	public static PWMSpeedController motor_izq;
	public static PWMSpeedController motor_der;
	public static PWMSpeedController escalador;
	
	public static void init(){
		//Inicializamos los controladores
		motor_izq = new Jaguar(0); // new Spark(0);
		motor_der = new VictorSP(1); // new Spark(1);
		escalador  = new VictorSP(2); // new Spark(2);
	}
	
}

package org.usfirst.frc.team6348.robot;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Estos Spark son los cuatro motores del tren motriz
	public static Jaguar motor_izq;
	public static VictorSP motor_der;
	public static VictorSP escalador;
	
	public static void init(){
		//Inicializamos los controladores
		motor_izq = new Jaguar(0);
		motor_der = new VictorSP(1);
		escalador  = new VictorSP(2);
	}
	
}

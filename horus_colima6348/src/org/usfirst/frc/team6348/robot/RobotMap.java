package org.usfirst.frc.team6348.robot;

import edu.wpi.first.wpilibj.Spark;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Estos Spark son los cuatro motores del tren motriz
	public static Spark motor_izq;
	public static Spark motor_der;
	public static Spark lanzador;
	
	public static void init(){
		//Inicializamos los controladores
		motor_izq = new Spark(0);
		motor_der = new Spark(1);
		lanzador  = new Spark(2);
	}
}

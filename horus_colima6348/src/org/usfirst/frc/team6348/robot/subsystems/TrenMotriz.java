package org.usfirst.frc.team6348.robot.subsystems;

import org.usfirst.frc.team6348.robot.Robot;
import org.usfirst.frc.team6348.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;



public class TrenMotriz extends Subsystem {

	Spark motor_sup_izq = RobotMap.motor_sup_izq;
	Spark motor_sup_der = RobotMap.motor_sup_der;
	Spark motor_inf_izq = RobotMap.motor_inf_izq;
	Spark motor_inf_der = RobotMap.motor_inf_der;
	
	public void parar(){
		motor_sup_izq.set(0);
		motor_sup_der.set(0);
		motor_inf_izq.set(0);
		motor_inf_der.set(0);
	}
	
	/*
	 * min -> 1
	 * max -> -1
	 * */
	private double smoothBetween(double min, double max, double degrees){
		double interval = max - min;
		double normalized = (max - degrees) / interval;
		return 2*normalized - 1;
	}
	
	private double motorIzq(double degrees, double gatillo){
		if(degrees < 90){
			return 1 * gatillo;
		} else if(degrees < 180){
			return smoothBetween(90, 180, degrees) * gatillo;
		} else if(degrees < 270){
			return -1 * gatillo;
		} else if(degrees < 360){
			return smoothBetween(360, 270, degrees) * gatillo;
		} else {
			return 0;
		}
	}
	
	private double motorDer(double degrees, double gatillo){
		if(degrees < 90){
			return smoothBetween(0, 90, degrees) * gatillo;
		} else if(degrees < 180){
			return 1 * gatillo;
		} else if(degrees < 270){
			return smoothBetween(270, 360, degrees) * gatillo;
		} else if(degrees < 360){
			return -1 * gatillo;
		} else {
			return 0;
		}
	}
	
	public void initDefaultCommand(){
		double x = Robot.oi.stick0.getRawAxis(0);
		double y = Robot.oi.stick0.getRawAxis(1);
		double gatillo = Robot.oi.stick0.getRawAxis(3);
		
		double deadZone = 0.15;
		
		
		//Los joysticks tienen una zona muerta en su centro, para evitar esto
		//si ambos valores están muy cercanos al mismo (0.15) los dejamos en cero.
		if(Math.abs(x) < deadZone && Math.abs(y) < deadZone){
			x       = 0;
			y       = 0;
			gatillo = 0;
		}
		
		double degrees = Math.atan2(y,x);
		
		double potencia_izq = motorIzq(degrees, gatillo);
		double potencia_der = motorDer(degrees, gatillo);
		
		/* Esta es la variante del código para cuatro motores 
		motor_sup_izq.set(potencia_izq);
		motor_inf_izq.set(potencia_izq);
		motor_sup_der.set(potencia_der);
		motor_sup_izq.set(potencia_der);
		*/
		
		//Variante con dos motores
		
		motor_sup_izq.set(potencia_izq);
		motor_sup_der.set(potencia_der);
		
		//Feliz cumpleaños fish...!!!
	}
}


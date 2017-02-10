package org.usfirst.frc.team6348.robot.subsystems;

import org.usfirst.frc.team6348.robot.Robot;
import org.usfirst.frc.team6348.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;



public class TrenMotriz extends Subsystem {

	Spark motor_izq = RobotMap.motor_izq;
	Spark motor_der = RobotMap.motor_der;
	
	public void parar(){
		motor_izq.set(0);
		motor_der.set(0);
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
	
	private double getMotorIzq(double degrees, double gatillo){
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
	
	private double getMotorDer(double degrees, double gatillo){
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
		
		double potencia_izq = getMotorIzq(degrees, gatillo);
		double potencia_der = getMotorDer(degrees, gatillo);
		
		//Gracias!!!

		motor_izq.set(potencia_izq);
		motor_der.set(potencia_der);

	}
}


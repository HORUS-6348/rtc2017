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
	 * min ->  1
	 * max -> -1
	 * */
	private double smoothBetween(double min, double max, double degrees){
		double interval = max - min;
		double normalized = (max - degrees) / interval;
		return 2*normalized - 1;
	}
	
	public double getMotorIzq(double degrees, double gatillo){
		if(degrees <= 90){
			return 1 * gatillo;
		} else if(degrees <= 180){
			return smoothBetween(90, 180, degrees) * gatillo;
		} else if(degrees <= 270){
			return -1 * gatillo;
		} else if(degrees <= 360){
			return smoothBetween(360, 270, degrees) * gatillo;
		} else {
			return 0;
		}
	}
	
	public double getMotorDer(double degrees, double gatillo){
		if(degrees <= 90){
			return smoothBetween(90, 0, degrees) * gatillo;
		} else if(degrees <= 180){
			return 1 * gatillo;
		} else if(degrees <= 270){
			return smoothBetween(180, 270, degrees) * gatillo;
		} else if(degrees <= 360){
			return -1 * gatillo;
		} else {
			return 0;
		}
	}
	
	private double toDegrees(double angrad){
		double degrees = Math.toDegrees(angrad);
		if(degrees < 0){
			return -degrees;
		} else {
			return 360 - degrees;
		}
	}
	
	public void initDefaultCommand(){
		double gatillo = Robot.oi.stick0.getRawAxis(3);
		double otroGatillo = Robot.oi.stick0.getRawAxis(2);
		
		if(otroGatillo > 0.05){
			gatillo = (otroGatillo * 0.5) + (gatillo * 0.5);
		}
		
		boolean stateA = Robot.oi.A.get();
		boolean stateX = Robot.oi.X.get();
		boolean stateY = Robot.oi.Y.get();

		if(stateA){
			gatillo = 0.25;
		} else if(stateX){
			gatillo = 0.5;
		} else if(stateY){
			gatillo = 0.75;
		}
		
		double dPad = Robot.oi.stick0.getPOV(0);
		
		if(dPad == -1){ //Si no está presionado el D-PAD, usamos joystick
			double x = Robot.oi.stick0.getRawAxis(0);
			double y = Robot.oi.stick0.getRawAxis(1);
			
			
			double deadZone = 0.15;
			
			
			//Los joysticks tienen una zona muerta en su centro, para evitar esto
			//si ambos valores están muy cercanos al mismo (0.15) los dejamos en cero.
			if(Math.abs(x) < deadZone && Math.abs(y) < deadZone){
				x       = 0;
				y       = 0;
				gatillo = 0;
			}
			
			double radians = Math.atan2(y,x); //Esta función nos da la dirección del vector en radianes
			double degrees = toDegrees(radians); //Lo pasamos a grados y lo corregimos a nuestra orientación
			
			double potencia_izq = getMotorIzq(degrees, gatillo);
			double potencia_der = getMotorDer(degrees, gatillo);
			
			motor_izq.set(potencia_izq);
			motor_der.set(-0.95 * potencia_der);
			//System.out.println("INPUTS" + " gatillo: " + gatillo + " degrees: " + degrees + "  OUTPUTS izq: " + potencia_izq + " der: " + potencia_der + " izq_full: " + getMotorIzq(degrees, 1) + " der_full: " + getMotorDer(degrees, 1));
			
		} else { //Está presionado el D-PAD, por lo que toma prioridad sobre el joystick
			double potencia_izq = getMotorIzqDpad(dPad, gatillo);
			double potencia_der = getMotorDerDpad(dPad, gatillo);
			
			motor_izq.set(potencia_izq);
			motor_der.set(0.95 * potencia_der);
			System.out.println("INPUTS gatillo: " + gatillo + " dPad: " + dPad + "   OUTPUTS izq: " + potencia_izq + " der: " + potencia_der + " izq_full: " + getMotorIzqDpad(dPad, 1) + " der_full: " + getMotorDerDpad(dPad, 1));
			
		}	
	}

	private double getMotorIzqDpad(double pad, double gatillo){
		if(pad == 0){
			return 1 * gatillo;
		} else if(pad == 45){
			return 1 * gatillo;
		} else if(pad == 90){
			return 1 * gatillo;
		} else if(pad == 135){
			return -1 * gatillo;
		} else if(pad == 180){
			return -1 * gatillo;
		} else if(pad == 225){
			return  0 * gatillo;
		} else if(pad == 270){
			return -1 * gatillo;
		} else if(pad == 315){
			return 0 * gatillo;
		} else {
			return 0;
		}
	}
	private double getMotorDerDpad(double pad, double gatillo){
		if(pad == 0){
			return -1 * gatillo;
		} else if(pad == 45){
			return 0 * gatillo;
		} else if(pad == 90){
			return 1 * gatillo;
		} else if(pad == 135){
			return 0 * gatillo;
		} else if(pad == 180){
			return 1 * gatillo;
		} else if(pad == 225){
			return 1 * gatillo;
		} else if(pad == 270){
			return -1 * gatillo;
		} else if(pad == 315){
			return -1 * gatillo;
		} else {
			return 0;
		}
	}


}


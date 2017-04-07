package org.usfirst.frc.team6348.robot.subsystems;

import org.usfirst.frc.team6348.robot.Robot;
import org.usfirst.frc.team6348.robot.RobotMap;
import org.usfirst.frc.team6348.robot.commands.ManejoTeleoperado;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class TrenMotriz extends Subsystem {

	PWMSpeedController motor_izq = RobotMap.motor_izq;
	PWMSpeedController motor_der = RobotMap.motor_der;
	
	/*
	 * min ->  1
	 * max -> -1
	 * */
	public double smoothBetween(double min, double max, double degrees){
		double interval = max - min;
		double normalized = (max - degrees) / interval;
		return 2*normalized - 1;
	}
	public  double getMotorIzq(double degrees, double gatillo){
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
	public  double getMotorDer(double degrees, double gatillo){
		if(degrees <= 90){
			return -smoothBetween(90, 0, degrees) * gatillo;
		} else if(degrees <= 180){
			return -1 * gatillo;
		} else if(degrees <= 270){
			return -smoothBetween(180, 270, degrees) * gatillo;
		} else if(degrees <= 360){
			return 1 * gatillo;
		} else {
			return 0;
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
	private double toDegrees(double angrad){
		double degrees = Math.toDegrees(angrad);
		if(degrees < 0){
			return -degrees;
		} else {
			return 360 - degrees;
		}
	}
	
	public void stop(){
		drive(0, 0);
	}
	
	public void driveInDirection(double reference, double gyro, double gatillo){
		double gyroAngle = -(gyro - reference);
		double kP = 5 +  Math.abs(gyro);
		
		SmartDashboard.putNumber("Reference angle", reference);
		SmartDashboard.putNumber("Gyro angle", gyro);
		SmartDashboard.putNumber("kP", kP);
		
		if(gyroAngle > 0){
			drive(90 - kP, gatillo);
		} else {
			drive(90 + kP , gatillo);
		}
	}
	
	public void drive(Joystick stick, boolean dpad){
		if(dpad && stick.getPOV() != -1){
			driveDpad(stick);
		} else {
			driveStick(stick);
		}
		
	}
	
	public void set_motors(double potencia_izq, double potencia_der){
		Robot.data.putNumber("motors/left", potencia_izq);
		Robot.data.putNumber("motors/right", potencia_izq);
		
		motor_izq.set(potencia_izq);
		motor_der.set(potencia_der);
	}
		
	public void drive(double heading, double gatillo){
		double potencia_izq = getMotorIzq(heading, gatillo);
		double potencia_der = getMotorDer(heading, gatillo);
		
		set_motors(potencia_izq, potencia_der);
		
	}
	
	private void driveDpad(Joystick stick){
		double gatillo = getGatillo(stick);
		int    dPad    = stick.getPOV();
		 
		double potencia_izq = getMotorIzqDpad(dPad, gatillo);
		double potencia_der = getMotorDerDpad(dPad, gatillo);
		
		set_motors(potencia_izq, potencia_der);
		
	}
	
	private void driveStick(Joystick stick){
		double gatillo = getGatillo(stick);
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
		
		drive(degrees, gatillo);
	}

	private double getGatillo(Joystick stick){
		double gatillo = stick.getRawAxis(3);
		double otroGatillo = stick.getRawAxis(2);
		
		gatillo = (gatillo * 0.5) + (otroGatillo * 0.5); 
		
		boolean stateA = stick.getRawButton(1);
		boolean stateX = stick.getRawButton(3);
		boolean stateY = stick.getRawButton(4);

		if(stateA){
			gatillo = 0.40;
		} else if(stateX){
			gatillo = 0.60;
		} else if(stateY){
			gatillo = 0.80;
		}
		
		return gatillo;
		
	}
	
	public void initDefaultCommand(){
		setDefaultCommand(new ManejoTeleoperado());
	}



}


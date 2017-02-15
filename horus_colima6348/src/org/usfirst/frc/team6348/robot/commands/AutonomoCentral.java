package org.usfirst.frc.team6348.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6348.robot.Robot;
import org.usfirst.frc.team6348.robot.RobotMap;

/**
 *
 */
public class AutonomoCentral extends Command {
	private Timer timer;
	private boolean isFinished = false;
	private double referenceAngle;
	
	public AutonomoCentral() {
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		referenceAngle = Robot.oi.gyro.getAngle();
		timer = new Timer();
		timer.reset();
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if(timer.get() < 1){
			RobotMap.motor_der.set(-0.3);
			RobotMap.motor_izq.set(0.3);
		} else if(timer.get() < 3.5){
			double gyroAngle = -(90 - Robot.oi.gyro.getAngle());
			double kP        = 1.03 +  Math.abs(gyroAngle) * .02;
			
			SmartDashboard.putNumber("Reference angle", 90);
			SmartDashboard.putNumber("Gyro angle", gyroAngle);
			SmartDashboard.putNumber("kP", kP);
			
			if(gyroAngle > 0){
				RobotMap.motor_der.set(-1.0 * kP * Robot.trenMotriz.getMotorDer(90, 0.3));
				RobotMap.motor_izq.set( 1.0 * Robot.trenMotriz.getMotorIzq(90, 0.3));
			} else {
				RobotMap.motor_der.set(-1.0 * Robot.trenMotriz.getMotorDer(90, 0.3));
				RobotMap.motor_izq.set( 1.0 * kP * Robot.trenMotriz.getMotorIzq(90, 0.3));
			}
		} else if(timer.get() < 3.5){
			isFinished = true;
			RobotMap.motor_der.set(-0.4);
			RobotMap.motor_izq.set(0);
		} else if(timer.get() < 5.5){
			double gyroAngle = -(referenceAngle - Robot.oi.gyro.getAngle());
			double kP        = 1.03 +  Math.abs(gyroAngle) * .02;
			
			SmartDashboard.putNumber("Reference angle", referenceAngle);
			SmartDashboard.putNumber("Gyro angle", gyroAngle);
			SmartDashboard.putNumber("kP", kP);
				
				
			if(gyroAngle > 0){
				RobotMap.motor_der.set(-1.0 * kP * Robot.trenMotriz.getMotorDer(90, 0.3));
				RobotMap.motor_izq.set( 1.0 * Robot.trenMotriz.getMotorIzq(90, 0.3));
			} else {
				RobotMap.motor_der.set(-1.0 * Robot.trenMotriz.getMotorDer(90, 0.3));
				RobotMap.motor_izq.set( 1.0 * kP * Robot.trenMotriz.getMotorIzq(90, 0.3));
			}
		} else {
			isFinished = true;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isFinished;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		RobotMap.motor_der.set(0);
		RobotMap.motor_izq.set(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}

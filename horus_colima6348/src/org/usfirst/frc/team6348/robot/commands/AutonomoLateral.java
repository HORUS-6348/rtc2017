package org.usfirst.frc.team6348.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6348.robot.Robot;
import org.usfirst.frc.team6348.robot.RobotMap;

/**
 *
 */
public class AutonomoLateral extends Command {
	private double referenceAngle;
	
	public AutonomoLateral() {
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		referenceAngle = Robot.oi.gyro.getAngle();
		SmartDashboard.putNumber("Reference angle", referenceAngle);
		RobotMap.motor_der.set(-1.0 * Robot.trenMotriz.getMotorDer(90, 0.3));
		RobotMap.motor_izq.set( 1.0 * Robot.trenMotriz.getMotorIzq(90, 0.3));
		
		setTimeout(6);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double gyroAngle = -(referenceAngle - Robot.oi.gyro.getAngle());
		double kP        = 1.03 +  Math.abs(gyroAngle) * .02;
		
		SmartDashboard.putNumber("Gyro angle", gyroAngle);
		SmartDashboard.putNumber("kP", kP);
			
			
		if(gyroAngle > 0){
			RobotMap.motor_der.set(-1.0 * kP * Robot.trenMotriz.getMotorDer(90, 0.3));
			RobotMap.motor_izq.set( 1.0 * Robot.trenMotriz.getMotorIzq(90, 0.3));
		} else {
			RobotMap.motor_der.set(-1.0 * Robot.trenMotriz.getMotorDer(90, 0.3));
			RobotMap.motor_izq.set( 1.0 * kP * Robot.trenMotriz.getMotorIzq(90, 0.3));
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isTimedOut();
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

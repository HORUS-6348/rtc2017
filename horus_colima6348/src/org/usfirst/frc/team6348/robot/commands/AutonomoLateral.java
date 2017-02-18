package org.usfirst.frc.team6348.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6348.robot.Robot;

/**
 *
 */
public class AutonomoLateral extends Command {
	private double referenceAngle;
	
	public AutonomoLateral() {
		requires(Robot.trenMotriz);
	}
	

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		referenceAngle = Robot.oi.gyro.getAngle();
		SmartDashboard.putNumber("Reference angle", referenceAngle);
		
		Robot.trenMotriz.drive(90, 0.3);		
		setTimeout(6);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double gyroAngle      = -(referenceAngle - Robot.oi.gyro.getAngle());
		double angleDeviation = (gyroAngle * 4) + 2;
		
		SmartDashboard.putNumber("Gyro angle", gyroAngle);
		SmartDashboard.putNumber("Angle correction", angleDeviation);
		
		if(gyroAngle > 0){
			Robot.trenMotriz.drive(90 + angleDeviation, 0.3);
		} else {
			Robot.trenMotriz.drive(90 - angleDeviation, 0.3);
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
		Robot.trenMotriz.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}

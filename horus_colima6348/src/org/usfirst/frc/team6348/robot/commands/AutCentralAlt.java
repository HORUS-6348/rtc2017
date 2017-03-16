package org.usfirst.frc.team6348.robot.commands;

import org.usfirst.frc.team6348.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutCentralAlt extends Command {

	private double fastCalibration;
	private Timer timer;

	public AutCentralAlt() {
		requires(Robot.trenMotriz);
	}
	

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		fastCalibration = Robot.oi.gyro.getAngle();
		setTimeout(6);
		timer = new Timer();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if(timer.get() < 3){
			Robot.trenMotriz.driveInDirection(fastCalibration, Robot.oi.gyro.getAngle(), 0.4);
		} else if(timer.get() < 10){
			Robot.trenMotriz.stop();
		} else if(timer.get() < 12){
			Robot.trenMotriz.drive(270, 0.3);
		} else if(timer.get() < 15){
			Robot.trenMotriz.drive(270, 0.4);
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

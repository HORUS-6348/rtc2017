package org.usfirst.frc.team6348.robot.commands;

import org.usfirst.frc.team6348.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomoLateralEngrane extends Command {
	private double fastCalibration;
	private Timer timer;
	private boolean turnComplete;

	public AutonomoLateralEngrane() {
		requires(Robot.trenMotriz);
	}
	
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		fastCalibration = Robot.oi.gyro.getAngle();
		timer = new Timer();
		setTimeout(15);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if(timer.get() < 1){
			Robot.trenMotriz.driveInDirection(fastCalibration, Robot.oi.gyro.getAngle(), 0.6);
		} else if(timer.get() < 5|| !turnComplete){
			Robot.trenMotriz.set_motors(0.4, 0);
			if(Robot.oi.gyro.getAngle() > 45){
				Robot.trenMotriz.stop();
				turnComplete = true;
			}
		} else if(timer.get() < 8 && turnComplete){
			Robot.trenMotriz.drive(90, 0.4);
		} else if(timer.get() < 10 && turnComplete){
			Robot.trenMotriz.drive(90, 0.3);
		} else if(timer.get() < 13 && turnComplete){
			Robot.trenMotriz.drive(270, 0.3);
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

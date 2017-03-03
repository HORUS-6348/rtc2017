package org.usfirst.frc.team6348.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6348.robot.Robot;



public class AutonomoCentralLadear extends Command {
	private Timer timer = new Timer();
	private double fastCalibration;
	

	public AutonomoCentralLadear() {
		requires(Robot.trenMotriz);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		fastCalibration = -(Robot.oi.gyro.getAngle() - 90);
		setTimeout(2);
		timer.start();

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if(timer.get() < 0.25){
			Robot.trenMotriz.driveInDirection(135, fastCalibration, 0.4);
		} else if(timer.get() < 0.75){
			Robot.trenMotriz.driveInDirection(45, fastCalibration, 0.4);
		} else if(timer.get() < 1.00){
			Robot.trenMotriz.driveInDirection(90, fastCalibration, 0.4);
		} else{
			timer.reset();
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

package org.usfirst.frc.team6348.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6348.robot.Robot;



public class AutonomoCentralInicial extends Command{
	private double fastCalibration;

	public AutonomoCentralInicial() {
		requires(Robot.trenMotriz);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		fastCalibration = Robot.oi.gyro.getAngle();
		setTimeout(1.5);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.trenMotriz.driveInDirection(fastCalibration, Robot.oi.gyro.getAngle(), 0.5);

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

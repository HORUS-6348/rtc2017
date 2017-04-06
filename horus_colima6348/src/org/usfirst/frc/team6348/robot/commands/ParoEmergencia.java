package org.usfirst.frc.team6348.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6348.robot.Robot;

/**
 *
 */
public class ParoEmergencia extends Command {
	public ParoEmergencia() {
		requires(Robot.trenMotriz);
		requires(Robot.escalador);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.trenMotriz.stop();
		Robot.escalador.stop();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		Robot.trenMotriz.stop();
	}
}

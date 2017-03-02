package org.usfirst.frc.team6348.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6348.robot.Robot;

/**
 *
 */
public class Lanzar extends Command {
	
	public Lanzar() {
		requires(Robot.lanzador);
	}
	

	@Override
	protected void initialize() {
		Robot.lanzador.iniciarMotor();

	}

	@Override
	protected void execute() {

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
	}
}

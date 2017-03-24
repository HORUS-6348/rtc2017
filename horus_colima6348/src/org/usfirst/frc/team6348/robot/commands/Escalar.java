package org.usfirst.frc.team6348.robot.commands;

import org.usfirst.frc.team6348.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Escalar extends Command {
	
		public Escalar(){
			requires(Robot.escalador);
		}

	// Called just before this Command runs the first time
		@Override
		protected void initialize() {
			Robot.escalador.escalar(0);
		}

		// Called repeatedly when this Command is scheduled to run
		@Override
		protected void execute() {
			double rawAxis = Robot.oi.stick1.getRawAxis(3);
			double potencia = Robot.trenMotriz.smoothBetween(-1, 1, rawAxis);
			
			Robot.escalador.escalar(potencia);
		}

		// Make this return true when this Command no longer needs to run execute()
		@Override
		protected boolean isFinished() {
			return false;
		}

		// Called once after isFinished returns true
		@Override
		protected void end() {
			Robot.escalador.escalar(0);
		}

		// Called when another command which requires one or more of the same
		// subsystems is scheduled to run
		@Override
		protected void interrupted() {
		}

}

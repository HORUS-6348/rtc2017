package org.usfirst.frc.team6134.robot.commands;

import org.usfirst.frc.team6134.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Dentro extends Command {
	
    public Dentro() {
    	requires(Robot.disparador);	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
   
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.disparador.disparar();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.disparador.detener();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

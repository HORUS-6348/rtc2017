package org.usfirst.frc.team6348.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team6348.robot.Robot;



public class AutonomoCentral extends CommandGroup{

	public AutonomoCentral() {
		addSequential(new AutonomoCentral());
	}
	
}

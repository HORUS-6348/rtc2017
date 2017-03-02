package org.usfirst.frc.team6348.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomoCentral extends CommandGroup {
	public AutonomoCentral(){
		addSequential(new AutonomoCentralInicial());
		addSequential(new AutonomoCentralLadear());
	}

}

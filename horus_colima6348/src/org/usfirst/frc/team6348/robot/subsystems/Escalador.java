package org.usfirst.frc.team6348.robot.subsystems;

import org.usfirst.frc.team6348.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Escalador extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	public void escalar(double potencia){
		RobotMap.escalador.set(potencia);
	}
	

}

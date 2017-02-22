package org.usfirst.frc.team6348.robot.subsystems;

import org.usfirst.frc.team6348.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Lanzador extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	public void iniciarMotor(){
		RobotMap.lanzador.set(.95);
	}
	
	public void pararMotor(){
		RobotMap.lanzador.set(0);
	}

}

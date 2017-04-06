package org.usfirst.frc.team6348.robot.subsystems;

import org.usfirst.frc.team6348.robot.RobotMap;
import org.usfirst.frc.team6348.robot.commands.Escalar;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Escalador extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Escalar());
	}
	
	public void escalar(double potencia){
		SmartDashboard.putNumber("Motor escalador: ", potencia);
		
		RobotMap.escalador.set(potencia);
	}

	public void stop() {
		RobotMap.escalador.set(0);
		
	}
	

}

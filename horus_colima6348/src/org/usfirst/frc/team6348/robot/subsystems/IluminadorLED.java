package org.usfirst.frc.team6348.robot.subsystems;

import org.usfirst.frc.team6348.robot.Robot;
import org.usfirst.frc.team6348.robot.RobotMap;
import org.usfirst.frc.team6348.robot.commands.CambiarColor;
import org.usfirst.frc.team6348.robot.commands.Escalar;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IluminadorLED extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CambiarColor());
	}
	
	public void iluminar(int r, int g, int b){
		SmartDashboard.putString("Color: ", String.format("RGB: (%d, %d, %d)", r, g, b));
	}
	

	public void stop() {
		iluminar(0, 0, 0);		
	}
	

}

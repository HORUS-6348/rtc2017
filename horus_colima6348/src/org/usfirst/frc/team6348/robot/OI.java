package org.usfirst.frc.team6348.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team6348.robot.commands.ExampleCommand;
import org.usfirst.frc.team6348.robot.commands.ParoEmergencia;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick stick0 = new Joystick(0); //Joystick de Xbox 360
	public Joystick stick1 = new Joystick(1); //Joystick Logitech
	
	public Button B = new JoystickButton(stick0, 3); //Botón B para paro de emergencia
	public Button X = new JoystickButton(stick0, 2);
	public Button Y = new JoystickButton(stick0, 1);
	public Button A = new JoystickButton(stick0, 0);
	
	public OI(){
		B.whileHeld(new ParoEmergencia()); //TODO: Implementar paro de emergencia
	}
	
	
	

}

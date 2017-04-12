package org.usfirst.frc.team6348.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class JoystickConfig {
	
	public double a;
	public double x;
	public double y;
	public double rt;
	public double lt;
	
	public JoystickConfig(){
		a = 0.4;
		x = 0.6;
		y = 0.8;
		rt = 0.5;
		lt = 0.5;
	}
	
	public void update(double aIn, double xIn, double yIn, double rtIn, double ltIn){
		a = aIn;
		x = xIn;
		y = yIn;
		rt = rtIn;
		lt = ltIn;
	}
	
	public void update(NetworkTable table){
		a = table.getNumber("drive/a", 0.4);
		x = table.getNumber("drive/x", 0.6);
		y = table.getNumber("drive/y", 0.8);
		rt = table.getNumber("drive/rt", 0.5);
		lt = table.getNumber("drive/lt", 0.5);
	}

}

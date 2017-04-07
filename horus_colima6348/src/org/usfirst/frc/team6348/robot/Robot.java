
package org.usfirst.frc.team6348.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6348.robot.commands.AutonomoCentral;
import org.usfirst.frc.team6348.robot.commands.AutonomoLateralEngrane;
import org.usfirst.frc.team6348.robot.commands.AutonomoCentral;
import org.usfirst.frc.team6348.robot.commands.AutonomoLateralLinea;
import org.usfirst.frc.team6348.robot.subsystems.Escalador;
import org.usfirst.frc.team6348.robot.subsystems.TrenMotriz;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static TrenMotriz trenMotriz;
	public static Escalador escalador;
	public static OI oi;
	
	PowerDistributionPanel pdp;
	Command autonomousCommand;
	UsbCamera camera;
	
	NetworkTable data;


	@Override
	public void robotInit() {
		RobotMap.init();
		
		trenMotriz     = new TrenMotriz();
		escalador      = new Escalador();
		pdp            = new PowerDistributionPanel();
		oi             = new OI();
		

		setupGyro();
		setupCamera(640, 480, 24);
	}

	private void setupGyro() {
		oi.gyro.calibrate();
	}
	
	private void setupCamera(int width, int height, int fps){
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setVideoMode(VideoMode.PixelFormat.kMJPEG, width, height, fps);
		
	}

	@Override
	public void disabledInit() {
		trenMotriz.stop();
		escalador.stop();
	}


	@Override
	public void autonomousInit() {
		String autoMode = data.getString("autoMode/selectedMode", "forward");
		
		switch(autoMode){
			case "forward": autonomousCommand = new AutonomoLateralLinea();
							break;
			case "left"   : autonomousCommand = new AutonomoLateralEngrane();
							break;
			case "center" : autonomousCommand = new AutonomoCentral();
							break;
			default		  : autonomousCommand = new AutonomoLateralLinea();
							break;
		}
		
		autonomousCommand.start();

	}
	
	@Override
	public void autonomousPeriodic() {	
		Scheduler.getInstance().run();
		
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null){
			autonomousCommand.cancel();
		}
		
		trenMotriz.stop();
		trenMotriz.initDefaultCommand();
	}


	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void robotPeriodic() {
		updateNetworkTables();
	}

	private void updateNetworkTables() {
		data.putNumber("power/batteryVoltage", pdp.getVoltage());
		data.putNumber("power/totalPowerUse", pdp.getTotalPower());
		data.putNumber("power/left", pdp.getCurrent(14));
		data.putNumber("power/right", pdp.getCurrent(15));
		data.putNumber("power/climber", pdp.getCurrent(2));
		data.putNumber("power/VRM", pdp.getCurrent(9));
		
		data.putNumber("match/time", DriverStation.getInstance().getMatchTime());
		data.putString("match/phase", getMatchPhase());
		
		data.putNumber("sensors/gyroAngle", Robot.oi.gyro.getAngle());
		
		
	}

	private String getMatchPhase() {
		double matchTime = DriverStation.getInstance().getMatchTime();
		boolean auto     = DriverStation.getInstance().isAutonomous();
		
		if(auto){
			return "auto";
		} else{
			if(matchTime < 95 || matchTime > 110){
				return "climb";
			} else{
				return "teleop";
			}
		}
	}

}

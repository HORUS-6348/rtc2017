package org.usfirst.frc.team6348.robot.commands;

import org.usfirst.frc.team6348.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Command;

public class CambiarColor extends Command {
	
		public CambiarColor(){
			requires(Robot.iluminadorLED);
		}


		// Called repeatedly when this Command is scheduled to run
		@Override
		protected void execute() {
			Alliance currentAlliance = DriverStation.getInstance().getAlliance();
			double   matchTimer      = DriverStation.getInstance().getMatchTime();
			
			if(matchTimer > 110){
				iluminarAlianza(currentAlliance);
			} else if(matchTimer > 95){
				flashear((int) matchTimer);
			} else {
				iluminarAlianza(currentAlliance);
			}
		}

		private void flashear(int matchTimer) {
			if(matchTimer % 2 == 0){
				Robot.iluminadorLED.iluminar(255, 255, 0);
			} else {
				Robot.iluminadorLED.iluminar(255, 140, 0);
			}
			
		}


		private void iluminarAlianza(Alliance currentAlliance) {
			if(currentAlliance == DriverStation.Alliance.Blue){
				Robot.iluminadorLED.iluminar(true);
			} else {
				Robot.iluminadorLED.iluminar(false);
			}
			
		}


		// Make this return true when this Command no longer needs to run execute()
		@Override
		protected boolean isFinished() {
			return false;
		}


}

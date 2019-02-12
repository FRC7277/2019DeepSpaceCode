/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.utils.VisionThread;

import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class AutoAlign extends Command {
  
  private double centerX;

  public AutoAlign() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);

    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      
    //Settnig the center of the X value to the right on
    centerX = Robot.visionProcess.getCenterX();
    //Making it so that the robot turn until the thing return zero
    double turn = centerX - (320 / 2);
    Robot.drivetrain.getDrive().arcadeDrive(0, turn * 0.005);
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Math.abs(centerX) <= 0.01);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.getDrive().arcadeDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}

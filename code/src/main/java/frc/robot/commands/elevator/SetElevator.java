/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;
import frc.robot.RobotMap;

public class SetElevator extends BaseElevator {

  private double position;

  public SetElevator(double position) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super();
    this.position = position;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    super.initialize();
    // Setup timeout TODO static reference? this is safety measure though
    setTimeout(RobotMap.elevatorTimeout);
    // Set target position and enable pids
    Robot.elevator.setSetpoint(this.position);
    Robot.elevator.enable();
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Elevator Distance", Robot.elevatorEncoder.getDistance());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.elevator.onTarget() || isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.elevator.setSpeed(0.0);
    Robot.elevator.disable();
    System.out.println("PID set done");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}

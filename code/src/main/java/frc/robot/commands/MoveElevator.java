/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DigitalInput;

import frc.robot.Robot;
import frc.robot.RobotMap;

public class MoveElevator extends Command {

  private DigitalInput endSwitch;
  private double power;
  private double timeout;

  public MoveElevator(int startSwitch, int endSwitch, double timeout) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);

    // Create digital input
    this.endSwitch = new DigitalInput(endSwitch);

    //Reference elevator speed
    this.power = RobotMap.elevatorSpeed * ((endSwitch > startSwitch) ? 1 : -1);

    // Reference timeout
    this.timeout = timeout;

  }

  public MoveElevator(int startSwitch, int endSwitch) {
    this(startSwitch, endSwitch, RobotMap.elevatorTimeout);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.setSpeed(this.power);
    setTimeout(this.timeout);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (this.endSwitch.get() || isTimedOut());
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.elevator.setSpeed(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

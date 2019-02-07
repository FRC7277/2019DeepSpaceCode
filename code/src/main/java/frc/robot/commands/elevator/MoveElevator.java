/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DigitalInput;

import frc.robot.Robot;
import frc.robot.RobotMap;

public class MoveElevator extends BaseElevator {

  private DigitalInput endSwitch;
  private int target;
  private double power;
  private double timeout;

  private boolean autoFlag;

  public MoveElevator(int start, int target, double timeout) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super();

    // Create digital input
    this.target = target;
    this.endSwitch = getSwitches()[target];

    //Reference elevator speed
    this.power = RobotMap.elevatorSpeed * ((target > start) ? 1 : -1);

    // Reference timeout
    this.timeout = timeout;

    this.autoFlag = false;

  }

  public MoveElevator(int start, int target) {
    this(start, target, RobotMap.elevatorTimeout);
  }

  public MoveElevator(int target, double timeout) {
    super();

    // Reference target
    this.target = target;
    this.endSwitch = getSwitches()[target];

    this.timeout = timeout;

    this.autoFlag = true;
  }

  public MoveElevator(int target) {
    this(target, RobotMap.elevatorTimeout);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    if (autoFlag) {
      this.power = RobotMap.elevatorSpeed * ((this.target > Robot.lastEP) ? 1 : -1);
    }

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
  /*
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
  */
}

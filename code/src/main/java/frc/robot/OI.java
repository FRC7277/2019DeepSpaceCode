/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.RobotMap;
import frc.robot.commands.PowerIntake;
import frc.robot.commands.elevator.DistanceElevator;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  private Joystick joystick = new Joystick(RobotMap.joystick);
  private XboxController controller = new XboxController(RobotMap.controller);

  private Button inButton = new JoystickButton(controller, RobotMap.xButton);
  private Button outButton = new JoystickButton(controller, RobotMap.yButton);

  private Button upButton = new JoystickButton(controller, RobotMap.lbButton);

  /**
   * Scales a double between -1 and 1 (inclusive) to provide
   * smoother and more controlled movement vectors
   */
  public double scaleJoystickInput(double input) {
    /*
    if (input >= 0) {
      return (input * input);
    } else {
      return (input * -input);
    }
    */

    // TODO the 0.5 is a safety feature that could be removed
    return input*this.getSlider(joystick)*0.5;

  }

  /**
   * Gets the value of a slider from 0-1
   * @param joy
   * @return
   */
  public double getSlider(Joystick joy) {

    return (joystick.getThrottle()+1.0)/2.0;

  }

  public OI() {
    
  }

  /**
   * Attaches buttons to commands
   */
  public void linkButtons() {

    //Assigning commands to buttons
    inButton.whileHeld(new PowerIntake(-1));
    outButton.whileHeld(new PowerIntake(1));

    upButton.whenPressed(new DistanceElevator(40));

    //alignButton.whenPressed(new AutoAlign());

  }

  //Getter for the Controller objects
  public Joystick getJoystick() {
    return this.joystick;
  }

  public XboxController getController() {
    return this.controller;
  }
  
}

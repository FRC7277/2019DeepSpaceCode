/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DMC60;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

//import frc.robot.commands.ControlElevator;

//import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Intake subsystem.
 */
public class Intake extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    // Define motor object for each motor
    private DMC60 left;
    private DMC60 right;
    
    // Define speed controller group
    private SpeedControllerGroup main;
    
    /**
     * Takes 4 port integers, left-back, right-back, left-front, right-front
     * Takes a joystick object to pass to the default JoystickDrive command
     */
    public Intake(int left, int right) {
        
        // Create Motor objects using parameters
        this.left = new DMC60(left);
        this.right = new DMC60(right);
        
        // Create group of controllers
        this.main = new SpeedControllerGroup(this.left, this.right);
        
    }
    
    /**
     * Constructs a drivetrain using RobotMap and OI
     */
    public Intake() {
        
        // Pass robotmap into DI constructor
        this(RobotMap.leftIntake, RobotMap.rightIntake);

    }
    
    /**
     * Accessor for the motor group
     */
    public SpeedControllerGroup getMotors() {
        return this.main;
    }

    // Method to set motors speed
    /**
     * Sets the speed of the intake motors
     * @param speed Value to set the motor speed to
     */
    public void setSpeed(double speed) {
        main.set(speed);
    }
    
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        
        //setDefaultCommand(new ControlElevator(Robot.m_oi.getController()));
    }

}

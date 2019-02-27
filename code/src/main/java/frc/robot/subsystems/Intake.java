/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SpeedController;

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
    private SpeedController motor;
    
    /**
     * Takes 4 port integers, left-back, right-back, left-front, right-front
     * Takes a joystick object to pass to the default JoystickDrive command
     */
    public Intake(int motor) {
        
        // Create Motor objects using parameters
        this.motor = new Talon(motor);
        
    }
    
    /**
     * Constructs a drivetrain using RobotMap and OI
     */
    public Intake() {
        
        // Pass robotmap into DI constructor
        this(RobotMap.intake);

    }
    
    /**
     * Accessor for the motor
     */
    public SpeedController getMotor() {
        return this.motor;
    }

    // Method to set motors speed
    /**
     * Sets the speed of the intake motors
     * @param speed Value to set the motor speed to
     */
    public void setSpeed(double speed) {
        motor.set(speed);
    }
    
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        
        //setDefaultCommand(new ControlElevator(Robot.m_oi.getController()));
    }

}

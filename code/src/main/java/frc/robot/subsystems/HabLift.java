/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedController;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
// TODO figure out actualy motor controller

//import frc.robot.commands.ControlElevator;

//import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Intake subsystem.
 */
public class HabLift extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    // Define motor object
    private SpeedController motor;
    
    /**
     * Takes 4 port integers, left-back, right-back, left-front, right-front
     * Takes a joystick object to pass to the default JoystickDrive command
     */
    public HabLift(int port) {
        
        // Create Motor objects using parameters
        this.motor = new WPI_VictorSPX(port);
        
    }
    
    /**
     * Constructs a drivetrain using RobotMap and OI
     */
    public HabLift() {
        
        // Pass robotmap into DI constructor
        this(RobotMap.habLift);

    }
    
    /**
     * Accessor for the motor group
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

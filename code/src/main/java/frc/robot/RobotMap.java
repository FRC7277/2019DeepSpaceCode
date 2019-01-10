/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;

    // Drive train motors
    public static final int leftBack = 2;
    public static final int leftFront = 4;
    public static final int rightBack = 1;
    public static final int rightFront = 3;
    
    // Joystick number
    public static final int joystick = 0;

    // Elevator lift motors
    public static final int leftElevator = 5;
    public static final int rightElevator = 6;

    // Elevator speed constant
    public static final double elevatorSpeed = 1.0;
    
}

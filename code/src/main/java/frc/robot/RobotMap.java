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

    // Drive train motors ports
    public static final int rightBack = 3;
    public static final int rightFront = 2;
    public static final int leftBack = 0;
    public static final int leftFront = 1;
    
    // Joystick number
    public static final int joystick = 0;
    public static final int controller = 1;

    // Elevator lift motors ports
    public static final int elevatorMotor = 4;

    // Intake motors ports
    public static final int intake = 5;

    // Encoder ports
    public static final int encoderAChannel = 8;
    public static final int encoderBChannel = 9;

    // Encoder data
    // Number of physical slots in encoder, 90 per revolution
    public static final double encoderRevolutionTicks = 90;
    // Radius of the gear attached to the encoder (in inches)
    public static final double encoderGearRadius = 0;
    // This is the distance the elevator rises (in inches) in 1 pulse of the encoder
    // Currently experimental, should find radius of gear to mathematicall compute this
    public static final double encoderPulseDistance = 44.5/481.0;

    // Elevator joystick speed modifier
    public static final double elevatorModifier = 0.50;
    // Passive voltage always sent to elevator on top of inputs
    // Ensures that the elavator doesnt fall when not controlled
    public static final double elevatorPassive = 0.07;
    // Ratio between elevator travel time and distance (will be outdated with encoder)
    public static final double elevatorTimePerInch = 0.01;
    // Tolerance for elevator position (in inches)
    public static final double elevatorTolerance = 1;

    // Controller button mappings
    public static final int aButton = 1;
    public static final int bButton = 2; //B button
    public static final int xButton = 3;
    public static final int yButton = 4;

    public static final int lbButton = 5;
    public static final int rbButton = 6;

    public static final int leftJoyButton = 9;
    public static final int rightJoyButton = 10;

    //Joystick button mappings
    public static final int joystickSideButton = 1;
    
}

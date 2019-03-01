/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;

import frc.robot.RobotMap;

/**
 * Subsystem designed for using an encoder connected to a chain or something by
 * a gear
 */
public class ChainEncoder extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private Encoder encoder;

    public ChainEncoder(int channelA, int channelB, double pulseDistance) {

        // Create encoder object using channel parameters
        this.encoder = new Encoder(channelA, channelB);

        // Set encoder scale factor
        this.encoder.setDistancePerPulse(pulseDistance);

        // Reset encoder
        this.reset();

    }

    /**
     * Uses RobotMap to create the default elevator encoder
     */
    public ChainEncoder() {
        this(RobotMap.encoderAChannel, RobotMap.encoderBChannel, RobotMap.encoderPulseDistance);
    }

    /**
     * Returns the Encoder (WPI) object that is attached to the subsytem
     * Used when more control over the encoder is needed than the subsystem class provides
     * @return Encoder object
     */
    public Encoder getEncoder() {
        return this.encoder;
    }

    /**
     * Use this method to find how far the elevator has travelled since last reset
     * 
     * @return double representing travel distance in inches
     */
    public double getDistance() {
        return this.encoder.getDistance();
    }

    /**
     * Resets encoder value (to 0 ticks)
     */
    public void reset() {

        this.encoder.reset();

    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

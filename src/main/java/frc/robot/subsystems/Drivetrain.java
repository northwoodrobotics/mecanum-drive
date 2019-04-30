/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.TeleDrive;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * A mecanum drivetrain with WPI_VictorSPX motor controllers.
 */
public class Drivetrain extends Subsystem {
	private WPI_VictorSPX leftFront;
	private WPI_VictorSPX rightFront;
	private WPI_VictorSPX leftBack;
	private WPI_VictorSPX rightBack;

	public void init() {
		leftFront = new WPI_VictorSPX(RobotMap.drivetrainLF);
		rightFront = new WPI_VictorSPX(RobotMap.drivetrainRF);
		leftBack = new WPI_VictorSPX(RobotMap.drivetrainLB);
		rightBack = new WPI_VictorSPX(RobotMap.drivetrainRB);

		leftFront.setInverted(RobotMap.lfInverted);
		rightFront.setInverted(RobotMap.rfInverted);
		leftBack.setInverted(RobotMap.lbInverted);
		rightBack.setInverted(RobotMap.rbInverted);
	}

	public void drive(double forward, double strafe, double rotate) {
		leftFront.set(forward + strafe + rotate);
		rightFront.set(forward - strafe - rotate);
		leftBack.set(forward - strafe + rotate);
		rightBack.set(forward + strafe - rotate);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new TeleDrive(this));
	}
}

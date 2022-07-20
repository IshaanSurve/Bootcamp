// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

//modify port
  private final I2C.Port i2cPort = I2C.Port.kMXP;
  public final ColorSensorV3 sensor = new ColorSensorV3(i2cPort); 
  private final TalonFX motor1 = new TalonFX(10);  
  private final ColorMatch colorMatch = new ColorMatch();
  public final Color blueTarget = new Color(0,0,255);
  public final Color redTarget = new Color(255,0,0);
  public final Color greenTarget = new Color(0,255,0);

  public ExampleSubsystem() {
    colorMatch.addColorMatch(redTarget);
    colorMatch.addColorMatch(greenTarget);
    colorMatch.addColorMatch(blueTarget);   
  }

  @Override
  public void periodic() {
    System.out.println("ABCD");
    // This method will be called once per scheduler run
    while(true){
    Color abc = sensor.getColor();
    SmartDashboard.putNumber("RED", abc.red );
    SmartDashboard.putNumber("GREEN", abc.green );
    SmartDashboard.putNumber("BLUE", abc.blue );
    }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void drive(double speed){
    motor1.set(ControlMode.PercentOutput, speed);
  }

  public Color sense() {
    return sensor.getColor();   
  }
}

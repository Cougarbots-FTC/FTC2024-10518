package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Move Forward with Button A", group="Linear Opmode")
public class MoveForwardWithButtonA extends LinearOpMode {

    // Declare your motor variables
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    @Override
    public void runOpMode() {

        // Initialize the hardware variables using configuration names
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        // Set the direction of motors (reverse one side to move forward properly)
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Check if the 'A' button on the gamepad is pressed
            if (gamepad1.a) {
                // Set power to both motors to move forward while button A is pressed
                leftDrive.setPower(0.5);
                rightDrive.setPower(0.5);
            } else {
                // Stop the motors when button A is not pressed
                leftDrive.setPower(0);
                rightDrive.setPower(0);
            }

            // Display telemetry data to the driver station for debugging
            telemetry.addData("Status", "Running");
            telemetry.addData("Button A Pressed", gamepad1.a);
            telemetry.update();
        }
    }
}
import com.qualcomm.robotcore.eventloop.opmode.0pMode;
import com.qualcomm.robotcore.eventloop.opmode.Tele0p;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "tutorial")
public class tutorial extends OpMode {
    DcMotor motor;
    @override
    public void init() {
        telemetry.addData( caption: "Intialization:", value "is a sucess");
        telemetry update;
    }

    @override
    public void loop(){
    }
}
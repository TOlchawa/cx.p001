package pl.cx.p001.model.robot;

/**
 * Frame groups previous step outputs of subsystems: sensor, drive, actuator.
 * Used as input to Robot.process in the unified feedback model.
 * Format when packed: [S_LEN, S..., D_LEN, D..., A_LEN, A...]
 */
public final class Frame {
    private final float[] sensor;
    private final float[] drive;
    private final float[] actuator;

    public Frame(float[] sensor, float[] drive, float[] actuator) {
        this.sensor = sensor == null ? new float[0] : sensor;
        this.drive = drive == null ? new float[0] : drive;
        this.actuator = actuator == null ? new float[0] : actuator;
    }

    public float[] getSensor() { return sensor; }
    public float[] getDrive() { return drive; }
    public float[] getActuator() { return actuator; }
}

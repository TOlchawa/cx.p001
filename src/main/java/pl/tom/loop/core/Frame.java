package pl.tom.loop.core;

/**
 * Frame groups outputs from subsystems: sensor, drive, actuator.
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

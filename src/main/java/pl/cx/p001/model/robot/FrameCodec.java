package pl.cx.p001.model.robot;

import java.util.Arrays;

/**
 * Packs and unpacks frame vectors.
 * Format: [S_LEN, S..., D_LEN, D..., A_LEN, A...]
 */
public final class FrameCodec {
    private FrameCodec() {}

    public static float[] pack(float[] sensor, float[] drive, float[] actuator) {
        int sLen = sensor == null ? 0 : sensor.length;
        int dLen = drive == null ? 0 : drive.length;
        int aLen = actuator == null ? 0 : actuator.length;
        float[] out = new float[3 + sLen + dLen + aLen];
        int i = 0;
        out[i++] = sLen;
        if (sLen > 0) { System.arraycopy(sensor, 0, out, i, sLen); i += sLen; }
        out[i++] = dLen;
        if (dLen > 0) { System.arraycopy(drive, 0, out, i, dLen); i += dLen; }
        out[i++] = aLen;
        if (aLen > 0) { System.arraycopy(actuator, 0, out, i, aLen); }
        return out;
    }

    public static Frame unpack(float[] in) {
        if (in == null || in.length < 3) return new Frame(new float[0], new float[0], new float[0]);
        int i = 0;
        int sLen = clampLen((int) in[i++], in.length - i);
        float[] s = sLen > 0 ? Arrays.copyOfRange(in, i, i + sLen) : new float[0];
        i += sLen;
        int dLen = (i < in.length) ? clampLen((int) in[i++], in.length - i) : 0;
        float[] d = dLen > 0 ? Arrays.copyOfRange(in, i, i + dLen) : new float[0];
        i += dLen;
        int aLen = (i < in.length) ? clampLen((int) in[i++], in.length - i) : 0;
        float[] a = aLen > 0 ? Arrays.copyOfRange(in, i, i + aLen) : new float[0];
        return new Frame(s, d, a);
    }

    private static int clampLen(int len, int remain) {
        if (len < 0) return 0;
        return Math.min(len, Math.max(0, remain));
    }
}


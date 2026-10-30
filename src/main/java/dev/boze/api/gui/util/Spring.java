package dev.boze.api.gui.util;

import dev.boze.api.internal.Instances;

import java.util.function.DoubleSupplier;

/**
 * Values keyed by any object that ease toward their targets without overshooting, at the same
 * speed at any frame rate. Time passes on its own: each update moves a value by the time since
 * its last one.
 * <br>
 * A key seen for the first time starts at its target, and so does one that has not been updated
 * for a while, so something that was out of sight does not animate a change it missed.
 */
public interface Spring {
    /**
     * @param seconds About how long a value takes to settle on a new target; 0 jumps
     * @return New springs
     */
    static Spring settling(double seconds) {
        return settling(() -> seconds);
    }

    /**
     * @param seconds Read on every update, so the time can follow a setting; 0 jumps
     * @return New springs
     */
    static Spring settling(DoubleSupplier seconds) {
        return Instances.getGui().spring(seconds);
    }

    /**
     * Moves the value for {@code key} toward {@code target}.
     *
     * @return The current value
     */
    float update(Object key, float target);

    /**
     * Moves the value for {@code key} toward 1 while {@code on}, toward 0 otherwise.
     *
     * @return The current value, 0 to 1
     */
    default float update(Object key, boolean on) {
        return update(key, on ? 1F : 0F);
    }

    /**
     * Puts the value for {@code key} at {@code value}, standing still, for example to have a new
     * piece grow from 0.
     */
    void set(Object key, float value);

    /**
     * Forgets every value, so each key starts at its target again.
     */
    void clear();
}

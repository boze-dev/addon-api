package dev.boze.api.input;

import dev.boze.api.internal.Instances;

/**
 * Input Name Provider, used to get the name of a key or button
 */
public final class InputNameProvider {

    /**
     * Get the name of a bind
     *
     * @param bind
     * @return the name of the bind
     */
    public static String getBindName(Bind bind) {
        return bind.isButton() ? getButtonName(bind.getBind()) : getKeyName(bind.getBind());
    }

    /**
     * Get the name of a (keyboard) key
     *
     * @param key
     * @return the name of the key
     */
    public static String getKeyName(int key) {
        return Instances.getInput().getKeyName(key);
    }

    /**
     * Get the name of a (mouse) button
     *
     * @param button
     * @return the name of the button
     */
    public static String getButtonName(int button) {
        return Instances.getInput().getButtonName(button);
    }
}

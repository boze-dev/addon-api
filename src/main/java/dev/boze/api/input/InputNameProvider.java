package dev.boze.api.input;

/**
 * Input Name Provider, used to get the name of a key or button
 */
public interface InputNameProvider {

    /**
     * Get the name of a bind
     *
     * @param bind
     * @return the name of the bind
     */
    default String getBindName(Bind bind) {
        return bind.isButton() ? getButtonName(bind.getBind()) : getKeyName(bind.getBind());
    }

    /**
     * Get the name of a (keyboard) key
     *
     * @param key
     * @return the name of the key
     */
    String getKeyName(int key);

    /**
     * Get the name of a (mouse) button
     *
     * @param button
     * @return the name of the button
     */
    String getButtonName(int button);
}

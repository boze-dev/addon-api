package dev.boze.api;

import dev.boze.api.addon.Addon;
import dev.boze.api.addon.AddonModule;
import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.IEventBus;
import net.minecraft.client.Minecraft;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Boze API Instance
 * <br>
 * This class keeps track of all the addons registered
 * It also provides addons a way to register packages and subscribe to events
 */
public final class BozeInstance {

    /**
     * The instance of Boze API
     */
    public static final BozeInstance INSTANCE = new BozeInstance();

    private final ArrayList<Addon> addons = new ArrayList<>();

    private IEventBus EVENT_BUS = new EventBus();

    private BozeInstance() {

    }

    /**
     * Register an addon
     *
     * @param addon The addon to register
     */
    public void registerAddon(Addon addon) {
        assert Minecraft.getInstance().isSameThread();

        addons.add(addon);
    }

    /**
     * Get all registered addons
     *
     * @return list of all registered addon metadata and addons
     */
    public ArrayList<Addon> getAddons() {
        return addons;
    }

    /**
     * Gets a list of all modules registered by addons
     *
     * @return A list of all modules registered by addons
     */
    public List<AddonModule> getModules() {
        return addons.stream()
                .map(addon -> addon.modules)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    /**
     * Registers a (java) package into the event bus
     *
     * @param pkg The package to register
     */
    public void registerPackage(String pkg) {
        EVENT_BUS.registerLambdaFactory(pkg, (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
    }

    /**
     * Subscribes an object to listen to events
     *
     * @param listener The object to subscribe
     */
    public void subscribe(Object listener) {
        EVENT_BUS.subscribe(listener);
    }

    /**
     * Subscribes a class to listen to events
     *
     * @param listener The class to subscribe
     */
    public void subscribe(Class<?> listener) {
        EVENT_BUS.subscribe(listener);
    }

    /**
     * Unsubscribes an object to listen to events
     *
     * @param listener The object to unsubscribe
     */
    public void unsubscribe(Object listener) {
        EVENT_BUS.unsubscribe(listener);
    }

    /**
     * Unsubscribes a class to listen to events
     *
     * @param listener The class to unsubscribe
     */
    public void unsubscribe(Class<?> listener) {
        EVENT_BUS.unsubscribe(listener);
    }

    /**
     * Post an event
     * <br>
     * Use this to post custom events
     *
     * @param event The event to post
     */
    public void post(Object event) {
        EVENT_BUS.post(event);
    }
}

package dev.boze.api;

import dev.boze.api.addon.Addon;
import dev.boze.api.addon.command.AddonDispatcher;
import dev.boze.api.addon.module.ToggleableModule;
import dev.boze.api.exception.AddonInitializationException;
import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.IEventBus;
import net.minecraft.client.MinecraftClient;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Boze (API) Instance
 *
 * This class keeps track of all the addons and modules registered by addons
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
     * @throws AddonInitializationException If the addon fails to initialize
     */
    public void registerAddon(Addon addon) throws AddonInitializationException {
        assert MinecraftClient.getInstance().isOnThread();

        try {
            if (addon.initialize()) {
                addons.add(addon);
            } else {
                throw new AddonInitializationException("Error initialising addon");
            }
        } catch (Exception e) {
            throw new AddonInitializationException("Error initializing addon", e);
        }
    }

    /**
     * Get all registered addons
     *
     * @return map of all registered addon metadata and addons
     */
    public ArrayList<Addon> getAddons() {
        return addons;
    }

    /**
     * Gets a list of all modules registered by addons
     *
     * @return A list of all modules registered by addons
     */
    public List<ToggleableModule> getModules() {
        return addons.stream()
                .map(addon -> addon.modules)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    /**
     * Gets a list of all addon dispatchers
     *
     * @return A list of all addon dispatchers
     */
    public List<AddonDispatcher> getDispatchers() {
        return addons.stream()
                .map(addon -> addon.dispatcher)
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
     *
     * @param event The event to post
     */
    public void post(Object event) {
        EVENT_BUS.post(event);
    }
}

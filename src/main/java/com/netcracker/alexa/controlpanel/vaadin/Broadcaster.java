package com.netcracker.alexa.controlpanel.vaadin;

import com.vaadin.flow.shared.Registration;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class Broadcaster {
    private static Executor executor = Executors.newSingleThreadExecutor();

    private static Map<String, List<Consumer<String>>> listeners = new HashMap<>();

    public static synchronized Registration register(String addressSubscribe, Consumer<String> listener) {
        if(!listeners.containsKey(addressSubscribe)) {
            listeners.put(addressSubscribe, new ArrayList<>());
        }
        listeners.get(addressSubscribe).add(listener);

        return () -> {
            synchronized (Broadcaster.class) {
                listeners.get(addressSubscribe).remove(listener);
            }
        };
    }

    public static synchronized void broadcast(String addressSubscribe, String message) {
        List<Consumer<String>> subscribes = listeners.get(addressSubscribe);
        if(subscribes != null) {
            for (Consumer<String> listener : subscribes) {
                executor.execute(() -> listener.accept(message));
            }
        }
    }
}

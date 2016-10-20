package by.kanarski.booking.utils.threadLocal;

import java.util.HashMap;

public enum  ThreadLocalUtil {
    CONNECTION();

    private final static ThreadLocal<ThreadVariables> THREAD_VARIABLES = new ThreadLocal<ThreadVariables>() {
        @Override
        protected ThreadVariables initialValue() {
            return new ThreadVariables();
        }
    };

    public Object get() {
        String name = this.name();
        Object variable = THREAD_VARIABLES.get().get(name);
        return variable;
    }

    public Object get(Object initialValue) {
        String name = this.name();
        Object variable = THREAD_VARIABLES.get().get(name);
        if (variable == null) {
            THREAD_VARIABLES.get().put(name, initialValue);
            return get();
        } else {
            return variable;
        }
    }

    public void set(String name, Object value) {
        THREAD_VARIABLES.get().put(name, value);
    }

    public static void destroy() {
        THREAD_VARIABLES.remove();
    }
}





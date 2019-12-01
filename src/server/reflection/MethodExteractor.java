package server.reflection;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Objects;

public class MethodExteractor {

    private HashMap<String ,Method> callableMethods;

    public MethodExteractor(){
        callableMethods = new HashMap<>();
    }

    public void collectMethods(Object obj){
        if(Objects.isNull(obj))
            return;

        Class<?> cls = obj.getClass();
        collectMethods(cls);
    }

    public void collectMethods(Class<?> cls){
        if(!cls.isAnnotationPresent(ServerMethodHost.class)){
            return;
        }

        for(Method method : cls.getDeclaredMethods()){
            if(method.isAnnotationPresent(ServerMethod.class)){
                callableMethods.put(method.getDeclaredAnnotation(ServerMethod.class).callName(), method);
            }
        }
    }

    public boolean isLegalMethod(String call){
        return callableMethods.containsKey(call);
    }
    public Method getMethod(String call){
        return callableMethods.get(call);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc.plugins;

import org.apache.commons.lang.IllegalClassException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Boreeas
 */
public class PluginClassLoader extends URLClassLoader {

    private static final Log logger = LogFactory.getLog("Plugins");

    public PluginClassLoader(URL[] urls) {

        super(urls);
        logger.debug("Creating PluginClassLoader for classes " + Arrays.toString(urls));
    }

    @Override
    public Class<?> loadClass(String className) throws ClassNotFoundException {
        return super.loadClass(className);
    }

    public Class<Plugin> loadPlugin(String classname) throws
            ClassNotFoundException {

        Class<?> c = loadClass(classname);

        if (!(Plugin.class.isAssignableFrom(c))) {
            throw new IllegalClassException(Plugin.class, c);
        }

        @SuppressWarnings("unchecked")
        Class<Plugin> plugin = (Class<Plugin>) c;

        checkPluginVersion(plugin);
        return plugin;
    }

    private void checkPluginVersion(Class<Plugin> plugClass) throws
            InvalidPluginVersionException {

        Map<String, Method> loadedMethodNames = getMethodNames(plugClass);

        for (Method interfaceMethod: Plugin.class.getDeclaredMethods()) {

            String name = interfaceMethod.getName();
            Method implMethod = loadedMethodNames.get(name);

            if (implMethod == null) {

                String err = ": Not all interface methods declared: "
                             + "Missing declaration for " + name;

                throw new InvalidPluginVersionException(plugClass + err);
            }

            assertNotAbstract(plugClass, implMethod);
            assertReturnTypesEqual(plugClass, interfaceMethod, implMethod);
            assertReturnTypesEqual(plugClass, interfaceMethod, implMethod);
        }
    }

    private void assertNotAbstract(Class<Plugin> plugClass, Method implMethod) {


        if ((implMethod.getModifiers() & Modifier.ABSTRACT) != 0) {

            String err = ": Abstract method in plugin class: " + implMethod;
            throw new InvalidPluginVersionException(plugClass + err);
        }
    }

    private void assertReturnTypesEqual(Class<Plugin> plugClass, Method interfaceMethod, Method implMethod) {

        if (!interfaceMethod.getReturnType().isAssignableFrom(implMethod.getReturnType())) {

            String expected = interfaceMethod.getReturnType().getCanonicalName();
            String found = implMethod.getReturnType().getCanonicalName();

            String err = ": Invalid return type for " + implMethod + ". "
                         + "Expected " + expected + " but found " + found;

            throw new InvalidPluginVersionException(plugClass + err);
        }
    }

    private void assertParametersMatch(Class<Plugin> plugClass, Method interfaceMethod, Method implMethod) {

        Class<?>[] interfaceParameters = interfaceMethod.getParameterTypes();
        Class<?>[] implParameters = implMethod.getParameterTypes();

        if (interfaceParameters.length != implParameters.length) {

            String err =
                   ": Not enough parameters declared. "
                   + "Expected " + interfaceParameters.length + " but found " + implParameters.length;

            throw new InvalidPluginVersionException(plugClass + err);
        }

        for (int i = 0; i < interfaceParameters.length; i++) {
            if (!interfaceParameters[i].isAssignableFrom(implParameters[i])) {

                String expected = interfaceParameters[i].getCanonicalName();
                String found = implParameters[i].getCanonicalName();

                String err =
                       ": Parameter type mismatch at position " + i + ". "
                       + "Expected " + expected + " but found " + found;

                throw new InvalidPluginVersionException(plugClass + err);
            }
        }

    }

    private Map<String, Method> getMethodNames(Class<Plugin> plugClass) {

        Map<String, Method> names = new HashMap<String, Method>();

        for (Method method: plugClass.getMethods()) {
            names.put(method.getName(), method);
        }

        return names;
    }
}

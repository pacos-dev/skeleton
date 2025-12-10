package org.pacos.plugin.skeleton.backend;

import org.springframework.stereotype.Component;
import org.vaadin.addons.variablefield.data.Scope;
import org.vaadin.addons.variablefield.data.ScopeName;
import org.vaadin.addons.variablefield.data.Variable;
import org.vaadin.addons.variablefield.provider.VariableProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * This class represents variable definition provider for todoModule.
 * The interface VariableProvider is automatically detected and attached to pacos variable system
 */
@Component
public class ToDoVariableProvider implements VariableProvider {
    public static ScopeName scopeName = new ScopeName("ToDo");
    public static Scope todoScope = new Scope(scopeName.name(), 1, 'T', "rgb(45,123,34)");
    public static Map<String, Variable> variableMap = new HashMap<>();

    static {
        variableMap.put("test", new Variable(1, "test", "init value", "current value", "test value"));
        variableMap.put("todo", new Variable(1, "todo", "my todo", "another todo", "test todo"));
    }

    @Override
    public List<Variable> loadVariables(Scope scope) {
        return variableMap.values().stream().toList();
    }

    @Override
    public Optional<Variable> loadVariable(Scope scope, String s) {
        return Optional.ofNullable(variableMap.get(s));
    }

    @Override
    public Set<ScopeName> supportedScopes() {
        return Set.of(scopeName);
    }
}

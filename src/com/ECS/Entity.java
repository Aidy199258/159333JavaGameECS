package com.ECS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entity {
    private Map<Class, Component> mComponents;
    public void addComponent(Component component){

        mComponents.put(component.getClass(),component);


    }

    public Boolean hasComponent(Class type) {
        return mComponents.containsKey(type);
    }


    public Component getComponent(Class type){
        return mComponents.get(type);

    }

    //Constructor
    public Entity(){

        mComponents = new HashMap<Class, Component>();

    }




}

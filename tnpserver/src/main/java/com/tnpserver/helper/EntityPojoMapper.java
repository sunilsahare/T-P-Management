package com.tnpserver.helper;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class EntityPojoMapper<Entity, Pojo> {

    private final Class<Entity> entityClass;
    private final Class<Pojo> pojoClass;

    public EntityPojoMapper(Class<Entity> entityClass, Class<Pojo> pojoClass) {
        this.entityClass = entityClass;
        this.pojoClass = pojoClass;
    }

    public Pojo mapEntityToPojo(Entity entity) {
        Pojo pojo = instantiatePojo();
        BeanUtils.copyProperties(entity, pojo);
        return pojo;
    }

    public Entity mapPojoToEntity(Pojo pojo) {
        Entity entity = instantiateEntity();
        BeanUtils.copyProperties(pojo, entity);
        return entity;
    }

    public List<Pojo> mapEntityListToPojoList(List<Entity> entityList) {
        return entityList.stream()
                .map(this::mapEntityToPojo)
                .collect(Collectors.toList());
    }

    public List<Entity> mapPojoListToEntityList(List<Pojo> pojoList) {
        return pojoList.stream()
                .map(this::mapPojoToEntity)
                .collect(Collectors.toList());
    }

    private Pojo instantiatePojo() {
        try {
            return pojoClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error instantiating POJO.", e);
        }
    }

    private Entity instantiateEntity() {
        try {
            return entityClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error instantiating Entity.", e);
        }
    }
}

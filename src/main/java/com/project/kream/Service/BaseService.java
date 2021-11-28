package com.project.kream.Service;


import com.project.kream.ifs.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public abstract class
BaseService<Req, Res, Entity> implements CrudInterface<Req, Res> {
    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;


}

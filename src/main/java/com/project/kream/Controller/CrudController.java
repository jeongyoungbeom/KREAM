package com.project.kream.Controller;

import com.project.kream.Model.Header;
import com.project.kream.Service.BaseService;
import com.project.kream.ifs.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public abstract class CrudController<Req, Res, Entity> implements CrudInterface<Req, Res> {
    @Autowired(required = false)
    protected BaseService<Req, Res, Entity> baseService;


}

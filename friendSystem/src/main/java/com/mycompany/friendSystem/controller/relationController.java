package com.mycompany.friendSystem.controller;

import com.mycompany.friendSystem.commons.JsonReport;
import com.mycompany.friendSystem.model.Relation;
import com.mycompany.friendSystem.model.User;
import com.mycompany.friendSystem.service.RelationService;
import com.mycompany.friendSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by JinBingBing on 2016/2/4.
 */
@Controller
@RequestMapping("/relationSystem")
public class relationController {
    @Autowired
    RelationService relationService;

    @RequestMapping(value="/add",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object addUser(Relation relation){
        JsonReport jr = new JsonReport();
        try{
            boolean result = relationService.insertRelation(relation);
            if(!result){
                jr.setSuccess(false);
                jr.setErrorMsg("连接数据库失败");
            }
            jr.setData(relationService.getRelation(relation.getId()));
        }catch(Exception e){
            jr.setSuccess(false);
            jr.setData("error");
            jr.setErrorMsg(e.getMessage());
        }
        return jr;
    }
    @RequestMapping(value="/update",produces ={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object updateUser(Relation relation){
        JsonReport jr = new JsonReport();
        try{
            boolean result = relationService.updateRelation(relation);
            if(!result){
                jr.setSuccess(false);
                jr.setErrorMsg("连接数据库失败");
            }
            jr.setData(relationService.getRelation(relation.getId()));
        }catch(Exception e){
            jr.setSuccess(false);
            jr.setData("error");
            jr.setErrorMsg(e.getMessage());
        }
        return jr;
    }
    @RequestMapping(value="/delete",produces ={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object deleteUserById(String id){
        JsonReport jr = new JsonReport();
        String s = "删除成功";
        try{
            boolean result = relationService.deleteRelationById(id);
            if(!result){
                jr.setSuccess(false);
                jr.setErrorMsg("连接数据库失败");
            }
            jr.setData(s);
        }catch(Exception e){
            jr.setSuccess(false);
            jr.setData("error");
            jr.setErrorMsg(e.getMessage());
        }
        return jr;
    }
    @RequestMapping(value="/getUser",produces ={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object getUserById(String id){
        JsonReport jsonReport = new JsonReport();
        Relation relation;
        try{
            relation = relationService.getRelation(id);
            jsonReport.setData(relation);
        }catch(Exception e){
            jsonReport.setSuccess(false);
            jsonReport.setData("error");
            jsonReport.setErrorMsg(e.getMessage());
        }
        return jsonReport;
    }
}

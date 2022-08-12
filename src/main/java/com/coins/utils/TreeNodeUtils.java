package com.coins.utils;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 一维数组转树形数组
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/8/2
 **/
public class TreeNodeUtils {
    public static List<Map> toTree(int pid,List<Object> list){
        List<Map> listMap = new ArrayList<>();
		list.forEach(item -> {
            try {
                Map objStr = JsonUtil.obj2map(item);
                listMap.add(objStr);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
		});
        List<Map> tree = createTree(pid,listMap);
        return tree;
    };
    private static List<Map> createTree(int pid, List<Map> tree) {
        List<Map> treeList = new ArrayList<>();
        for (Map item : tree) {
            if (pid == (int) item.get("parentId")) {
                item.put("_showChildren",true);
                item.put("children",createTree((int) item.get("id"), tree));
                treeList.add(item);
            }
        }
        return treeList;
    }

    public static List<Map> toSelect(int pid,List<Object> list){
        List<Map> listMap = new ArrayList<>();
		list.forEach(item -> {
            try {
                Map objBean = JsonUtil.obj2map(item);
                Map<String,Object> objStr = new HashMap<>();
                objStr.put("id",objBean.get("id"));
                objStr.put("parentId",objBean.get("parentId"));
                objStr.put("title",objBean.get("name"));
                objStr.put("value",objBean.get("id"));
                objStr.put("expand",true);
                objStr.put("selected",false);
                objStr.put("checked",false);
                listMap.add(objStr);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
		});
        List<Map> tree = createSelect(pid,listMap);
        Map<String,Object> parentStr = new HashMap<>();
        parentStr.put("id",0);
        parentStr.put("parentId",0);
        parentStr.put("title","一级分类");
        parentStr.put("value",0);
        parentStr.put("expand",true);
        parentStr.put("selected",false);
        parentStr.put("checked",false);
        tree.add(0,parentStr);
        return tree;
    };
    private static List<Map> createSelect(int pid, List<Map> tree) {
        List<Map> treeList = new ArrayList<>();
        for (Map item : tree) {
            if (pid == (int) item.get("parentId")) {
                item.put("children",createTree((int) item.get("id"), tree));
                treeList.add(item);
            }
        }
        return treeList;
    }

    // 递归查所有子菜单
    public static List<Integer> getIds(int pid,List<Object> list){
        List<Map> listMap = new ArrayList<>();
		list.forEach(item -> {
            try {
                Map objStr = JsonUtil.obj2map(item);
                listMap.add(objStr);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
		});
        List<Integer> listIds = gIds(pid,listMap,new ArrayList<>());
        return listIds;
    }
	private static List<Integer> gIds(int pid, List<Map> list, List<Integer> listIds) {
        for (Map item : list) {
            if (pid == (int) item.get("parentId")) {
                listIds.add((int) item.get("id"));
                gIds((int) item.get("id"), list,listIds);
            }
        }
        return listIds;
    }
}

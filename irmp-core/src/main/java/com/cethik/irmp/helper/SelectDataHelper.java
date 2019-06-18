/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.helper;

import com.cethik.irmp.model.Corp;
import com.cethik.irmp.vmodel.DictSelect;

import java.util.ArrayList;
import java.util.List;

/**
 * the description of class or interface
 *
 * @author zhongping@hikchina.com
 * @date 2018-08-31
 */
public class SelectDataHelper {
    public static List<DictSelect> GetCorpSelect2List(List<Corp> source)
    {
        List<DictSelect> listDict = new ArrayList<>();
        for (int i = 0; i < source.size(); i++) {
            Corp c = source.get(i);
            DictSelect dict = new DictSelect();
            dict.setId(c.getCode());
            dict.setText(c.getShortname());
            dict.setCode(c.getId().toString());
            dict.setName(c.getName());

            listDict.add(dict);
        }
        return listDict;
    }
}

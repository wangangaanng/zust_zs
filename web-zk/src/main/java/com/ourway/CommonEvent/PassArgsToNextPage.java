package com.ourway.CommonEvent;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.PageInitSer;

import java.util.Map;

public class PassArgsToNextPage implements PageInitSer {
    @Override
    public void initPage(BaseWindow baseWindow, Map map, PageVO pageVO) {
        for (Object key : map.keySet()) {
            baseWindow.getPpt().put(key.toString(), map.get(key));
        }
    }
}

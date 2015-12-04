
package com.dwyoo.spring.command;

import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dwyoo.spring.dao.BDao;
import com.dwyoo.spring.dto.BDto;

public class BDeleteCommand implements BCommand {

    @Override
    public void execute(Model model) {
        // TODO Auto-generated method stub
        Map<String,Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        
        String bid = request.getParameter("bId");
        
        BDao dao = new BDao();
        dao.delete(bid);
    }

}

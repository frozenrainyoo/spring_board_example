
package com.dwyoo.spring.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dwyoo.spring.dao.BDao;
import com.dwyoo.spring.dto.BDto;

public class BContentCommand implements BCommand {

    @Override
    public void execute(Model model) {
        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        String bid = request.getParameter("bId");

        BDao dao = new BDao();
        BDto dto = dao.contentView(bid);
        
        model.addAttribute("content_view", dto);
    }
}

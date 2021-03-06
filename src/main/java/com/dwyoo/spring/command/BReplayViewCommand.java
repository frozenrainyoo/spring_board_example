package com.dwyoo.spring.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dwyoo.spring.dao.BDao;
import com.dwyoo.spring.dto.BDto;

public class BReplayViewCommand implements BCommand{

    @Override
    public void execute(Model model) {
        // TODO Auto-generated method stub
        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        String bId = request.getParameter("bId");
        BDao dao = new BDao();
        BDto dto = dao.replay_view(bId);

        model.addAttribute("reply_view", dto);
    }

}

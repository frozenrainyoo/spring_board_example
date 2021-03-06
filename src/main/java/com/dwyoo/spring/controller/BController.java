
package com.dwyoo.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dwyoo.spring.command.BCommand;
import com.dwyoo.spring.command.BContentCommand;
import com.dwyoo.spring.command.BDeleteCommand;
import com.dwyoo.spring.command.BListCommand;
import com.dwyoo.spring.command.BModifyCommand;
import com.dwyoo.spring.command.BReplayViewCommand;
import com.dwyoo.spring.command.BReplyCommand;
import com.dwyoo.spring.command.BWriteCommand;
import com.dwyoo.spring.util.Constant;

//Client요청이 들어오면 Dispather가 받고 autoscan을 통해 controller를 찾고 annotation Controller에서 찾음.
@Controller
public class BController {

    BCommand command;
    private JdbcTemplate template;
    
    @RequestMapping("/list")
    public String list(Model model) {
        System.out.println("list"); // sysout으로 로그를 쉽게 넣을 수 있다.
        command = new BListCommand();
        command.execute(model);

        return "list";
    }

    @RequestMapping("/write_view")
    public String write_view(Model model) {
        System.out.println("write_view");

        return "write_view";
    }

    // 쓰기는 폼에서 데이터를 받아와야하므로 request를 받는다.
    @RequestMapping("/write")
    public String write(HttpServletRequest request, Model model) {
        System.out.println("write");

        model.addAttribute("request", request);
        command = new BWriteCommand();
        command.execute(model);

        return "redirect:list";
    }

    @RequestMapping("/content_view")
    public String content_view(HttpServletRequest request, Model model) {
        System.out.println("content_view");
        model.addAttribute("request", request);
        command = new BContentCommand();
        command.execute(model);

        return "content_view";

    }

    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    public String modify(HttpServletRequest request, Model model) {
        System.out.println("modify");

        model.addAttribute("request", request);
        command = new BModifyCommand();
        command.execute(model);

        return "redirect:list";
    }

    @RequestMapping("/reply_view")
    public String reply_view(HttpServletRequest request, Model model) {
        System.out.println("reply_view");

        model.addAttribute("request", request);
        command = new BReplayViewCommand();
        command.execute(model);

        return "reply_view";
    }

    @RequestMapping("/reply")
    public String reply(HttpServletRequest request, Model model) {
        System.out.println("reply");

        model.addAttribute("request", request);
        command = new BReplyCommand();
        command.execute(model);

        return "redirect:list";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, Model model) {
        System.out.println("delete");

        model.addAttribute("request", request);
        command = new BDeleteCommand();
        command.execute(model);

        return "redirect:list";
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    //자동으로 템플릿 셋
    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
        Constant.template = template;
    }
}

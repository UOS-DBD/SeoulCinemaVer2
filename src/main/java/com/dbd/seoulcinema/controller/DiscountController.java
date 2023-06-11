package com.dbd.seoulcinema.controller;

import com.dbd.seoulcinema.domain.enumeration.DiscountType;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DiscountController {


    @GetMapping("/events")
    public String viewEventList(Model model){

        List<DiscountType> collect = EnumSet.allOf(DiscountType.class)
            .stream()
            .filter(f -> f.getAmount() != 0)
            .collect(Collectors.toList());

        model.addAttribute("events",collect);
        System.out.println(collect.size());

        return "viewEvents";
    }
}

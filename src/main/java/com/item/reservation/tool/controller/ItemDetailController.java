package com.item.reservation.tool.controller;

import com.item.reservation.tool.entity.Item;
import com.item.reservation.tool.entity.ItemReservation;
import com.item.reservation.tool.entity.User;
import com.item.reservation.tool.form.ReserveItemForm;
import com.item.reservation.tool.service.ItemService;
import com.item.reservation.tool.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Log4j
@Controller
@RequestMapping(value = "/itemDetail/{itemUuid}")
public class ItemDetailController {

    private static final String DETAIL_ITEM_VIEW = "itemDetailPage";
    private static final String RESERVE_ITEM_FORM = "reserveItemForm";

    private final ItemService itemService;
    private final UserService userService;


    @Autowired
    public ItemDetailController(final ItemService itemService, final UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView initDetailItemPage(@PathVariable("itemUuid") String uuid) {
        ModelAndView modelAndView = new ModelAndView(DETAIL_ITEM_VIEW);
        Item item = itemService.findByUuid(uuid);
        if (item.getItemReservations() != null && !item.getItemReservations().isEmpty()) {
            ItemReservation itemReservation = item.getItemReservations().get(0);
            User user = userService.findUserByUuid(itemReservation.getUserUuid());
            itemReservation.setUser(user);
        }
        modelAndView.addObject("item", item);
        modelAndView.addObject(RESERVE_ITEM_FORM, new ReserveItemForm());
        return modelAndView;
    }
}

package com.item.reservation.tool.controller;

import com.item.reservation.tool.entity.Item;
import com.item.reservation.tool.entity.ItemReservation;
import com.item.reservation.tool.entity.User;
import com.item.reservation.tool.exceptions.SaveItemImageException;
import com.item.reservation.tool.form.CreateItemForm;
import com.item.reservation.tool.form.ReserveItemForm;
import com.item.reservation.tool.service.ItemReservationService;
import com.item.reservation.tool.service.ItemService;
import com.item.reservation.tool.service.message.MessageService;
import com.item.reservation.tool.utils.ImageUtils;
import com.item.reservation.tool.utils.MessageType;
import com.item.reservation.tool.utils.UserUtils;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j
@Controller
public class ItemController {

    private static final String CREATE_ITEM_VIEW = "createNewItem";
    private static final String CREATE_ITEM_PATH = "/createItem";
    private static final String OWN_RESERVED_ITEM_PATH = "/reservedItem";
    private static final String OWN_RESERVED_ITEM_VIEW = "userReservedItem";
    private static final String WELCOME_VIEW = "welcome";
    private static final String WELCOME_PATH = "/welcome";
    private static final String CREATE_ITEM_FORM = "createItemForm";
    private static final String RESERVE_ITEM_FORM = "reserveItemForm";
    private static final String RESERVE_ITEM_PATH = "/reserveItem";
    private static final String STATIC_IMAGE_PATH = "src/main/webapp/resources/images/items";
    private static final String DATE_PATTERN = "yyyy-MM-dd";


    private final MessageService messageService;

    private final ItemService itemService;

    private final ItemReservationService itemReservationService;


    @Autowired
    public ItemController(final MessageService messageService, final ItemService itemService,
                          final ItemReservationService itemReservationService) {
        this.messageService = messageService;
        this.itemService = itemService;
        this.itemReservationService = itemReservationService;
    }

    @GetMapping(value = WELCOME_PATH)
    public ModelAndView initWelcome() {
        ModelAndView modelAndView = new ModelAndView(WELCOME_VIEW);
        loadItemsToPage(modelAndView);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'WORKER')")
    @GetMapping(value = CREATE_ITEM_PATH)
    public ModelAndView initCreateNewItem() {
        ModelAndView modelAndView = new ModelAndView(CREATE_ITEM_VIEW);
        modelAndView.addObject(CREATE_ITEM_FORM, new CreateItemForm());
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'WORKER')")
    @PostMapping(value = CREATE_ITEM_PATH)
    public ModelAndView createNewItem(@Valid @ModelAttribute(CREATE_ITEM_FORM) CreateItemForm createItemForm, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName(CREATE_ITEM_VIEW);
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        String message = "com.item.reservation.tool.create.item.created.successfully";
        MessageType messageType = MessageType.SUCCESS;
        if (createItemForm.getImages() != null && createItemForm.getImages().length == 0) {
            message = "com.item.reservation.tool.create.item.image.empty.error";
            messageType = MessageType.ERROR;
        }
        Item item = new Item();
        try {
            BeanUtils.copyProperties(createItemForm, item);
            saveImages(createItemForm.getImages(), item);
            item.setUuid(itemService.getUniqueUuid());
            itemService.createNewItem(item);
        } catch (SaveItemImageException e) {
            message = "com.item.reservation.tool.create.item.image.save.error";
            messageType = MessageType.ERROR;
            log.error("image not save ", e);
        } catch (Exception e) {
            message = "com.item.reservation.tool.create.item.error";
            messageType = MessageType.ERROR;
            log.error("item not saved", e);
        }
        modelAndView.addObject(CREATE_ITEM_FORM, new CreateItemForm());//set form to empty
        modelAndView.addObject(messageType.getType(), messageService.message(message));
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'WORKER', 'VERIFIED')")
    @GetMapping(value = OWN_RESERVED_ITEM_PATH)
    public ModelAndView userReservedItem(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView(OWN_RESERVED_ITEM_VIEW);
        loadReservedUserItems(modelAndView, httpServletRequest);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'WORKER', 'VERIFIED')")
    @GetMapping(value = "/cancelReservation/{uuid}")
    public ModelAndView cancelReservation(@PathVariable("uuid") String uuid, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView(OWN_RESERVED_ITEM_VIEW);
        String message = "com.item.reservation.tool.delete.reservation.successfully";
        MessageType messageType = MessageType.SUCCESS;
        try {
            Item item = itemService.findByUuid(uuid);
            User user = UserUtils.getUserFromSession(httpServletRequest);
            if (item.getItemReservations() != null && !item.getItemReservations().isEmpty()) {
                ItemReservation itemReservation = item.getItemReservations().get(0);
                if (user.getUuid().equals(itemReservation.getUserUuid())) {
                    itemReservationService.deleteReservation(itemReservation);
                } else {
                    message = "com.item.reservation.tool.delete.reservation.another.user.exception";
                    messageType = MessageType.ERROR;
                }
            } else {
                message = "com.item.reservation.tool.delete.reservation.dont.exist.exception";
                messageType = MessageType.ERROR;
            }
            loadReservedUserItems(modelAndView, httpServletRequest);
        } catch (Exception e) {
            message = "com.item.reservation.tool.delete.reservation.unsuccessfully";
            messageType = MessageType.ERROR;
            log.error("Can delete reservation");
        }
        modelAndView.addObject(messageType.getType(), messageService.message(message));
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'WORKER', 'VERIFIED')")
    @PostMapping(value = RESERVE_ITEM_PATH)
    public ModelAndView reserveItem(@Valid @ModelAttribute(RESERVE_ITEM_FORM) ReserveItemForm reserveItemForm, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView(WELCOME_PATH);
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        ItemReservation itemReservation = new ItemReservation();
        String message = "com.item.reservation.tool.item.reservation.successfully";
        MessageType messageType = MessageType.SUCCESS;
        try {
            Date end = DateUtils.parseDate(reserveItemForm.getReservationEnds(), DATE_PATTERN);
            Date start = new Date();
            if (end.after(start)) {
                Item item = itemService.findByUuid(reserveItemForm.getUuid());
                itemReservation.setReservationEnd(end);
                itemReservation.setReservationStart(start);
                itemReservation.setItemId(item.getId());

                User user = UserUtils.getUserFromSession(httpServletRequest);
                itemReservation.setUserUuid(user.getUuid());
                itemReservationService.reserveItem(itemReservation);
            } else {
                message = "com.item.reservation.tool.item.reservation.wrong.date";
                messageType = MessageType.ERROR;
            }
        } catch (Exception e) {
            message = "com.item.reservation.tool.item.reservation.unsuccessfully";
            messageType = MessageType.ERROR;
            log.error("Cant create item ", e);
        }
        loadItemsToPage(modelAndView);
        modelAndView.addObject(messageType.getType(), messageService.message(message));
        return modelAndView;
    }

    private void loadItemsToPage(ModelAndView modelAndView) {
        //need for items in load page
        List<Item> items = itemService.getAllItems();
        items.stream().filter(i -> i.getItemReservations() != null && !i.getItemReservations().isEmpty())
                .forEach(i -> {
                    ItemReservation itemReservation = i.getItemReservations().get(0);
                    if (itemReservation.getReservationEnd().before(new Date())) {
                        itemReservationService.deleteReservation(itemReservation);
                        i.setItemReservations(null);
                    }
                });
        modelAndView.addObject(RESERVE_ITEM_FORM, new ReserveItemForm());
        modelAndView.addObject("items", items);
    }

    private void loadReservedUserItems(ModelAndView modelAndView, HttpServletRequest httpServletRequest) {
        List<Item> results = new ArrayList<>();
        User user = UserUtils.getUserFromSession(httpServletRequest);
        List<Item> items = itemService.getAllItems();
        if (items != null) {
            items.stream().filter(i -> i.getItemReservations() != null && !i.getItemReservations().isEmpty())
                    .forEach(i -> {
                        if (i.getItemReservations().get(0).getUserUuid().equals(user.getUuid())) {
                            results.add(i);
                        }
                    });
            modelAndView.addObject("items", results);
        }
    }

    private void saveImages(MultipartFile[] multipartFiles, Item item) throws SaveItemImageException {
        int i = 1;
        for (MultipartFile m : multipartFiles) {
            if (StringUtils.isNotBlank(m.getOriginalFilename())) {
                String path = Paths.get(STATIC_IMAGE_PATH).toAbsolutePath().toString() + "/";
                try {
                    Path p = Paths.get(path + m.getOriginalFilename());
                    Files.write(p, ImageUtils.resizeImage(m.getBytes()));
                    switch (i++) {
                        case 1:
                            item.setImagePath1(m.getOriginalFilename());
                            break;
                        case 2:
                            item.setImagePath2(m.getOriginalFilename());
                            break;
                        case 3:
                            item.setImagePath3(m.getOriginalFilename());
                            break;
                        case 4:
                            item.setImagePath4(m.getOriginalFilename());
                        default:
                            log.error("Image list to long");
                    }
                } catch (IOException e) {
                    log.error("Image not saved", e);
                    throw new SaveItemImageException("Can`t save image");
                }
            }
        }
    }
}
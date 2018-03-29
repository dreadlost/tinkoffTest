package ru.tinkoff.testTask.controller;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.testTask.model.Application;
import ru.tinkoff.testTask.model.Contact;
import ru.tinkoff.testTask.model.ContactRepository;
import ru.tinkoff.testTask.model.virtual.Response;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(value = "Апи тестового задания")
public class ApplicationRestController {

    private static final Logger log = LoggerFactory.getLogger(ApplicationRestController.class);

    private final ContactRepository contactRepository;

    @Autowired
    ApplicationRestController(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @ApiOperation(value = "Метод получения последней заявки контакта по ид")
    @RequestMapping(value = "/{contactId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Контакт не найден"),
            @ApiResponse(code = 200, message = "Успех") })
    ResponseEntity<?> readApplication(@ApiParam(value = "Идентификатор контакта", required = true) @PathVariable("contactId") Long contactId){
        try {
            Contact contact =  this.contactRepository.getOne(contactId);
            Optional<Application> application = contact.getApplications().stream().max(Comparator.comparing(Application::getDate));
            Response response = new Response();
            response.setApplicationId(application.get().getId());
            response.setContactId(application.get().getContact().getId());
            response.setDate(application.get().getDate());
            response.setName(application.get().getProductName());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


}

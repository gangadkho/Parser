package com.hackathon.parser;


import com.hackathon.parser.domain.SearchText;
import com.hackathon.parser.service.TextParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ParserController {

    @Autowired
    TextParserService textParserService;

    @RequestMapping(
            method = {RequestMethod.POST},
            value = {"/parse"},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public List<String> parsePossibleNames(@RequestBody SearchText searchText) {
        return textParserService.getPossibleNames(searchText);
    }
}

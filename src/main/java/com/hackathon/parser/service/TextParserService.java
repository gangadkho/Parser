package com.hackathon.parser.service;

import com.hackathon.parser.domain.SearchText;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TextParserService {

    String serializedClassifier = "src/main/resources/classifiers/english.all.3class.distsim.crf.ser.gz";


    public List<String> getPossibleNames(SearchText searchText) {
        log.info("Searching possible names in the search text : {} ", searchText);
        List<String> namesList = new ArrayList<>();
        AbstractSequenceClassifier classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
        List<List> res = classifier.classify(searchText.getText());
        List<CoreLabel> re1 = res.get(0);
        for (CoreLabel cl : re1) {

                if ("PERSON".equalsIgnoreCase(cl.get(CoreAnnotations.AnswerAnnotation.class))) {
                    namesList.add(cl.get(CoreAnnotations.ValueAnnotation.class));
                }

        }
        log.info("Possible name list : {}", namesList);
        return namesList;
    }

}

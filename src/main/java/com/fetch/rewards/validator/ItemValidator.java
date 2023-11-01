package com.fetch.rewards.validator;

import com.fetch.rewards.dto.Item;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Component
public class ItemValidator {
    public boolean validateItem(Item item) {
        if(Strings.isBlank(item.getPrice()) || Strings.isBlank(item.getShortDescription())){
            return false;
        }
        return true;
    }
}

package com.fetch.rewards.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class IdValidator {

    private static final Pattern UUID_PATTERN = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    public boolean isUUIDValid(String id) {
        return UUID_PATTERN.matcher(id).matches();
    }
}

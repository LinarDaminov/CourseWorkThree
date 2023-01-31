package com.example.courseworkthree.model;



import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToSizeConverter implements Converter<String, Size> {

    @Override
    public Size convert(String source) {
        return Size.forValues(source);
    }

}